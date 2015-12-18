package com.larrainvial.logviewer.listener.client;

import com.larrainvial.logviewer.event.client.SendMessageMKDADREvent;
import com.larrainvial.logviewer.event.client.SendMessageRoutingADREvent;
import com.larrainvial.trading.emp.Event;
import com.larrainvial.trading.emp.Listener;
import quickfix.Session;
import quickfix.SessionID;


public class SendMessageMKDADRListener implements Listener {

    public SessionID sessionID;

    public SendMessageMKDADRListener(SessionID sessionID){
        this.sessionID = sessionID;
    }

    @Override
    public synchronized void eventOccurred(Event event){

        try {

            SendMessageMKDADREvent ev = (SendMessageMKDADREvent) event;

            Session.sendToTarget(ev.message, sessionID.getSenderCompID(), sessionID.getTargetCompID());

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
