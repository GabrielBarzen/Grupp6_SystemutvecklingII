package com.grp6.edim.server;

import com.grp6.edim.shared.*;

import com.grp6.edim.server.API.MessageController;
import com.grp6.edim.server.API.Receiver;
import com.grp6.edim.server.API.Sender;
import com.grp6.edim.server.logging.LogLevel;
import com.grp6.edim.server.logging.Logger;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class CommunicationController {

    ThreadPoolExecutor threadPoolExecutor;
    HashMap<User, MessageController> connectionMap;
    CommunicationController communicationController = this;
    Buffer<Message> receiveBuffer = new Buffer<>();

    public CommunicationController() {
        threadPoolExecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        connectionMap  = new HashMap<>();
        Thread thread = new Thread(new receiver_handler());
        thread.start();
    }

    public void sendObject(User user, Message message) {
        connectionMap.get(user).getSender().send(message);
        System.out.println(message.getType() + " sent");
    }

    public void receiveMessage(Message message) {
        receiveBuffer.put(message);
        System.out.println(message.getType() + " received");
    }

    public Object handleMessage(Message message) {
        System.out.println("what server");
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
                Message outgoingMessage = new Message(ServerMain.getActivityManager().getRandomActivity(),null, MessageType.NewActivity);
                System.out.println(message.getUser().getUsername());
                sendObject(message.getUser(),outgoingMessage);
                System.out.println(message.getType());
            }
            break;

            case SaveActivity : {
                Logger.log("save activity requested", LogLevel.Debug);
                if (message.getData() instanceof Activity)
                ServerMain.getActivityManager().saveActivity((Activity) message.getData());
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

    class receiver_handler implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    handleMessage(receiveBuffer.get());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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

                OutputStream os = clientSocket.getOutputStream();
                InputStream is = clientSocket.getInputStream();

                System.out.println("här");
                ObjectOutputStream oos = new ObjectOutputStream(os);
                ObjectInputStream ois = new ObjectInputStream(is);

                Object obj = ois.readObject();

                if (obj instanceof Message) {
                    Message message = (Message) obj;
                    if (message.getType().equals(MessageType.Login)) {
                        User user = message.getUser();

                        System.out.println("wassaaaaaaa");

                        reciever = new Receiver(communicationController, ois);
                        sender = new Sender(communicationController, oos);

                        reciever.start();
                        sender.start();

                        System.out.println(user.getUsername());
                        MessageController messageController = new MessageController(reciever, sender);
                        connectionMap.put(user, messageController);
                        //User user1 = new User("Isak");
                        MessageController messageController1 = connectionMap.get(user);

                        Message outgoingMessage = new Message(null,user,MessageType.Login);
                        //sender.send(outgoingMessage);
                        messageController1.getSender().send(outgoingMessage);
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
