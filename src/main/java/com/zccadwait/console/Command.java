package com.zccadwait.console;

/**
 * Stores a command class to add details for every command.
 * The extension to this class will also store the actions it will perform.
 *
 * @author Adwait Bauskar
 */
public class Command {
    private final String command;
    private final String docString;
    private final String formatString;

    public Command(String command, String docString, String formatString){
        this.command = command;
        this.docString = docString;
        this.formatString = formatString;
    }

    public String getHelpDoc(String formatString){
        return String.format(formatString, this.command, this.docString, this.formatString);
    }

    public String getCommand() {
        return command;
    }

    public String getDocString() {
        return docString;
    }

    public String getFormatString() {
        return formatString;
    }
}
