package server_v2.API;

import server.Buffer;
import server_v2.CommunicationController;
import server_v2.Message;
import server_v2.logging.LogLevel;
import server_v2.logging.Logger;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class Sender implements Runnable {

    private CommunicationController communicationController;
    private boolean isRunning = false;
    ObjectOutputStream outputStream;
    Buffer<Object> buffer = new Buffer<>();

    Thread thread = null;

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

    public Sender(CommunicationController communicationController, ObjectOutputStream outputStream) {
        this.communicationController = communicationController;
        this.outputStream = outputStream;
    }

    public void send(Message obj) {
        buffer.put(obj);
    }



    @Override
    public void run() {
        while (isRunning) {
            try {
                outputStream.writeObject(buffer.get());
            } catch (Exception e) {
                e.printStackTrace();
                stop();
            }
        }
    }


}
