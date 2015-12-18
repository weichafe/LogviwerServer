package com.larrainvial.logviewer.adaptador;

import com.larrainvial.logviewer.event.client.*;
import com.larrainvial.logviewer.event.history.*;
import com.larrainvial.logviewer.listener.client.*;
import com.larrainvial.logviewer.listener.history.*;
import com.larrainvial.trading.emp.Controller;
import quickfix.*;
import quickfix.Message;
import quickfix.fix44.*;
import quickfix.fix44.MessageCracker;


public final class QuickFixAdapter extends MessageCracker implements Application {

    public SocketAcceptor socketAcceptor;
    private SessionSettings sessionSettings;

    public QuickFixAdapter(String quickFixIniFile) {

        try {

            sessionSettings = new SessionSettings(quickFixIniFile);

            FileStoreFactory fileStoreFactory = new FileStoreFactory(sessionSettings);
            FileLogFactory fileLogFactory = new FileLogFactory(sessionSettings);

            DefaultMessageFactory defaultMessageFactory = new DefaultMessageFactory();
            socketAcceptor = new SocketAcceptor(this, fileStoreFactory, sessionSettings, fileLogFactory, defaultMessageFactory);
            socketAcceptor.start();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void onCreate(SessionID sessionID) {


    }

    public void onLogon(SessionID sessionID) {


        Controller.addEventListener(SendMessageDolarEvent.class, new SendMessageDolarListener(sessionID));
        Controller.addEventListener(SendMessageMKDADREvent.class, new SendMessageMKDADRListener(sessionID));
        Controller.addEventListener(SendMessageMKDLocalEvent.class, new SendMessageMKDLocalListener(sessionID));
        Controller.addEventListener(SendMessageRoutingADREvent.class, new SendMessageRoutingADRListener(sessionID));
        Controller.addEventListener(SendMessageRoutingLocalEvent.class, new SendMessageRoutingLocalListener(sessionID));

        Controller.addEventListener(SendHistoryRoutingADREvent.class, new SendHistoryRoutingADRListener(sessionID));
        Controller.addEventListener(SendHistoryRoutingLocalEvent.class, new SendHistoryRoutingLocalListener(sessionID));
        Controller.addEventListener(SendStrategyEvent.class, new SendStrategyHistoryListener(sessionID));


        Controller.dispatchEvent(new SendStrategyEvent(this, sessionID));



    }

    public void onLogout(SessionID sessionID) {
    }

    public void toAdmin(Message message, SessionID sessionID) {

    }

    public void fromAdmin(Message message, SessionID sessionID) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon {

        try {
            crack(message, sessionID);
        } catch (UnsupportedMessageType unsupportedMessageType) {

        }
    }

    public void toApp(Message message, SessionID sessionID) throws DoNotSend {
        // nothing
    }

    public void fromApp(Message message, SessionID sessionID) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {

        try {
            crack(message, sessionID);
        } catch (UnsupportedMessageType unsupportedMessageType) {

        }
    }

    public void onMessage(ExecutionReport executionReport, SessionID sessionID) {

    }

    public void onMessage(OrderCancelReject orderCancelReject, SessionID sessionID) {

    }

    public void onMessage(NewOrderSingle newOrderSingle, SessionID sessionID) throws FieldNotFound {
    }

    public void onMessage(OrderCancelReplaceRequest orderCancelReplaceRequest, SessionID sessionID) throws FieldNotFound {
    }

    public void onMessage(OrderCancelRequest orderCancelRequest, SessionID sessionID) throws FieldNotFound {
    }


}

