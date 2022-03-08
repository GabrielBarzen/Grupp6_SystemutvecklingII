package server_v2;

import server_v2.API.MessageController;
import server_v2.API.Receiver;
import server_v2.API.Sender;
import server_v2.logging.LogLevel;
import server_v2.logging.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class CommunicationController {

    ThreadPoolExecutor threadPoolExecutor;
    Map<User, MessageController> connectionMap;
    CommunicationController communicationController = this;

    public CommunicationController() {
        threadPoolExecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        connectionMap  = new HashMap<>();
    }

    public void sendObject(User user, Message message) {
        connectionMap.get(user).getSender().send(message);
    }

    public Object receiveMessage(Message message) {
        switch (message.getType()) {


            case Login : {
                Logger.log("illegal login", LogLevel.Warning);
            }
            break;

            case Logout : {
                Logger.log("logout", LogLevel.Debug);
            } //TODO login with disconnect
            break;

            case NewActivity : {
                Logger.log("new activity requested", LogLevel.Debug);
                Message outgoingMessage = new Message(ActivityManager.getRandomActivity(),null,MessageType.OK);
                sendObject(message.getUser(),outgoingMessage);
            } //TODO send new activity to client
            break;

            case SaveActivity : {
                Logger.log("save activity requested", LogLevel.Debug);
            } //TODO save activity to file
            break;

            case OK : {
                Logger.log("message received on client", LogLevel.Debug);
            } //TODO handle OK message
            break;

            case Error : {
                Logger.log("error on client side", LogLevel.Debug);
            } //TODO handle Error message
            break;
        }
        return null;
    }

    public void connectionReceived(Socket socket) {
        establish_communication_runner runner = new establish_communication_runner(socket);
        threadPoolExecutor.execute(runner);
    }

    class establish_communication_runner implements Runnable {

        Receiver reciever;
        Sender sender;

        Socket clientSocket;

        establish_communication_runner(Socket socket) {
            clientSocket = socket;
        }

        @Override
        public void run() {
            try {
                ObjectInputStream stream = (ObjectInputStream) clientSocket.getInputStream();
                Object obj = stream.readObject();

                if (obj instanceof Message) {
                    Message message = (Message) obj;
                    if (message.getType().equals(MessageType.Login)) {
                        User user = message.getUser();

                        reciever = new Receiver(communicationController, new ObjectInputStream(clientSocket.getInputStream()));
                        sender = new Sender(communicationController, new ObjectOutputStream(clientSocket.getOutputStream()));

                        reciever.start();
                        sender.start();

                        connectionMap.put(user, new MessageController(reciever, sender));
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
