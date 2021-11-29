package com.zccadwait.console;

public enum ConsoleMessages {
    ERROR401("The server failed to authenticate your credentials."),
    SERVER_DOWN("The server seems to be down."),
    TRY_AGAIN("Please try again after some time."),
    GREATER_THAN_N_ARGUMENTS("More than %d arguments passed. Please check help for the command."),
    INCORRECT_COMMAND("Incorrect command received."),
    SERVER_DOWN_INCORRECT_ID("Either the server is down or the ticket with the given id %d does not exist.");

    String message;
    ConsoleMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
