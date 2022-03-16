package com.grp6.edim.client.controller;

import com.grp6.edim.shared.*;

import com.grp6.edim.server.logging.LogLevel;
import com.grp6.edim.server.logging.Logger;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadPoolExecutor;

public class CommunicationControllerClient {

    private ThreadPoolExecutor threadPoolExecutor;
    private Map<User, Communication> connectionMap;
    private CommunicationControllerClient communicationController = this;
    private Buffer<Message> sendBuffer = new Buffer<>();
    private Timer timer;
    private User user;
    private int value;

    public CommunicationControllerClient() {
        connectionMap = new HashMap<>();
        Thread thread = new Thread(new receiver_handler());
    }

    public void sendObject(Message message) {
        connectionMap.get(message.getUser()).getSender().send(message);
    }


    public void receiveMessage(Message message) {
        sendBuffer.put(message);
    }

    public void handleMessage(Message message) {
        switch (message.getType()) {

            case Login: {
                Logger.log("illegal login", LogLevel.Warning);
                this.user = message.getUser();
            }
            break;

            case Logout: {
                Logger.log("logout", LogLevel.Debug);
            } //TODO login with disconnect
            break;

            case NewActivity: {
                Logger.log("new activity requested", LogLevel.Debug);
                showNotification((Activity) message.getData());
            }
            break;

            case OK: {
                Logger.log("message received on server", LogLevel.Debug);
            } //TODO handle OK message
            break;

            case Error: {
                Logger.log("error on server side", LogLevel.Debug);
            } //TODO handle Error message
            break;
        }
    }

    class receiver_handler implements Runnable {
        @Override
        public void run() {
            try {
                handleMessage(sendBuffer.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void showNotification(Activity activity) {

        Toolkit.getDefaultToolkit().beep();
        ImageIcon activityIcon = createActivityIcon(activity);
        String[] buttons = {"Jag har gjort aktiviteten!", "Påminn mig om fem minuter",};
        String[] instructions = activity.getActivityInstruction().split("&");

        int answer = JOptionPane.showOptionDialog(null, instructions, activity.getActivityName(),
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, activityIcon, buttons, buttons[0]);
        if (answer == 0) {
            //TODO lista ska uppdateras
            startActivityTimer(value * 60 * 1000);
        } else {
            startActivityTimer(5 * 60 * 1000);
        }
    }

    public ImageIcon createActivityIcon(Activity activity) {
        ImageIcon activityIcon = activity.getActivityImage();
        Image image = activityIcon.getImage();
        Image newImg = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }

    public void startActivityTimer(int value) {
        this.value = value;
        timer = new java.util.Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                sendObject(new Message(null, user, MessageType.NewActivity));
            }
        }, value);
    }
}

