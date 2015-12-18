package com.larrainvial.logviewer.listener.history;


import com.larrainvial.logviewer.Repository;
import com.larrainvial.logviewer.event.history.SendHistoryRoutingADREvent;
import com.larrainvial.logviewer.event.history.SendHistoryRoutingLocalEvent;
import com.larrainvial.logviewer.event.history.SendStrategyEvent;
import com.larrainvial.trading.emp.Controller;
import com.larrainvial.trading.emp.Event;
import com.larrainvial.trading.emp.Listener;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.field.AllocReportID;
import quickfix.field.Text;
import quickfix.fix44.AllocationReportAck;

public class SendStrategyHistoryListener implements Listener {

    public SessionID sessionID;
    public AllocationReportAck allocationReportAck = new AllocationReportAck();

    public SendStrategyHistoryListener(SessionID sessionID){
        this.sessionID = sessionID;
    }

    @Override
    public void eventOccurred(Event event) {

        try {

            SendStrategyEvent ev = (SendStrategyEvent)event;

            if (!sessionID.equals(ev.sessionID)) return;

            for (String key : Repository.strategy.keySet()) {

                allocationReportAck.setString(Text.FIELD, Repository.strategy.get(key).nameAlgo);
                Session.sendToTarget(allocationReportAck, sessionID.getSenderCompID(), sessionID.getTargetCompID());
            }

            Controller.dispatchEvent(new SendHistoryRoutingADREvent(this, ev.sessionID));
            Controller.dispatchEvent(new SendHistoryRoutingLocalEvent(this, ev.sessionID));

        } catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
