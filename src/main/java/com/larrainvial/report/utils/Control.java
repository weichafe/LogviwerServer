package com.larrainvial.report.utils;


import com.larrainvial.report.event.*;
import com.larrainvial.report.listener.*;
import com.larrainvial.trading.emp.Controller;

public class Control {

    public static void initialize(){

        Controller.addEventListener(ReadMarketDataAdrEvent.class, new ReadMarketDataAdrListener());
        Controller.addEventListener(ReadMarketDataDolarEvent.class, new ReadMarketDataDolarListener());
        Controller.addEventListener(ReadMarketDataLocalEvent.class, new ReadMarketDataLocalListener());
        Controller.addEventListener(ReadRoutingAdrEvent.class, new ReadRoutingAdrListener());
        Controller.addEventListener(ReadRoutingLocalEvent.class, new ReadRoutingLocalListener());


    }
}
