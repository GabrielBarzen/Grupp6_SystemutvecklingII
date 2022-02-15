package tests;

import org.junit.Before;
import org.junit.Test;
import server.Activity;
import server.ServerController;
import server.User;
import server.UserTimer;

import static org.junit.Assert.*;

public class UserTimerTest {
    private User user;
    private UserTimer userTimer;
    private ServerController serverController;

    @Before
    public void setUp(){
        serverController = new ServerController(4343);
        user = new User("Kalle");
        userTimer = new UserTimer(serverController, user);

    }

    @Test
    public void startTimer() {
        assertTrue(userTimer.startTimer());
    }

    @Test
    public void stopTimer() {
        assertFalse(userTimer.stopTimer());

    }

    @Test
    public void updateUser() {
       assertEquals(user,userTimer.updateUser(user));
    }

    @Test
    public void getCurrentTime() {
        int currentTime = userTimer.setCurrentTime(1);
        assertEquals(currentTime,userTimer.getCurrentTime());

    }

    @Test
    public void setCurrentTime() {
        assertEquals(1, userTimer.setCurrentTime(1));
    }

    @Test
    public void getUser() {
        userTimer.updateUser(user);
        assertEquals(user,userTimer.getUser());
    }

    @Test
    public void setDelayTimer() {
        assertEquals(1,userTimer.setDelayTimer(1));
    }

    @Test
    public void setActiveDelay() {
        assertTrue(userTimer.setActiveDelay(true));
    }

    @Test
    public void actionPerformed() {
        // vet ej hur jag ska testa denna
    }

    @Test
    public void sendActivity() {
        // denna går ej att testa
    }

    @Test
    public void checkDelayInterval() {
        assertTrue(userTimer.checkDelayInterval());
    }

    @Test
    public void checkTimeInterval() {
        assertFalse(userTimer.checkTimeInterval());
    }
}