
//Benny
import server.Buffer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

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