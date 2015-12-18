package com.larrainvial.logviewer.event.history;

import com.larrainvial.trading.emp.Event;
import quickfix.SessionID;


public class SendHistoryRoutingLocalEvent extends Event {

    public SessionID sessionID;

    public SendHistoryRoutingLocalEvent(Object source, SessionID sessionID) {
        super(source);
        this.sessionID = sessionID;
    }
}
