package com.grp6.edim.shared;

import com.grp6.edim.shared.MessageType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Viktor Näslund
 */
class ActivityTest {


    Activity activity;

    @BeforeEach
    void setUp(){
        activity = new Activity("Name");
    }

    @Test
    void setActivityName() {
        assertEquals("Name", activity.setActivityName("Name"));
    }

    @Test
    void setDescription() {
        assertEquals("Info", activity.setDescription("Info"));
    }

    @Test
    void getInstruction() {
        activity.setInstruction("Instruction");
        assertEquals("Instruction", activity.getInstruction());
    }
}