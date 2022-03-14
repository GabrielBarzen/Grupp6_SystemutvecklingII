package com.grp6.edim.client.controller;

import com.grp6.edim.client.controller.Receiver;
import com.grp6.edim.client.controller.Sender;

public class Communication {

    private Receiver receiver;
    private Sender sender;

    public Communication(Receiver receiver, Sender sender) {
        this.receiver = receiver;
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
