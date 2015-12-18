package com.larrainvial.logviewer.event.stringtofix;

import com.larrainvial.logviewer.bo.Algo;
import com.larrainvial.trading.emp.Event;

public class RoutingLocalEvent extends Event {

    public Algo algo;
    public String lineFromLog;

    public RoutingLocalEvent(Object source, String lineFromLog) {
        super(source);
        this.algo = (Algo) source;
        this.lineFromLog = lineFromLog;
    }

}
