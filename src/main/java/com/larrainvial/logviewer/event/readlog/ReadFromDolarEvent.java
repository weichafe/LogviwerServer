package com.larrainvial.logviewer.event.readlog;

import com.larrainvial.logviewer.bo.Algo;
import com.larrainvial.trading.emp.Event;

public class ReadFromDolarEvent extends Event {

    public Algo algo;

    public ReadFromDolarEvent(Algo algo) {
        super(algo);
        this.algo = algo;
    }
}
