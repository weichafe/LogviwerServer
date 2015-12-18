package com.larrainvial.logviewer.listener.client;

import com.larrainvial.logviewer.event.client.SendMessageDolarEvent;
import com.larrainvial.logviewer.event.client.SendMessageRoutingADREvent;
import com.larrainvial.trading.emp.Event;
import com.larrainvial.trading.emp.Listener;
import quickfix.Session;
import quickfix.SessionID;


public class SendMessageDolarListener implements Listener {

    public SessionID sessionID;

    public SendMessageDolarListener(SessionID sessionID){
        this.sessionID = sessionID;
    }

    @Override
    public synchronized void eventOccurred(Event event){

        try {

            SendMessageDolarEvent ev = (SendMessageDolarEvent) event;

            Session.sendToTarget(ev.message, sessionID.getSenderCompID(), sessionID.getTargetCompID());

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
