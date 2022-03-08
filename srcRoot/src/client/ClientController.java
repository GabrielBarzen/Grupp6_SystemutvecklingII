package client;

import server.Activity;


import client.gui.MainFrame;
import server_v2.Message;
import server_v2.MessageType;
import server_v2.User;
import server.UserType;

import java.awt.image.BufferedImage;

/**
 * This class manages the logic for the Client and controls the data flow.
 *
 * @version 1.0
 * @author Carolin Nordstrom, Oscar Kareld, Chanon Borgstrom, Sofia Hallberg.
 */

public class ClientController {

    private MainFrame mainFrame;
    private ClientCommunicationController ccc;
    private User user;
    private Activity currentActivity;
    private String className = "Class: ClientController ";

    /**
     * Constructs a MainFrame and a ClientCommunicationController object. Then calls the method createUser.
     */
    public ClientController() {
        mainFrame = new MainFrame(this);
    }

    /**
     * Receives a String and creates a new User object and calls the logIn method.
     *
     * @param userName
     */
    public void createUser(String userName) {
        user = new User(userName);
        logIn();
    }

    /**
     * Receives an Activity Object and sends it forth to the ClientCommunicationController.
     *
     * @param activity the received object.
     */
    public void sendActivityToCCC(Activity activity) {
        user.addActivityToList(activity);
        Message message = new Message(activity,user,MessageType.NewActivity);
        ccc.sendObject(message);
    }

    public void saveActivity(Activity activity, BufferedImage image) {
        ccc.sendObject(activity);
        ccc.sendObject(image);
    }

    /**
     * Sets the UserType to LOGIN and sends the user object to ClientCommunicationController.
     */
    public void logIn() {
        user.setUserType(UserType.LOGIN);
        ccc = new ClientCommunicationController(this);
        Message message = new Message(null, user, MessageType.Login);
        ccc.sendObject(message);
        ccc.sendObject(new Message(MessageType.NewActivity));
    }

    /**
     * Sets the UserType to LOGOUT and sends the user object to ClientCommunicationController.
     */
    public void logOut() {
        user.setUserType(UserType.LOGOUT);
        ccc.sendObject(user);

    }

    /**
     * Receives an Activity object an sends it forth to MainFrame.
     *
     * @param activity the received object.
     */
    public void receiveNotificationFromCCC(Activity activity) {
        setCurrentActivity(activity);
        mainFrame.showNotification(currentActivity);
    }

    private void setCurrentActivity(Activity activity) {
        currentActivity = activity;
    }

    /**
     * Replaces the temporary user object with the already existing object from the server.
     * If it's a new user, a welcome message is sent.
     *
     * @param user the received object.
     */
    public void receiveUser(User user) {
        UserType userType = user.getUserType();
        this.user = user;
        if (userType == UserType.SENDWELCOME) {
            mainFrame.sendWelcomeMessage();
        }
    }

    /**
     * Sets a users interval and the UserType to SENDINTERVAL and sends it to {@link ClientCommunicationController}.
     * @param interval the notification interval.
     */
    public void setInterval(int interval) {
        user.setNotificationInterval(interval);
        user.setUserType(UserType.SENDINTERVAL);
        ccc.sendObject(user);
    }

    public void getNewActivity() {
        sendActivityToCCC(currentActivity);
    }
}
