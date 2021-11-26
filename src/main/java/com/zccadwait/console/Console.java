package com.zccadwait.console;

import com.zccadwait.connection.Connection;
import com.zccadwait.connection.ConnectionManager;
import com.zccadwait.credentials.EndpointReader;
import com.zccadwait.model.Ticket;
import com.zccadwait.model.TicketList;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Math.min;

/**
 * This class is the main entry point for the console.
 *
 * @author Adwait Bauskar
 */
public class Console {
    private static final Logger LOGGER = Logger.getLogger(Console.class.getName());

    private static final HashMap<String, Command> commandsMap;

    private static final Integer HELP_ROW_SIZE = 85;
    private static final String HELP_FORMAT_STRING = "| %10s | %40s | %25s |";
    private static final Integer TICKET_ROW_SIZE = 85;
    private static final String TICKET_LIST_FORMAT_STRING = "| %4s | %11s | %7s | %50s |";
    private static final Integer TICKET_MAX_VIEW_COUNT = 25;

    private static final String CREDENTIALS_HELPER_FILE = "src/main/resources/credentials.properties";

    static {
        commandsMap = new HashMap<>();

        Command command;

        command = new Command("get-all", "Get all tickets", "get-all");
        commandsMap.put(command.getCommand(), command);

        command = new Command("get-single", "Get a single ticket given an id", "get-single <ticket-id>");
        commandsMap.put(command.getCommand(), command);

        command = new Command("help", "Get the list of all valid commands", "help");
        commandsMap.put(command.getCommand(), command);

        command = new Command("quit", "Quit the console", "quit");
        commandsMap.put(command.getCommand(), command);
    }

    public static void main(String[] args){
        LOGGER.setLevel(Level.SEVERE);

        LOGGER.info("Starting console.");
        System.out.println("Welcome to Adwait's Zendesk Ticket Reader.");
        printHelp();
        runConsole();
        System.out.println("Thanks for using Adwait's Zendesk Ticket Reader.");
    }

    private static String readDefaultEndpoint(String suffix){
        EndpointReader endpointReader = new EndpointReader(CREDENTIALS_HELPER_FILE);
        Connection connection = ConnectionManager.getConnection(endpointReader.getUrl() + suffix);
        return connection.executeGet(endpointReader.getUsername(), endpointReader.getPassword());
    }

    private static void runConsole(){
        String command;
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.print(" > ");
            command = scanner.nextLine();

            String[] args = command.split(" ", 0);
            if(args.length != 0){
                if("quit".equals(args[0]))
                    break;
                else if("get-all".equals(args[0])){
                    String ret = readDefaultEndpoint("");
                    TicketList ticketList = TicketList.parseTicketList(ret);
                    runSubconsoleForList(ticketList);
                }
                else if("get-single".equals(args[0])){
                    if(args.length != 2){
                        System.out.println("More than 2 arguments passed. Please pass single id.");
                        continue;
                    }

                    try{
                        int id = Integer.parseInt(args[1]);
                        String ret = readDefaultEndpoint("/" + id);
                        Ticket ticket = Ticket.parseTicket(ret);

                        if((ret == null) || (ticket == null)){
                            System.out.println("The ticket with the given id " + id + " does not exist.");
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
                    System.out.print("Incorrect command received. ");
                    printHelp();
                }
            }
        }
    }

    private static void runSubconsoleForList(TicketList ticketList){
        System.out.println("Retrieved " + ticketList.getCount() + " tickets.");
        int pages = ticketList.getCount()/TICKET_MAX_VIEW_COUNT;

        if(pages > 1)
            System.out.println("Displaying result in " + pages + " pages.");

        String command;
        Scanner scanner = new Scanner(System.in);

        for(int i = 0; i < pages; i++) {
            System.out.println("Page " + (i+1));
            printEntries(ticketList.getTickets(), i*TICKET_MAX_VIEW_COUNT,
                    min((i+1)*TICKET_MAX_VIEW_COUNT, ticketList.getCount()));
            System.out.println("Press q to quit and any other to continue ...");

            if(i == pages-1)
                break;

            command = scanner.nextLine();
            if("q".equals(command))
                break;
        }
    }

    private static void printEntries(List<Ticket> tickets, int start, int end){
        System.out.println("-".repeat(TICKET_ROW_SIZE));
        System.out.printf(TICKET_LIST_FORMAT_STRING + "\n", "ID", "Created On", "Status", "Subject");
        System.out.println("-".repeat(TICKET_ROW_SIZE));
        for(int i = start; i < min(end, tickets.size()); i++)
            System.out.println(tickets.get(i).getEntry(TICKET_LIST_FORMAT_STRING));
        System.out.println("-".repeat(TICKET_ROW_SIZE));
    }

    private static void printHelp(){
        System.out.println("Available Commands:");
        System.out.println("-".repeat(HELP_ROW_SIZE));
        System.out.printf((HELP_FORMAT_STRING) + "%n", "Command", "Description", "Usage");
        System.out.println("-".repeat(HELP_ROW_SIZE));
        for(Command supportedCommand : commandsMap.values())
            System.out.println(supportedCommand.getHelpDoc(HELP_FORMAT_STRING));
        System.out.println("-".repeat(HELP_ROW_SIZE));
    }
}
