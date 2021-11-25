package com.zccadwait;

import java.util.logging.Logger;

public class Console {
    private static final Logger LOGGER = Logger.getLogger(Console.class.getName());

    public static void main(String[] args){
        LOGGER.info("Starting console.");
        System.out.println("Welcome to Zendesk Ticket Reader");
    }
}
