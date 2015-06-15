package com.larrainvial.logviwer.event.senddata;


import com.larrainvial.logviwer.model.ModelMarketData;
import com.larrainvial.trading.emp.Event;

public class SendMkdAdrEvent extends Event {

    public String nameAlgo;
    public String typeMarket;
    public ModelMarketData modelMarketData;

    public SendMkdAdrEvent(Object source, String nameAlgo, String typeMarket, ModelMarketData modelMarketData) {

        super(source);
        this.typeMarket = typeMarket;
        this.nameAlgo = nameAlgo;
        this.modelMarketData = modelMarketData;
    }
}
