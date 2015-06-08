package com.larrainvial.logviwer.listener.server;

import com.larrainvial.logviwer.Algo;
import com.larrainvial.logviwer.Repository;
import com.larrainvial.logviwer.event.SendDatatoClientEvent;
import com.larrainvial.trading.emp.Event;
import com.larrainvial.trading.emp.Listener;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;
import java.io.Serializable;



public class SendDataToClientListener implements Listener {

    @Override
    public void eventOccurred(Event event) {

        SendDatatoClientEvent ev = (SendDatatoClientEvent) event;

        try {

            Socket socket = ev.socket;

            ObjectOutputStream ooStream = new ObjectOutputStream(socket.getOutputStream());
            ooStream.writeObject(Repository.strategy);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
