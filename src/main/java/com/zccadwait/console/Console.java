package com.zccadwait.console;

import com.zccadwait.connection.Connection;
import com.zccadwait.connection.ConnectionManager;
import com.zccadwait.connection.Response;
import com.zccadwait.credentials.EndpointReader;
import com.zccadwait.model.Ticket;
import com.zccadwait.model.TicketList;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.zccadwait.console.ConsolePrintUtil.printEntries;
import static com.zccadwait.console.ConsolePrintUtil.printHelp;
import static java.lang.Math.min;

/**
 * This class is the main entry point for the console.
 *
 * @author Adwait Bauskar
 */
public class Console {
    private static final Logger LOGGER = Logger.getLogger(Console.class.getName());

    static{
        LOGGER.setLevel(Level.SEVERE);
    }

    private static final Integer TICKET_MAX_VIEW_COUNT = 25;

    private static final String CREDENTIALS_HELPER_FILE = "src/main/resources/credentials.properties";

    private static final String PROMPT = " > ";
    private static final String SUBSTITUTE_PROMPT = " >> ";


    public static void main(String[] args){
        LOGGER.setLevel(Level.SEVERE);

        LOGGER.info("Starting console.");
        System.out.println("Welcome to Adwait's Zendesk Ticket Reader.");
        printHelp();
        runConsole();
        System.out.println("Thanks for using Adwait's Zendesk Ticket Reader.");
    }

    private static Response readDefaultEndpoint(String suffix){
        EndpointReader endpointReader = new EndpointReader(CREDENTIALS_HELPER_FILE);
        Connection connection = ConnectionManager.getConnection(endpointReader.getUrl() + suffix);
        return connection.executeGet(endpointReader.getUsername(), endpointReader.getPassword());
    }

    /**
     * Runs the main console for tracking the commands.
     */
    private static void runConsole(){
        String command;
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.print(PROMPT);
            command = scanner.nextLine();

            String[] args = command.split(" ", 0);
            if(args.length != 0){
                if("quit".equals(args[0]))
                    break;
                else if("get-all".equals(args[0])){
                    Response response = readDefaultEndpoint("");

                    if(response.getResponseCode() == 401){
                        System.out.println(ConsoleMessages.ERROR401.getMessage());
                        continue;
                    }

                    TicketList ticketList = TicketList.parseTicketList(response.getResponse());

                    if((response.getResponse() == null) || (ticketList == null)){
                        System.out.println(ConsoleMessages.SERVER_DOWN.getMessage());
                        System.out.println(ConsoleMessages.TRY_AGAIN.getMessage());
                        continue;
                    }

                    runSubstituteConsoleForList(ticketList);
                }
                else if("get-single".equals(args[0])){
                    if(args.length != 2){
                        System.out.printf((ConsoleMessages.GREATER_THAN_N_ARGUMENTS.getMessage()) + "%n", 2);
                        continue;
                    }

                    try{
                        int id = Integer.parseInt(args[1]);
                        Response response = readDefaultEndpoint("/" + id);

                        if(response.getResponseCode() == 401){
                            System.out.println(ConsoleMessages.ERROR401.getMessage());
                            continue;
                        }

                        Ticket ticket = Ticket.parseTicket(response.getResponse());

                        if((response.getResponse() == null) || (ticket == null)){
                            System.out.printf(ConsoleMessages.SERVER_DOWN_INCORRECT_ID.getMessage() + "\n", id);
                            System.out.println(ConsoleMessages.TRY_AGAIN.getMessage());
                            continue;
                        }

                        printEntries(List.of(ticket), 0, 1);
                    } catch (Exception e){
                        System.out.println("2nd argument passed to get-single is not an integer.");
                    }
                }
                else if("help".equals(args[0]))
                    printHelp();
                else {
                    System.out.print(ConsoleMessages.INCORRECT_COMMAND.getMessage());
                    printHelp();
                }
            }
        }
    }

    /**
     * Runs the substitute console for paging in between the large list.
     * Displays TICKET_MAX_VIEW_COUNT number of tickets per page.
     *
     * @param ticketList List of tickets to iterate through.
     */
    private static void runSubstituteConsoleForList(TicketList ticketList){
        System.out.println("Retrieved " + ticketList.getCount() + " tickets.");
        int pages = ticketList.getCount()/TICKET_MAX_VIEW_COUNT;
        if(ticketList.getCount()%TICKET_MAX_VIEW_COUNT != 0) pages++;

        if(pages > 1)
            System.out.println("Displaying result in " + pages + " pages.");

        String command;
        Scanner scanner = new Scanner(System.in);

        for(int i = 0; i < pages; i++) {
            System.out.println("Page " + (i+1));
            printEntries(ticketList.getTickets(), i*TICKET_MAX_VIEW_COUNT,
                    min((i+1)*TICKET_MAX_VIEW_COUNT, ticketList.getCount()));
            if(i == pages-1)
                break;

            System.out.println("Press q to quit and any other to continue ...");
            System.out.print(SUBSTITUTE_PROMPT);
            command = scanner.nextLine();
            if("q".equals(command))
                break;
        }
    }
}
