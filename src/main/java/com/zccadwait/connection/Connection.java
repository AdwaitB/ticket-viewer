package com.zccadwait.connection;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.*;
import java.util.Base64;
import java.util.Scanner;

public class Connection {
    URL url;

    public Connection(String httpsURL){
        try {
            this.url = new URL(httpsURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String executeGet(String userName, String password) {
        try {
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("accept", "application/json");
            connection.setDoOutput(true);

            if(userName != null) {
                String authStr = userName + ":" + password;
                String authEncoded = Base64.getEncoder().encodeToString(authStr.getBytes());
                connection.setRequestProperty("Authorization", "Basic " + authEncoded);
            }
            
            if (connection.getResponseCode() == 200) {
                Scanner responseReader = new Scanner(connection.getInputStream());
                StringBuilder stringBuilder = new StringBuilder();

                while (responseReader.hasNextLine())
                    stringBuilder.append(responseReader.nextLine()).append("\n");

                responseReader.close();
                return stringBuilder.toString();
            }
            else
                return "";
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
