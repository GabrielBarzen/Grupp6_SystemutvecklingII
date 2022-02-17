package tests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.ServerController;
import server.User;
import server.UserTimer;

import java.io.IOException;
import java.net.BindException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Linn Borgström
 * Date: 2022-02-15
 */
public class UserTimerTest {
    private User user;
    private UserTimer userTimer;
    private ServerController serverController;

    @BeforeEach
    public void setUp() throws IOException {
        serverController = new ServerController(4343);
        user = new User("Kalle");
        userTimer = new UserTimer(serverController, user);

    }

    /**
     * Tests that the timer is started.
     */
    @Test
    public void startTimer() {
        assertTrue(userTimer.startTimer());
    }

    /**
     * Tests that the timed has stoped
     */
    @Test
    public void stopTimer() {
        assertFalse(userTimer.stopTimer());

    }

    /**
     * Tests that user is updated correctly
     */
    @Test
    public void updateUser() {
       assertEquals(user,userTimer.updateUser(user));
    }

    /**
     * Tests that the time is returned correctly
     */
    @Test
    public void getCurrentTime() {
        int currentTime = userTimer.setCurrentTime(1);
        assertEquals(currentTime,userTimer.getCurrentTime());

    }

    /**
     * Tests that the time is set correctly
     */
    @Test
    public void setCurrentTime() {
        assertEquals(1, userTimer.setCurrentTime(1));
    }

    /**
     * Tests that the method returns the right user
     */
    @Test
    public void getUser() {
        userTimer.updateUser(user);
        assertEquals(user,userTimer.getUser());
    }

    /**
     * Tests that the timer is set correctly
     */
    @Test
    public void setDelayTimer() {
        assertEquals(1,userTimer.setDelayTimer(1));
    }

    /**
     * Tests that the activeDelay is true
     */
    @Test
    public void setActiveDelay() {
        assertTrue(userTimer.setActiveDelay(true));
    }

    /**
     * Unable to test this
     */
    @Test
    public void actionPerformed() {

    }

    /**
     * Unable to test this
     */
    @Test
    public void sendActivity() {

    }

    /**
     * Tests that the delay interval is true
     */
    @Test
    public void checkDelayInterval() {
        assertTrue(userTimer.checkDelayInterval());
    }

    /**
     * Tests this that the time interval is false
     */
    @Test
    public void checkTimeInterval() {
        assertFalse(userTimer.checkTimeInterval());
    }
}