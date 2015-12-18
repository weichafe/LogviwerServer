package com.larrainvial.logviewer.listener.client;

import com.larrainvial.logviewer.event.client.SendMessageMKDLocalEvent;
import com.larrainvial.logviewer.event.client.SendMessageRoutingADREvent;
import com.larrainvial.trading.emp.Event;
import com.larrainvial.trading.emp.Listener;
import quickfix.Session;
import quickfix.SessionID;


public class SendMessageMKDLocalListener implements Listener {

    public SessionID sessionID;

    public SendMessageMKDLocalListener(SessionID sessionID){
        this.sessionID = sessionID;
    }

    @Override
    public synchronized void eventOccurred(Event event){

        try {

            SendMessageMKDLocalEvent ev = (SendMessageMKDLocalEvent) event;

            Session.sendToTarget(ev.message, sessionID.getSenderCompID(), sessionID.getTargetCompID());

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
