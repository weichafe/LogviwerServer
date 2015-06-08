package com.larrainvial.logviwer.utils;

import com.larrainvial.logviwer.Algo;
import com.larrainvial.logviwer.Repository;
import com.larrainvial.logviwer.event.*;
import com.larrainvial.logviwer.listener.data.ReadLogListener;
import com.larrainvial.logviwer.listener.data.SendToViewListener;
import com.larrainvial.logviwer.listener.data.StringToFixMessageListener;
import com.larrainvial.logviwer.listener.data.TriggerReadFileListener;
import com.larrainvial.logviwer.listener.server.NewClienteListener;
import com.larrainvial.logviwer.listener.server.SendDataToClientListener;
import com.larrainvial.logviwer.listener.server.ServerListener;
import com.larrainvial.trading.emp.Controller;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Control {

    public static void initialize(){

        Controller.addEventListener(StringToFixMessageEvent.class, new StringToFixMessageListener());
        Controller.addEventListener(SendToViewEvent.class, new SendToViewListener());
        Controller.addEventListener(ReadLogEvent.class, new ReadLogListener());
        Controller.addEventListener(TriggerReadFileEvent.class, new TriggerReadFileListener());
        Controller.addEventListener(NewClientEvent.class, new NewClienteListener());
        Controller.addEventListener(ServerEvent.class, new ServerListener());
        Controller.addEventListener(SendDatatoClientEvent.class, new SendDataToClientListener());


    }


    public  static void initializaAll() throws InterruptedException {

        initializeAdrArbitrageXSGO();

    }


    private static void initializeAdrArbitrageXSGO(){

        try {

            Algo algo = new Algo();

            algo.setNameAlgo("ADRArbitrage XSGO");
            algo.setMkd_dolar("MKD_DOLAR");
            algo.setMkd_adr("MKD_NYSE");
            algo.setMkd_local("MKD_XSGO");
            algo.setRouting_adr("ROUTING_ADR");
            algo.setRouting_local("ROUTING_LOCAL");

            Date fechaActual = new Date();

            DateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd");
            String year = formatoFecha.format(fechaActual).replace("/", "");

            //String location = "W:\\ADRArbitrageXSGOIB\\log\\";
            String location = "src\\main\\resources\\log\\AdrArbitrageIB\\";
            String mkd_dolar = "FIX.4.4-ALGOARBADR5-MDFHBLP.messages_";
            String mkd_nyse = "FIX.4.4-ARBv3_EQUITY_NYS_BCS-MAMA_NYSE.messages_";
            String mkd_local = "FIX.4.4-MKDATACL2-MKDATAFHBCS2.messages_";
            String routing_local = "FIX.4.4-ARDARB_XSGO_IB-AMGTOBCS.messages_";
            String routing_nyse = "FIX.4.4-LVBSG-ADR_ARBITRAGE_IB_XNYS.messages_";
            String log = ".log";

            algo.setFile_mkd_dolar(new File(location + mkd_dolar + year + log));
            algo.setFile_mkd_local(new File(location + mkd_local + year + log));
            algo.setFile_mkd_adr(new File(location + mkd_nyse + year + log));
            algo.setFile_routing_local(new File(location + routing_local + year + log));
            algo.setFile_routing_adr(new File(location + routing_nyse + year + log));

            algo.fileReader();

            Repository.strategy.put(algo.getNameAlgo(), algo);

            algo.iniziale();


        } catch (Exception e){
            e.printStackTrace();
        }
    }







}