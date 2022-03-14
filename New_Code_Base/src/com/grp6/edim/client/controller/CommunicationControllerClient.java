package com.grp6.edim.client.controller;

import com.grp6.edim.client.ClientMain;
import com.grp6.edim.shared.*;

import com.grp6.edim.server.logging.LogLevel;
import com.grp6.edim.server.logging.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class CommunicationControllerClient {

    private ThreadPoolExecutor threadPoolExecutor;
    private Map<User, Communication> connectionMap;
    private CommunicationControllerClient communicationController = this;
    private Buffer<Message> sendBuffer = new Buffer<>();

    public CommunicationControllerClient() {
        threadPoolExecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        connectionMap = new HashMap<>();
        Thread thread = new Thread(new receiver_handler());
    }

    public void sendObject(User user, Message message) {
        connectionMap.get(user).getSender().send(message);
    }


    public void receiveMessage(Message message) {
        sendBuffer.put(message);
    }

    public Object handleMessage(Message message) {
        switch (message.getType()) {

            case Login: {
                Logger.log("illegal login", LogLevel.Warning);
            }
            break;
            case Logout: {
                Logger.log("logout", LogLevel.Debug);
            } //TODO login with disconnect
            break;

            case NewActivity: {
                Logger.log("new activity requested", LogLevel.Debug);
                ClientMain.getActivityManager().getActivity(0);
            }
            break;

            case SaveActivity: {
                Logger.log("save activity requested", LogLevel.Debug);
                if (message.getData() instanceof Activity) {
                    sendObject(message.getUser(), message);
                }
            } //TODO save activity to file
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
        return null;
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
}

