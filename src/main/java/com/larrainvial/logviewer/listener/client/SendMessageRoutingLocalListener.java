package com.larrainvial.logviewer.listener.client;

import com.larrainvial.logviewer.event.client.SendMessageRoutingLocalEvent;
import com.larrainvial.logviewer.event.history.SendHistoryRoutingLocalEvent;
import com.larrainvial.trading.emp.Event;
import com.larrainvial.trading.emp.Listener;
import quickfix.Session;
import quickfix.SessionID;


public class SendMessageRoutingLocalListener implements Listener {

    public SessionID sessionID;

    public SendMessageRoutingLocalListener(SessionID sessionID){
        this.sessionID = sessionID;
    }

    @Override
    public synchronized void eventOccurred(Event event){

        try {

            SendMessageRoutingLocalEvent ev = (SendMessageRoutingLocalEvent) event;

            Session.sendToTarget(ev.message, sessionID.getSenderCompID(), sessionID.getTargetCompID());

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
