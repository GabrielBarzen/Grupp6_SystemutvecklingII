package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import server.Buffer;

import static org.junit.Assert.*;

public class BufferTest {
    Buffer buffer=new Buffer();
    @Test
    public void put() throws InterruptedException {

        assertEquals("hej",buffer.put("hej"));
    }

    @Test
    public void get() throws InterruptedException {
        buffer.put("hej");
       assertEquals("hej",buffer.get());

    }

    @Test
    public void size() {
        buffer.put("hej");
        assertEquals(1,buffer.size());
    }
}