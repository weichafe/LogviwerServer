package com.larrainvial.logviwer.listener.data;

import com.larrainvial.logviwer.Algo;
import com.larrainvial.logviwer.Repository;
import com.larrainvial.logviwer.event.SendToViewEvent;
import com.larrainvial.logviwer.event.StringToFixMessageEvent;
import com.larrainvial.logviwer.model.ModelMarketData;
import com.larrainvial.logviwer.model.ModelRoutingData;
import com.larrainvial.logviwer.utils.CalculatePositions;
import com.larrainvial.logviwer.utils.StringToMarketData;
import com.larrainvial.logviwer.utils.StringToRoutingData;
import com.larrainvial.trading.emp.Controller;
import com.larrainvial.trading.emp.Event;
import com.larrainvial.trading.emp.Listener;

public class StringToFixMessageListener implements Listener {

    private ModelRoutingData modelRoutingData;
    private ModelMarketData modelMarketData;
    private Algo algo;

    @Override
    public void eventOccurred(Event event) {

        try {

            StringToFixMessageEvent ev = (StringToFixMessageEvent) event;

            if (ev.lineFromLog.equals("")) return;

            algo = Repository.strategy.get(ev.nameAlgo);

            if (ev.typeMarket.startsWith("ROUTING")) {

                StringToRoutingData stringToRoutingData = new StringToRoutingData();

                modelRoutingData = stringToRoutingData.routing(ev.lineFromLog);

                Controller.dispatchEvent(new SendToViewEvent(this, ev.nameAlgo, ev.typeMarket, modelRoutingData));

                if (modelRoutingData.execType.equals("Trade")) {
                    new CalculatePositions(algo, modelRoutingData);

                }

            } else {

                StringToMarketData stringToMarketData = new StringToMarketData();
                modelMarketData = stringToMarketData.marketData(ev.lineFromLog);

                Controller.dispatchEvent(new SendToViewEvent(this, ev.nameAlgo, ev.typeMarket, modelMarketData));

            }



        } catch (Exception e){
            e.printStackTrace();
        }

    }

}