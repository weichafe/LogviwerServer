package com.larrainvial.logviewer.event.history;


import com.larrainvial.trading.emp.Event;
import quickfix.SessionID;

public class SendStrategyEvent extends Event {

    public SessionID sessionID;

    public SendStrategyEvent(Object source, SessionID sessionID) {
        super(source);
        this.sessionID = sessionID;
    }

}
