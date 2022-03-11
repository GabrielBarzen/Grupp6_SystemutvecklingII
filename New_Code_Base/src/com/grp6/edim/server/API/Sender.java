package com.grp6.edim.server.API;

import com.grp6.edim.shared.Buffer;
import com.grp6.edim.server.CommunicationController;
import com.grp6.edim.server.Message;
import com.grp6.edim.server.logging.LogLevel;
import com.grp6.edim.server.logging.Logger;

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