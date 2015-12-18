package com.larrainvial.logviewer.listener.stringtofix;

import com.larrainvial.logviewer.Repository;
import com.larrainvial.logviewer.bo.Algo;
import com.larrainvial.logviewer.event.client.SendMessageDolarEvent;
import com.larrainvial.logviewer.event.client.SendMessageRoutingADREvent;
import com.larrainvial.logviewer.event.client.SendMessageRoutingLocalEvent;
import com.larrainvial.logviewer.event.stringtofix.RoutingLocalEvent;
import com.larrainvial.logviewer.utils.Constants;
import com.larrainvial.trading.emp.Controller;
import com.larrainvial.trading.emp.Event;
import com.larrainvial.trading.emp.Listener;
import com.larrainvial.trading.utils.IDGenerator;
import quickfix.DataDictionary;
import quickfix.DefaultMessageFactory;
import quickfix.MessageUtils;
import quickfix.field.MsgType;
import quickfix.field.SecondaryExecID;
import quickfix.field.SecondaryOrderID;
import quickfix.field.Text;
import quickfix.fix44.BusinessMessageReject;
import quickfix.fix44.Message;

public class RoutingLocalListener implements Listener {

    public Algo algo;
    public Message message;
    public DataDictionary dd;

    public RoutingLocalListener(Algo algo) {

        try {

            this.algo = algo;
            message = new Message();
            dd = new DataDictionary(Repository.FIX44);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public synchronized void eventOccurred(Event event) {

        try {

            RoutingLocalEvent ev = (RoutingLocalEvent) event;

            if (ev.lineFromLog.equals(Constants.EMPTY)) return;
            if (!ev.algo.nameAlgo.equals(algo.nameAlgo)) return;

            message = (Message) MessageUtils.parse(new DefaultMessageFactory(), dd, deleteDataforMessage(ev.lineFromLog));

            if (message.getHeader().getString(MsgType.FIELD).equals("0")) return;

            if (validateMessage(message)) {

                BusinessMessageReject businessMessageReject = new BusinessMessageReject();
                businessMessageReject.setString(SecondaryOrderID.FIELD, ev.algo.nameAlgo);
                businessMessageReject.setString(SecondaryExecID.FIELD, Constants.TypeMarket.ROUTING_LOCAL);

                businessMessageReject.setString(Text.FIELD, message.getHeader().getString(35) + "-" +
                        (message.isSetField(Text.FIELD) ? message.getString(Text.FIELD) : ""));

                algo.messageFixRoutingLOCAL.put(IDGenerator.getID(), businessMessageReject);
                Controller.dispatchEvent(new SendMessageRoutingLocalEvent(this, algo, businessMessageReject));

            } else {

                message.setString(SecondaryOrderID.FIELD, ev.algo.nameAlgo);
                message.setString(SecondaryExecID.FIELD, Constants.TypeMarket.ROUTING_LOCAL);

                algo.messageFixRoutingLOCAL.put(IDGenerator.getID(), message);
                Controller.dispatchEvent(new SendMessageRoutingLocalEvent(this, algo, message));

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public String deleteDataforMessage(String lineFromLog){
        return lineFromLog.split(": ")[1];
    }

    public Boolean validateMessage(Message message) throws Exception {
        return (message.getHeader().getString(MsgType.FIELD).equals("5") ||
                message.getHeader().getString(MsgType.FIELD).equals("4") ||
                message.getHeader().getString(MsgType.FIELD).equals("A") ||
                message.getHeader().getString(MsgType.FIELD).equals("3") ||
                message.getHeader().getString(MsgType.FIELD).equals("1")) ? true : false;
    }

}
