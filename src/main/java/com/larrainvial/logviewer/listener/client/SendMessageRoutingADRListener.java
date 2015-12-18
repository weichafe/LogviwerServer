package com.larrainvial.logviewer.listener.client;

import com.larrainvial.logviewer.event.client.SendMessageRoutingADREvent;
import com.larrainvial.trading.emp.Event;
import com.larrainvial.trading.emp.Listener;
import quickfix.Session;
import quickfix.SessionID;


public class SendMessageRoutingADRListener implements Listener {

    public SessionID sessionID;

    public SendMessageRoutingADRListener(SessionID sessionID){
        this.sessionID = sessionID;
    }

    @Override
    public synchronized void eventOccurred(Event event){

        try {

            SendMessageRoutingADREvent ev = (SendMessageRoutingADREvent) event;

            Session.sendToTarget(ev.message, sessionID.getSenderCompID(), sessionID.getTargetCompID());

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
