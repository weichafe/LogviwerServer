package com.larrainvial.logviewer.listener.history;

import com.larrainvial.logviewer.Repository;
import com.larrainvial.logviewer.event.history.SendHistoryRoutingADREvent;
import com.larrainvial.logviewer.event.history.SendStrategyEvent;
import com.larrainvial.trading.emp.Event;
import com.larrainvial.trading.emp.Listener;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.field.ExecType;
import quickfix.fix44.Message;


public class SendHistoryRoutingADRListener implements Listener {

    public SessionID sessionID;

    public SendHistoryRoutingADRListener(SessionID sessionID){
        this.sessionID = sessionID;
    }

    @Override
    public void eventOccurred(Event event) {

        try {

            SendHistoryRoutingADREvent ev = (SendHistoryRoutingADREvent)event;

            if (!sessionID.equals(ev.sessionID)) return;

            for (String strategy : Repository.strategy.keySet()) {

                for (String typeMarket : Repository.strategy.get(strategy).messageFixRoutingADR.keySet()) {

                    Message msg = Repository.strategy.get(strategy).messageFixRoutingADR.get(typeMarket);

                    Session.sendToTarget(msg, sessionID.getSenderCompID(), sessionID.getTargetCompID());

                }

            }


        } catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
