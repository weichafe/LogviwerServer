package com.larrainvial.report.event;

import com.larrainvial.report.Algo;
import com.larrainvial.trading.emp.Event;
import java.io.FileInputStream;


public class ReadMarketDataLocalEvent  extends Event {

    public Algo algo;
    public String filter;

    public ReadMarketDataLocalEvent(Object source, Algo algo, String filter) {
        super(source);
        this.algo = algo;
        this.filter = filter;
    }
}
