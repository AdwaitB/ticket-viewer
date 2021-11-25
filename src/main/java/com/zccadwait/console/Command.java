package com.zccadwait.console;

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
