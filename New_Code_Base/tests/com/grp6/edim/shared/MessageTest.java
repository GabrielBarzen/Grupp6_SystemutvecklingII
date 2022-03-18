package com.grp6.edim.shared;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Viktor Näslund
 */
class MessageTest {

    Message message;

    @BeforeEach
    void setUp() {
        message = new Message(null, null, MessageType.Login);
    }

    @Test
    void getType() {
        assertEquals(MessageType.Login, message.getType());
    }

    @Test
    void getData() {
        assertNull(message.getData());
    }

    @Test
    void getUser() {
        assertNull(message.getUser());
    }
}