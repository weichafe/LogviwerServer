package com.larrainvial.logviewer.event.readlog;

import com.larrainvial.logviewer.bo.Algo;
import com.larrainvial.trading.emp.Event;

public class ReadLogMkdAdrEvent extends Event {

    public Algo algo;

    public ReadLogMkdAdrEvent(Object source) {
        super(source);
        this.algo = (Algo) source;
    }
}
