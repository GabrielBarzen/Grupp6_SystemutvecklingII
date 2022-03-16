package com.grp6.edim.server;

import com.grp6.edim.shared.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

class CommunicationControllerTest {
    private CommunicationController communicationController;

    @BeforeEach
    void setUp() {
        communicationController = new CommunicationController();
    }

    @Test
    void testReceiveMessage() {
        communicationController.receiveMessage(new Message(MessageType.OK));
        assertEquals(1, communicationController.receiveBuffer.size());
    }

    @Test
    void testConnectionReceived() throws IOException {
        communicationController.connectionReceived(new Socket("127.0.0.1", 4343));
        assertEquals(1, communicationController.threadPoolExecutor.getActiveCount());
    }


    @Test
    void testSendObjectInvalidUser() throws IOException {
        communicationController.connectionReceived(new Socket("127.0.0.1", 4343));
        assertThrows(NullPointerException.class, () -> communicationController.sendObject(new User("Test"),
                new Message(MessageType.OK)));
    }

    @AfterEach
    void tearDown() {
        communicationController = null;
    }
}