package com.larrainvial.logviwer.listener.data;

import com.larrainvial.logviwer.Algo;
import com.larrainvial.logviwer.Repository;
import com.larrainvial.logviwer.event.SendToViewEvent;
import com.larrainvial.logviwer.event.senddata.*;
import com.larrainvial.logviwer.model.ModelPositions;
import com.larrainvial.logviwer.utils.Helper;
import com.larrainvial.trading.emp.Controller;
import com.larrainvial.trading.emp.Event;
import com.larrainvial.trading.emp.Listener;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;

public class SendToViewListener implements Listener {

    @Override
    public synchronized void eventOccurred(Event event) {

        SendToViewEvent ev = (SendToViewEvent) event;

        try {

            Algo algo = Repository.strategy.get(ev.nameAlgo);

            if (ev.typeMarket.equals(algo.strategyDataVO.mkd_dolar)) {
                Controller.dispatchEvent(new SendDolarEvent(this, ev.nameAlgo, ev.typeMarket, ev.modelMarketData));
            }

            if (ev.typeMarket.equals(algo.strategyDataVO.mkd_adr)) {
                Controller.dispatchEvent(new SendMkdAdrEvent(this, ev.nameAlgo, ev.typeMarket, ev.modelMarketData));
            }

            if (ev.typeMarket.equals(algo.strategyDataVO.mkd_local)) {
                Controller.dispatchEvent(new SendMkdLocalEvent(this, ev.nameAlgo, ev.typeMarket, ev.modelMarketData));
            }

            if (ev.typeMarket.equals(algo.strategyDataVO.routing_adr)) {
                Controller.dispatchEvent(new SendRoutingAdrEvent(this, ev.nameAlgo, ev.typeMarket, ev.modelRoutingData));
                Controller.dispatchEvent(new SendPositionEvent(this, ev.nameAlgo, ev.typeMarket, ev.modelRoutingData));
            }

            if (ev.typeMarket.equals(algo.strategyDataVO.routing_local)) {
                Controller.dispatchEvent(new SendRoutingLocalEvent(this, ev.nameAlgo, ev.typeMarket, ev.modelRoutingData));
                Controller.dispatchEvent(new SendPositionEvent(this, ev.nameAlgo, ev.typeMarket, ev.modelRoutingData));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}