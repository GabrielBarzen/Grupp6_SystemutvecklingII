package tests;

import org.junit.Test;
import server.Activity;
import server.User;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class UserTest {
    private LinkedList<Activity> completedActivities=new LinkedList<>();

    private User user=new User("Benny");

    @Test
    public void getCompletedActivities() {
/*       completedActivities.add();
        user.setCompletedActivities(completedActivities);
        user.getCompletedActivities();*/
    }

    @Test
    public void addActivityToList() {
    }

    @Test
    public void setCompletedActivities() {
    }

    @Test
    public void getUsername() {
        user.setUsername("Göran");
        assertEquals("Göran",user.getUsername());
    }

    @Test
    public void setUsername() {
        assertEquals("Laban",user.setUsername("Laban"));
    }

    @Test
    public void getAge() {
        user.setAge(12);
        assertEquals(12,user.getAge());
    }

    @Test
    public void setAge() {
        assertEquals(13,user.setAge(13));
    }

    @Test
    public void getNotificationInterval() {
        user.setNotificationInterval(40);
        assertEquals(40,user.getNotificationInterval());
    }

    @Test
    public void setNotificationInterval() {
        assertEquals(38,user.setNotificationInterval(38));
    }

    @Test
    public void isOnline() {
    }

    @Test
    public void setOnline() {
    }

    @Test
    public void getUserType() {
    }

    @Test
    public void setUserType() {
    }

    @Test
    public void getDelayedActivity() {
    }

    @Test
    public void setDelayedActivity() {
    }
}