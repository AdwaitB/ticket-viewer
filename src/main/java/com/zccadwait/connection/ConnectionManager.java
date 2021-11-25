package com.zccadwait.connection;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionManager {
    private static final Logger LOGGER = Logger.getLogger(ConnectionManager.class.getName());

    static {
        LOGGER.setLevel(Level.SEVERE);
    }

    private static final Map<String, Connection> connectionMap = new HashMap<>();

    public static Connection getConnection(String url){
        if(connectionMap.containsKey(url)) {
            LOGGER.info("Giving previously created connection for " + url);
            return connectionMap.get(url);
        }
        else{
            LOGGER.info("Creating new connection for " + url);
            Connection connection = new Connection(url);
            connectionMap.put(url, connection);
            return connection;
        }
    }
}
