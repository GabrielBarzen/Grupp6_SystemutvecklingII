
import client.ClientController;
import model.UserType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.ServerController;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ardian Glamniki
 */
class ClientControllerTest {
    private ClientController clientController;

    @BeforeAll
    static void startServer() {
        ServerController server = new ServerController(4343);
        server.start();
    }

    @BeforeEach
    void setUp() {
        clientController = new ClientController();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testCreateUser() {
        clientController.createUser("Test");
        assertEquals("Test", clientController.getUser().getUsername());
    }

    @Test
    void testSaveUser() {
        clientController.createUser("Test");
        assertEquals("Test", clientController.getUser().getUsername());
    }

    @Test
    void testLogin() {
        clientController.createUser("Test");
        clientController.logIn();
        assertEquals(UserType.LOGIN, clientController.getUser().getUserType());
    }
}