package com.larrainvial.report.event;

import com.larrainvial.report.Algo;
import com.larrainvial.trading.emp.Event;


public class ReadRoutingLocalEvent extends Event {

    public Algo algo;
    public String filter;

    public ReadRoutingLocalEvent(Object source, Algo algo, String filter) {
        super(source);
        this.algo = algo;
        this.filter = filter;
    }
}
