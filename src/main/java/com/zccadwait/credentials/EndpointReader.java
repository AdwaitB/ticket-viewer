package com.zccadwait.credentials;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Reads an endpoint from the file along with the credentials.
 *
 * @author Adwait Bauskar
 */
public class EndpointReader {
    private static final Logger LOGGER = Logger.getLogger(EndpointReader.class.getName());

    static {
        LOGGER.setLevel(Level.SEVERE);
    }

    private String url;
    private String username;
    private String password;

    public EndpointReader(String credentialsFileName){
        try {
            LOGGER.info("Reading from file " + credentialsFileName);

            File file = new File(credentialsFileName);
            Scanner myReader = new Scanner(file);
            
            if(myReader.hasNext())
                this.url = myReader.nextLine();
            if(myReader.hasNext())
                this.username = myReader.nextLine();
            if(myReader.hasNext())
                this.password = myReader.nextLine();
            
            myReader.close();
            LOGGER.info("Successfully read from file " + credentialsFileName);
        } catch (FileNotFoundException e) {
            LOGGER.severe("Failed to read from file " +credentialsFileName);
            LOGGER.severe(Arrays.toString(e.getStackTrace()));
        }
    }

    public EndpointReader(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        EndpointReader that = (EndpointReader) o;

        return Objects.equals(url, that.url) &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password);
    }
}
