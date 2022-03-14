package com.grp6.edim.client.controller;

import com.grp6.edim.server.logging.LogLevel;
import com.grp6.edim.server.logging.Logger;
import com.grp6.edim.shared.Message;

import java.io.IOException;
import java.io.ObjectInputStream;

public class Receiver implements Runnable {
    private CommunicationControllerClient communicationController;

    private boolean isRunning = false;

    private ObjectInputStream inputStream;
    private Thread thread = null;


    public Receiver(CommunicationControllerClient communicationController, ObjectInputStream inputStream) {
        this.communicationController = communicationController;
        this.inputStream = inputStream;
    }

    public void start() {
        if (thread == null) {
            isRunning = true;
            thread = new Thread(this);
            thread.start();
        } else {
            Logger.log("Thread already running", LogLevel.Debug);
        }
    }

    public void stop() {
        if (thread != null) {
            isRunning = false;
            thread.interrupt();
            thread = null;
        } else {
            Logger.log("Thread already stopped", LogLevel.Debug);
        }
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                Object object = inputStream.readObject();
                if (object instanceof Message) {
                    communicationController.receiveMessage((Message) object);
                }
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
                stop();
            }
        }
    }
}
