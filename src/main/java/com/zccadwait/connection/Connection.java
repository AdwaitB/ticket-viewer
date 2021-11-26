package com.zccadwait.connection;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.*;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class defines a connection to an api and also gets data from that endpoint.
 * This class does not have a constructor and is only instantiated using its factory class ConnectionManager
 *
 * @author Adwait Bauskar
 */
public class Connection {
    private static Logger LOGGER = Logger.getLogger(Connection.class.getName());

    static {
        LOGGER.setLevel(Level.SEVERE);
    }

    private URL url;

    /**
     * Create a URL object from a given https url.
     *
     * @param httpsURL URL to connect to
     */
    Connection(String httpsURL){
        try {
            this.url = new URL(httpsURL);
        } catch (Exception e) {
            LOGGER.severe("Given an invalid URL " + httpsURL);
            LOGGER.severe(Arrays.toString(e.getStackTrace()));
        }
    }

    /**
     * Execute a get request given the url. Uses Basic Authorization.
     *
     * @param userName username used for authorization
     * @param password password used for authorization
     * @return String of response if the get receives 200. null otherwise.
     */
    public String executeGet(String userName, String password) {
        try {
            if(url == null)
                return null;

            LOGGER.info("Starting connection to URL " + url);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("accept", "application/json");
            connection.setDoOutput(true);

            if(password != null) {
                LOGGER.info("Setting auth connection for username " + userName + ", to URL " + url.toString());
                String authStr = userName + ":" + password;
                String authEncoded = Base64.getEncoder().encodeToString(authStr.getBytes());
                connection.setRequestProperty("Authorization", "Basic " + authEncoded);
            }

            // Both unavailable API as well no output returns 404, so there is no use of using seperate if conditions.
            if (connection.getResponseCode() == 200) {
                LOGGER.info("Received 200 for " + url.toString());
                Scanner responseReader = new Scanner(connection.getInputStream());
                StringBuilder stringBuilder = new StringBuilder();

                while (responseReader.hasNextLine())
                    stringBuilder.append(responseReader.nextLine()).append("\n");

                responseReader.close();
                return stringBuilder.toString();
            }
            else {
                LOGGER.info("URL " + url.toString() + ", did not return 200.");
                return null;
            }
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
            LOGGER.severe(Arrays.toString(e.getStackTrace()));
        }

        return null;
    }
}
