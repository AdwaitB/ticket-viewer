package com.zccadwait.console;

import com.zccadwait.connection.Connection;
import com.zccadwait.connection.ConnectionManager;
import com.zccadwait.credentials.EndpointReader;

import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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

        command = new Command("quit", "Quit the console", "quit");
        commandsMap.put(command.getCommand(), command);
    }

    public static void main(String[] args){
        LOGGER.setLevel(Level.SEVERE);

        LOGGER.info("Starting console.");
        System.out.println("Welcome to Adwait's Zendesk Ticket Reader.");
        runConsole();
        System.out.println("Thanks for using Adwait's Zendesk Ticket Reader.");
    }

    private static String readDefaultEndpoint(){
        EndpointReader endpointReader = new EndpointReader(CREDENTIALS_HELPER_FILE);
        Connection connection = ConnectionManager.getConnection(endpointReader.getUrl());
        return connection.executeGet(endpointReader.getUsername(), endpointReader.getPassword());
    }

    private static void runConsole(){
        String command = "help";

        Scanner scanner = new Scanner(System.in);

        while(!"quit".equals(command)){
            System.out.println(command);
            command = scanner.nextLine();
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
