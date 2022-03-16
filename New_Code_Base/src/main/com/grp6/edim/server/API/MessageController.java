package com.grp6.edim.server.API;

public class MessageController {

    private Receiver receiver;
    private Sender sender;

    public MessageController(Receiver reciever, Sender sender) {
        this.receiver = reciever;
        this.sender = sender;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }
}
