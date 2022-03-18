package server;

import java.io.IOException;

/**
 * This class creates a server-object and starts the server.
 *
 * @author Sofia Hallberg.
 * @version 1.0
 */

public class StartServer {
    public static void main(String[] args) throws IOException {
        ServerController server = new ServerController(4343);
        server.start();
    }
}
