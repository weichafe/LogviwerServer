package com.larrainvial.logviwer.event;


import com.larrainvial.trading.emp.Event;

import java.net.Socket;

public class ValidateClienteEvent extends Event {

    public Socket socket;
    public String users;
    public String password;

    public ValidateClienteEvent(Object source, Socket socket, String users, String password) {

        super(source);
        this.socket = socket;
        this.users = users;
        this.password = password;
    }
}
