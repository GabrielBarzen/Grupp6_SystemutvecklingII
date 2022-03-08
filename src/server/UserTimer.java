package server;


import model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class handles the information for a user's timer.
 *
 * @version 1.0
 * @author Carolin Nordstrom, Oscar Kareld, Chanon Borgstrom, Sofia Hallberg.
 */

public class UserTimer implements ActionListener {

    private Timer timer;
    private int currentTime;
    private int delay;
    private int userInterval;
    private boolean activateDelay;
    private ServerController serverController;
    private volatile User user;
    private String className = "Class: UserTimer ";


    public UserTimer(ServerController serverController, User user) {
        currentTime = 0;
        this.serverController = serverController;
        this.user = user;
    }

    /**
     * Starts the timer object.
     */
    public boolean startTimer() {
        timer = new Timer(60000, this);
        timer.start();
        if(timer.isRunning()){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Stops the timer object.
     */
    public boolean stopTimer() {
        currentTime = 0;
        if(timer!=null) {
            timer.stop();
            timer = null;
            return true;
        }
        else{
            return false;
        }

    }

    /**
     * Updates the user object with the received user.
     * @param user the received user.
     */
    public User updateUser(User user) {
        this.user = user;
        return user;
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public int setCurrentTime(int currentTime) {
        this.currentTime = currentTime;
        return currentTime;
    }

    public User getUser() {
        return user;
    }

    public int setDelayTimer(int delay) {
        this.delay = delay;
        return delay;
    }

    public boolean setActiveDelay(boolean active){
        this.activateDelay = active;
        return active;
    }

    public void actionPerformed(ActionEvent e) {
        currentTime++;
        if (checkTimeInterval()) {
            sendActivity();
            stopTimer();
        } else if (activateDelay) {
            if (checkDelayInterval()) {
                sendActivity();
                stopTimer();
                activateDelay = false;
            }
        }
//        System.out.println(className + currentTime);
    }

    /**
     * Calls for {@link ServerController} method sendActivity.
     */
    public boolean sendActivity() {
        return serverController.sendActivity(user.getUsername());
    }

    /**
     * Checks if the delayed interval is as much as the current time.
     * @return a boolean if it's true or false.
     */
    public boolean checkDelayInterval() {
        if (currentTime >= delay) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the currentTime is at the same as the User's notification interval.
     * @return a boolean if they are the same value.
     */
    public boolean checkTimeInterval() {
        System.out.println("UserTimer:  currentTime: " + currentTime + " notifInterval: " + user.getNotificationInterval());
        if (currentTime >= user.getNotificationInterval()) {
            return true;
        }
        return false;

    }
}
