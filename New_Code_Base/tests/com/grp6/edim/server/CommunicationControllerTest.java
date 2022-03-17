package com.grp6.edim.server;

import com.grp6.edim.shared.Buffer;
import com.grp6.edim.shared.Message;
import com.grp6.edim.shared.MessageType;
import com.grp6.edim.shared.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ardian Glamniki
 */
class CommunicationControllerTest {
    private CommunicationController communicationController;

    @BeforeEach
    void setUp() {
        communicationController = new CommunicationController();
    }

    @Test
    void testReceiveMessage() {
        Buffer<Message> buffer = communicationController.receiveMessage(new Message(MessageType.OK));
        assertEquals(1, buffer.size());
    }

    @Test
    void testConnectionReceived() throws IOException {
        communicationController.connectionReceived(new Socket("127.0.0.1", 4343));
        assertEquals(1, communicationController.getThreadPoolExecutor().getActiveCount());
    }


    @Test
    void testSendObjectInvalidUser() throws IOException {
        communicationController.connectionReceived(new Socket("127.0.0.1", 4343));
        assertThrows(NullPointerException.class, () -> communicationController.sendObject(new Message("Test", new User("Test"), MessageType.OK)));
    }

    @AfterEach
    void tearDown() {
        communicationController = null;
    }
}