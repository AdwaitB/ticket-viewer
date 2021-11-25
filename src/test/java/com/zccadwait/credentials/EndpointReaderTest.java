package com.zccadwait.credentials;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Can be converted into parametrized tests
public class EndpointReaderTest {
    private static final String ROOT_FOLDER = "src/test/resources/endpoints/";

    @Test
    public void testNoFile(){
        assertEquals(
                new EndpointReader(ROOT_FOLDER + "nofile"),
                new EndpointReader(null, null, null)
        );
    }

    @Test
    public void testDefault(){
        assertEquals(
                new EndpointReader(ROOT_FOLDER + "1"),
                new EndpointReader("a", "b", "c")
        );
    }

    @Test
    public void testMissingURL(){
        assertEquals(
                new EndpointReader(ROOT_FOLDER + "2"),
                new EndpointReader("", "b", "c")
        );
    }

    @Test
    public void testMissingUsername(){
        assertEquals(
                new EndpointReader(ROOT_FOLDER + "3"),
                new EndpointReader("a", "", "c")
        );
    }

    @Test
    public void testMissingPassword(){
        assertEquals(
                new EndpointReader(ROOT_FOLDER + "4"),
                new EndpointReader("a", "b", null)
        );
    }
}
