package tests;

import client.ClientCommunicationController;
import client.ClientController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.ServerController;

import static org.junit.jupiter.api.Assertions.*;

class ClientCommunicationControllerTest {
    ClientCommunicationController client;
    ServerController server;
    ClientController clientController;
    @BeforeEach
    void setUp() {
/*        clientController= new ClientController();
        client= new ClientCommunicationController(clientController);
        server= new ServerController(4343);*/

    }

    @Test
    void connect() {
    }

    @Test
    void disconnect() {
  //      server.start();//
        assertTrue(client.disconnect());

    }

    @Test
    void sendObject() {
    }
}