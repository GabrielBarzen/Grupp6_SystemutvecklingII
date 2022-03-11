
import client.ClientCommunicationController;
import client.ClientController;
import model.Buffer;
import model.User;
import model.UserType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import server.*;


import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CommunicationTest {
    ClientCommunicationController clientCommunicationController;
    ServerController server;
    ClientController clientController;
    Buffer<Object> buffer;

    @BeforeAll
    void setUp() {
        server = new ServerController(4343);
        server.start();
        buffer = server.getReceiverServer().getReceiveBuffer();

        clientController = new ClientController();
        clientCommunicationController = new ClientCommunicationController(clientController);
    }

    @Test
    void test(){
        User user = new User("test");
        user.setUserType(UserType.OTHER);
        clientCommunicationController.sendObject(user);
        Object obj = new Object();
        try {
            obj = buffer.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertInstanceOf(User.class, obj.getClass());

    }



}