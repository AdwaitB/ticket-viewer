package com.zccadwait.credentials;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EndpointReader {
    private String url;
    private String username;
    private String password;

    public EndpointReader(String credentialsFileName){
        try {
            File file = new File(credentialsFileName);
            Scanner myReader = new Scanner(file);
            
            if(myReader.hasNext())
                this.url = myReader.nextLine();
            if(myReader.hasNext())
                this.username = myReader.nextLine();
            if(myReader.hasNext())
                this.password = myReader.nextLine();
            
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
}
