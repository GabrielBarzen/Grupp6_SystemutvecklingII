package com.grp6.edim.server;

import com.grp6.edim.server.logging.LogLevel;
import com.grp6.edim.server.logging.Logger;
import com.grp6.edim.shared.ActivityManager;

import java.io.IOException;

public class ServerMain {

    private static int port = 4343;
    private static boolean doLogging = true;
    private static ActivityManager activityManager = new ActivityManager("files/activities.dat");


    public static void main(String[] args) {

        for (String argument : args) {
            String[] splitArgument = argument.split(":");
            switch (splitArgument[0]) {
                case "port":
                    port = Integer.parseInt(splitArgument[1]);
                    break;
                case "log":
                    doLogging = Boolean.parseBoolean(splitArgument[1]);
                default:
                    break;
            }
        }

        ConnectionHandler handler = null;
        try {
            handler = new ConnectionHandler(port, doLogging);
        } catch (IOException e) {
            Logger.log(e.getMessage(), LogLevel.Debug);
        }
        Thread connectionHandlerThread = new Thread(handler);
        connectionHandlerThread.start();
    }

    public static ActivityManager getActivityManager() {
        return activityManager;
    }
}
