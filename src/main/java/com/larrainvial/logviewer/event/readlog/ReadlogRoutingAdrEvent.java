package com.larrainvial.logviewer.event.readlog;

import com.larrainvial.logviewer.bo.Algo;
import com.larrainvial.trading.emp.Event;

public class ReadlogRoutingAdrEvent extends Event {

    public Algo algo;

    public ReadlogRoutingAdrEvent(Object source) {
        super(source);
        this.algo = (Algo) source;
    }
}
