package com.grp6.edim.server;

import com.grp6.edim.shared.Activity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActivityManagerTest {
    private ActivityManager activityManager;

    @BeforeEach
    public void setup() {
        activityManager = new ActivityManager("files/activities.dat");
    }

    @Test
    public void saveActivityNoImage() {
        Activity activity = new Activity("Isaks aktivitet");
        activity.setInstruction("Gör så här:");
        activity.setDescription("Hallå hallå");
        assertEquals("Needs to include an image",activityManager.saveActivity(activity));
    }

    @Test
    public void saveActivityNoInstruction() {
        Activity activity = new Activity("Isaks aktivitet");
        activity.setDescription("Hallå hallå");
        assertEquals("Needs to include an instruction", activityManager.saveActivity(activity));
    }
}
