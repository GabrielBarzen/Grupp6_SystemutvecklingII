import client.Buffer;
import client.ClientController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import server.Activity;
import server.User;

import static org.junit.jupiter.api.Assertions.*;

public class ClientControllerTest {
    ClientController clientController = new ClientController();
    Buffer buffer;

    @BeforeEach
    public void setup() {
        buffer = new Buffer();
    }

    @Test
    public void createUser() {
        User user = clientController.createUser("Isak");
        assertEquals("Isak", user.getUsername());
    }

    @Test
    public void sendActivityToCCC() {
        User user = clientController.createUser("Isak");
        Activity activity = new Activity();
        boolean bool = clientController.sendActivityToCCC(activity);
        assertTrue(bool);
        try {
            assertEquals(activity, buffer.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
