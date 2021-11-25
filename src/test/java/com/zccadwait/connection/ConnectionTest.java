package com.zccadwait.connection;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

// Can be converted into parametrized tests
public class ConnectionTest {
    @Test
    public void testGoogle(){
        Connection connection = new Connection("https://www.google.com");
        assertNotNull(connection.executeGet(null, null));
    }

    @Test
    public void testRandom(){
        Connection connection = new Connection("wlls://lol.nosuchsiteexists.lol");
        assertNull(connection.executeGet(null, null));
    }
}
