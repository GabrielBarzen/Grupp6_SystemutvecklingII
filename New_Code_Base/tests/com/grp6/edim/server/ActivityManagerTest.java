package com.grp6.edim.server;

import com.grp6.edim.shared.Activity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActivityManagerTest {
    private Activity activity;
    private ActivityManager activityManager;

    @BeforeEach
    public void setup() {
        activityManager = new ActivityManager("files/activities.dat");
        activity = new Activity("Isaks aktivitet");
        activity.setInstruction("Gör så här:");
        activity.setDescription("Hallå hallå");
    }

    @Test
    public void saveActivityNoImage() {
        assertEquals("Needs to include an image",activityManager.saveActivity(activity));
    }
}
