package tests;

import org.junit.Before;
import org.junit.Test;
import server.Activity;
import server.ActivityRegister;

import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * Author: Linn Borgström
 * Date: 2022-02-15
 */
public class ActivityRegisterTest {
    private ActivityRegister activityRegister;
    private Activity activity;
    private LinkedList<Activity> activityLinkedList;
    @Before
    public void setUp(){
        activityRegister = new ActivityRegister("Ursprungliga Projektfiler/EDIM/files/activities.txt");
        activity = new Activity();
    }

    /**
     * Tests that the method makes the same list that is created in the class
     */
    @Test
    public void getActivityRegister() {
        activityLinkedList = new LinkedList<>();
        activityLinkedList = activityRegister.createRegister("Ursprungliga Projektfiler/EDIM/files/activities.txt");
        assertEquals(activityLinkedList,activityRegister.getActivityRegister());
    }

    /**
     * Tests that the method created an object
     */
    @Test
    public void createActivityRegister() {
        assertNotNull(activityRegister.createRegister("Ursprungliga Projektfiler/EDIM/files/activities.txt"));
    }
}