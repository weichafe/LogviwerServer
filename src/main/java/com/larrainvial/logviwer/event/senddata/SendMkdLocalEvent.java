package com.larrainvial.logviwer.event.senddata;


import com.larrainvial.logviwer.model.ModelMarketData;
import com.larrainvial.trading.emp.Event;

public class SendMkdLocalEvent extends Event {

    public String nameAlgo;
    public String typeMarket;
    public ModelMarketData modelMarketData;

    public SendMkdLocalEvent(Object source, String nameAlgo, String typeMarket, ModelMarketData modelMarketData) {
        super(source);
        this.typeMarket = typeMarket;
        this.nameAlgo = nameAlgo;
        this.modelMarketData = modelMarketData;
    }
}
