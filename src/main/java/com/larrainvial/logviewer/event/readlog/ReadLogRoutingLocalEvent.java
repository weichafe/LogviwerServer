package com.larrainvial.logviewer.event.readlog;

import com.larrainvial.logviewer.bo.Algo;
import com.larrainvial.trading.emp.Event;

public class ReadLogRoutingLocalEvent extends Event {

    public Algo algo;

    public ReadLogRoutingLocalEvent(Object source) {
        super(source);
        this.algo = (Algo) source;
    }
}
