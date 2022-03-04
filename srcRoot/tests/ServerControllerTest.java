import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import server.ServerController;
import server.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServerControllerTest {

    ServerController controller = new ServerController(4343);

    @Test
    public void createUserTimer() {
        assertEquals("John Doe", controller.createUserTimer(new User("John Doe")).getUser().getUsername());
    }
}
