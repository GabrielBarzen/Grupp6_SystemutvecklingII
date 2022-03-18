package com.grp6.edim.client.controller;

import com.grp6.edim.client.ClientMain;
import com.grp6.edim.shared.*;

import com.grp6.edim.server.logging.LogLevel;
import com.grp6.edim.server.logging.Logger;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class CommunicationControllerClient {

    private Communication communication;
    private Buffer<Message> sendBuffer = new Buffer<>();
    private Timer timer = new Timer();
    private User user;
    private int value;
    private ClientMain main;
    private Socket socket;
    private Receiver receiver;
    private Sender sender;

    public CommunicationControllerClient(ClientMain main, Socket socket) {
        Thread thread = new Thread(new receiver_handler());
        thread.start();
        this.main = main;
        this.socket = socket;

        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            ois = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        receiver = new Receiver(this, ois);
        sender = new Sender(this, oos);
        receiver.start();
        sender.start();
        communication = new Communication(receiver, sender);
    }

    public void sendObject(Message message) {
        System.out.println(message.getUser().getUsername());
        communication.getSender().send(message);
    }


    public void receiveMessage(Message message) {
        sendBuffer.put(message);
    }

    public void handleMessage(Message message) {
        System.out.println("what client");
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
                System.out.println("new activity");
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

    public void setUser(User user) {
        this.user = user;
    }

    public void showNotification(Activity activity) {
        System.out.println("inne i showNotification");

        Toolkit.getDefaultToolkit().beep();
        ImageIcon activityIcon = createActivityIcon(activity);
        String[] buttons = {"Jag har gjort aktiviteten!", "Påminn mig om fem minuter",};
        String[] instructions = activity.getInstruction().split("&");

        int answer = JOptionPane.showOptionDialog(null, instructions, activity.getName(),
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, activityIcon, buttons, buttons[0]);
        if (answer == 0) {
            //TODO lista ska uppdateras
            main.getMainPanel().getListModel().addElement(activity.getName() + new Date());
            startActivityTimer(value * 60 * 1000);
        } else {
            startActivityTimer(5 * 60 * 1000);
        }
    }

    public ImageIcon createActivityIcon(Activity activity) {
        try {
            ImageIcon activityIcon = new ImageIcon(activity.getImage());
            Image image = activityIcon.getImage();
            Image newImg = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            return new ImageIcon(newImg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void startActivityTimer(int value) {
        this.value = value;
        timer = new Timer();
        System.out.println("timertask starting");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Timertask started");
                sendObject(new Message(null, user, MessageType.NewActivity));
            }
        }, value);
    }

    public void stopActivityTimer() {
        timer.cancel();
    }

    class receiver_handler implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    handleMessage(sendBuffer.get());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

