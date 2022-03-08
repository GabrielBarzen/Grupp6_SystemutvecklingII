package server_v2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionHandler implements Runnable {

    private int port;
    private boolean logging;

    private ServerSocket connectionSocket;
    private CommunicationController communicationController;

    private boolean isRunning = true;

    public ConnectionHandler(int port, boolean doLogging) throws IOException {
        this.port = port;
        this.logging = doLogging;
        this.connectionSocket = new ServerSocket(port);
        this.communicationController = new CommunicationController();
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                Socket socket = connectionSocket.accept();
                communicationController.connectionReceived(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void stop() {
        isRunning = false;
    }

}
