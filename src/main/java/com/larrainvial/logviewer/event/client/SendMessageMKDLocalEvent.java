package com.larrainvial.logviewer.event.client;

import com.larrainvial.logviewer.bo.Algo;
import com.larrainvial.trading.emp.Event;
import quickfix.fix44.Message;

public class SendMessageMKDLocalEvent extends Event {

    public Message message;
    public Algo algo;

    public SendMessageMKDLocalEvent(Object object, Algo algo, Message message) {
        super(object);
        this.message = message;
        this.algo = algo;
    }
}
