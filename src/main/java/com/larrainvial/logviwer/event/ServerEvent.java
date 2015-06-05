package com.larrainvial.logviwer.event;

import com.larrainvial.trading.emp.Event;

import java.net.Socket;


public class ServerEvent extends Event {

    public Socket socket;
    public int idSession;

    public ServerEvent(Object source, Socket socket, int idSession) {
        super(source);
        this.socket = socket;
        this.idSession = idSession;
    }
}
