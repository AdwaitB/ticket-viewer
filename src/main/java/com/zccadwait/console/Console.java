package com.zccadwait.console;

import com.zccadwait.connection.Connection;
import com.zccadwait.connection.ConnectionManager;
import com.zccadwait.credentials.EndpointReader;
import com.zccadwait.model.Ticket;
import com.zccadwait.model.TicketList;

import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is the main entry point for the console.
 *
 * @author Adwait Bauskar
 */
public class Console {
    private static final Logger LOGGER = Logger.getLogger(Console.class.getName());

    private static final HashMap<String, Command> commandsMap;

    private static final Integer ROW_SIZE = 85;
    private static final String FORMAT_STRING = "| %10s | %40s | %25s |";
    private static final String DIVIDER = "-".repeat(ROW_SIZE);

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
                    System.out.println("Received " + ticketList.getCount() + " tickets.");
                }
                else if("get-single".equals(args[0])){
                    if(args.length != 2){
                        System.out.println("More than 2 arguments passed. Please pass single id.");
                        continue;
                    }

                    int id;

                    try{
                        id = Integer.parseInt(args[1]);
                    } catch (Exception e){
                        System.out.println("2nd arguement passed to get-single is not an integer.");
                        continue;
                    }

                    String ret = readDefaultEndpoint("/" + id);

                    if(ret == null){
                        System.out.println("The ticket with the given id " + id + " does not exist.");
                        continue;
                    }

                    Ticket ticket = Ticket.parseTicket(ret);

                    if(ticket == null){
                        System.out.println("The ticket with the given id " + id + " does not exist.");
                        continue;
                    }

                    System.out.println(ticket.getDescription());
                }
                else if("help".equals(args[0]))
                    printHelp();
            }
        }
    }

    private static void printHelp(){
        System.out.println("Available Commands.");
        System.out.println(DIVIDER);
        System.out.printf((FORMAT_STRING) + "%n", "Command", "Description", "Usage");
        System.out.println(DIVIDER);
        for(Command supportedCommand : commandsMap.values())
            System.out.println(supportedCommand.getHelpDoc(FORMAT_STRING));
        System.out.println(DIVIDER);
    }
}
