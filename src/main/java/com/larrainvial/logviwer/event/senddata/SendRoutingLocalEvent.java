package com.larrainvial.logviwer.event.senddata;


import com.larrainvial.logviwer.model.ModelMarketData;
import com.larrainvial.logviwer.model.ModelRoutingData;
import com.larrainvial.trading.emp.Event;

public class SendRoutingLocalEvent extends Event {

    public String nameAlgo;
    public String typeMarket;
    public ModelMarketData modelMarketData;
    public ModelRoutingData modelRoutingData;


    public SendRoutingLocalEvent(Object source, String nameAlgo, String typeMarket, ModelRoutingData modelRoutingData) {
        super(source);
        this.typeMarket = typeMarket;
        this.nameAlgo = nameAlgo;
        this.modelRoutingData = modelRoutingData;
    }
}
