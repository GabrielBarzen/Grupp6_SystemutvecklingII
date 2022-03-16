package com.grp6.edim.shared;

import com.grp6.edim.server.MessageType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActivityTest {


    Activity activity;

    @BeforeEach
    void setUp(){
        activity = new Activity("Name");
    }

    @Test
    void getActivityName() {
        assertEquals("Name", activity.getActivityName());
    }

    @Test
    void getActivityInfo() {
        activity.setDescription("Info");
        assertEquals("Info", activity.getActivityInfo());
    }

    @Test
    void getActivityUser() {
        activity.setActivityUser("User");
        assertEquals("User", activity.getActivityUser());
    }
}