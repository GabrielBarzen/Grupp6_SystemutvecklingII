package server_v2;

import server_v2.logging.LogLevel;
import server_v2.logging.Logger;

import java.io.IOException;

public class ServerMain {

    private static int port = 4343;
    private static boolean doLogging = false;

    public static void main(String[] args) {
        for (String argument : args) {
            String[] splitArgument = argument.split(":");
            switch (splitArgument[0]) {
                case "port":
                    port = Integer.parseInt(splitArgument[1]);
                    break;
                case "log":
                    doLogging = true;
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
}
