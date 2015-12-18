package com.larrainvial.logviewer.event.history;

import com.larrainvial.trading.emp.Event;
import quickfix.SessionID;


public class SendHistoryRoutingADREvent extends Event {

    public SessionID sessionID;

    public SendHistoryRoutingADREvent(Object source, SessionID sessionID) {
        super(source);
        this.sessionID = sessionID;
    }
}
