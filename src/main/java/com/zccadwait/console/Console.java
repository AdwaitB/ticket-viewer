package com.zccadwait.console;

import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Console {
    private static final Logger LOGGER = Logger.getLogger(Console.class.getName());

    private static final HashMap<String, Command> commandsMap;

    private static final Integer rowSize = 85;
    private static final String formatString = "| %10s | %40s | %25s |";
    private static final String divider = "-".repeat(rowSize);

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
        printHelp();

        runConsole();

        System.out.println("Thanks for using Adwait's Zendesk Ticket Reader.");
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
        System.out.println(divider);
        System.out.printf((formatString) + "%n", "Command", "Description", "Usage");
        System.out.println(divider);
        for(Command supportedCommand : commandsMap.values())
            System.out.println(supportedCommand.getHelpDoc(formatString));
        System.out.println(divider);
    }
}
