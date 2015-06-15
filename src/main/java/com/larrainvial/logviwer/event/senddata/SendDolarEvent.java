package com.larrainvial.logviwer.event.senddata;


import com.larrainvial.logviwer.model.ModelMarketData;
import com.larrainvial.logviwer.model.ModelRoutingData;
import com.larrainvial.trading.emp.Event;

public class SendDolarEvent extends Event {

    public String nameAlgo;
    public String typeMarket;
    public ModelMarketData modelMarketData;

    public SendDolarEvent(Object source, String nameAlgo, String typeMarket, ModelMarketData modelMarketData) {
        super(source);
        this.typeMarket = typeMarket;
        this.nameAlgo = nameAlgo;
        this.modelMarketData = modelMarketData;

    }

}
