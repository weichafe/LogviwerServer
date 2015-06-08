package com.larrainvial.logviwer.event;

import com.larrainvial.trading.emp.Event;
import java.net.Socket;

public class SendDatatoClientEvent extends Event {

    public Socket socket;

    public SendDatatoClientEvent(Object source, Socket socket) {
        super(source);
        this.socket = socket;
    }
}
