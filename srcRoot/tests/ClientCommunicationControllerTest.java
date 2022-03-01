
//Benny
import client.ClientCommunicationController;
import client.ClientController;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import server.ServerController;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ClientCommunicationControllerTest {
    ClientCommunicationController client;
    ServerController server;
    ClientController clientController;

    @BeforeAll
    void setUp() {
        server = new ServerController(4343);
        server.start();
        clientController= new ClientController();
        client = new ClientCommunicationController(clientController);
    }



    @Test
    void sendObject() {

    }

    @Test
    void disconnect() {
        assertTrue(client.disconnect());
    }

    @Test
    void connect() {
        client.connect();
        assertTrue(client.isConnected());
    }
}