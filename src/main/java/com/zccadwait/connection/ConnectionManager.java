package com.zccadwait.connection;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Factory for connections.
 * It maintains a map of every api endpoint to URL object. This is aimed at making the connections persistent in the future.
 *
 * @author Adwait Bauskar
 */
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
