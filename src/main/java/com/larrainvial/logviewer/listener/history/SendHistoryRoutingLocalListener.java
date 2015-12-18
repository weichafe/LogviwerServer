package com.larrainvial.logviewer.listener.history;

import com.larrainvial.logviewer.Repository;
import com.larrainvial.logviewer.event.history.SendHistoryRoutingLocalEvent;
import com.larrainvial.trading.emp.Event;
import com.larrainvial.trading.emp.Listener;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.fix44.Message;


public class SendHistoryRoutingLocalListener implements Listener {

    public SessionID sessionID;

    public SendHistoryRoutingLocalListener(SessionID sessionID){
        this.sessionID = sessionID;
    }

    @Override
    public void eventOccurred(Event event) {

        try {

            SendHistoryRoutingLocalEvent ev = (SendHistoryRoutingLocalEvent)event;

            if (!sessionID.equals(ev.sessionID)) return;

            for (String strategy : Repository.strategy.keySet()) {

                for (String typeMarket : Repository.strategy.get(strategy).messageFixRoutingLOCAL.keySet()) {

                    Message msg = Repository.strategy.get(strategy).messageFixRoutingLOCAL.get(typeMarket);

                    Session.sendToTarget(msg, sessionID.getSenderCompID(), sessionID.getTargetCompID());

                }

            }


        } catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
