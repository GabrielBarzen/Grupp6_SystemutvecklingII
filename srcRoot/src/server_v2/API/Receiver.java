package server_v2.API;

import server_v2.CommunicationController;
import server_v2.Message;
import server_v2.logging.LogLevel;
import server_v2.logging.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;

public class Receiver implements Runnable {

    private CommunicationController communicationController;

    private boolean isRunning = false;

    private ObjectInputStream inputStream;
    Thread thread = null;


    public Receiver(CommunicationController communicationController, ObjectInputStream inputStream) {
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
            }
        }
    }
}
