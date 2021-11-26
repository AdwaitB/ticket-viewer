package com.zccadwait.console;

import com.zccadwait.model.Ticket;

import java.util.HashMap;
import java.util.List;

import static java.lang.Math.min;

public class ConsolePrintUtil {
    static final HashMap<String, Command> commandsMap;

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

    private static final Integer HELP_ROW_SIZE = 85;
    private static final String HELP_FORMAT_STRING = "| %10s | %40s | %25s |";
    private static final Integer TICKET_ROW_SIZE = 85;
    private static final String TICKET_LIST_FORMAT_STRING = "| %4s | %11s | %7s | %50s |";

    public static void printEntries(List<Ticket> tickets, int start, int end){
        System.out.println("-".repeat(TICKET_ROW_SIZE));
        System.out.printf(TICKET_LIST_FORMAT_STRING + "\n", "ID", "Created On", "Status", "Subject");
        System.out.println("-".repeat(TICKET_ROW_SIZE));
        for(int i = start; i < min(end, tickets.size()); i++)
            System.out.println(tickets.get(i).getEntry(TICKET_LIST_FORMAT_STRING));
        System.out.println("-".repeat(TICKET_ROW_SIZE));
    }

    public static void printHelp(){
        System.out.println("Available Commands:");
        System.out.println("-".repeat(HELP_ROW_SIZE));
        System.out.printf((HELP_FORMAT_STRING) + "%n", "Command", "Description", "Usage");
        System.out.println("-".repeat(HELP_ROW_SIZE));
        for(Command supportedCommand : commandsMap.values())
            System.out.println(supportedCommand.getHelpDoc(HELP_FORMAT_STRING));
        System.out.println("-".repeat(HELP_ROW_SIZE));
    }
}
