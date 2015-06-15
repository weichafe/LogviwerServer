package com.larrainvial.logviwer.utils;

import com.larrainvial.logviwer.Algo;
import com.larrainvial.logviwer.Repository;
import com.larrainvial.logviwer.event.*;
import com.larrainvial.logviwer.event.readlog.*;
import com.larrainvial.logviwer.event.senddata.*;
import com.larrainvial.logviwer.listener.data.readlog.*;
import com.larrainvial.logviwer.listener.data.SendToViewListener;
import com.larrainvial.logviwer.listener.data.StringToFixMessageListener;
import com.larrainvial.logviwer.listener.data.send.*;
import com.larrainvial.logviwer.listener.server.NewClienteListener;
import com.larrainvial.logviwer.listener.server.SendDataToClientListener;
import com.larrainvial.logviwer.listener.server.ServerListener;
import com.larrainvial.logviwer.listener.server.ValidateClienteListener;
import com.larrainvial.trading.emp.Controller;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Control {

    public static void initialize(){

        Controller.addEventListener(StringToFixMessageEvent.class, new StringToFixMessageListener());
        Controller.addEventListener(SendToViewEvent.class, new SendToViewListener());
        Controller.addEventListener(NewClientEvent.class, new NewClienteListener());
        Controller.addEventListener(ServerEvent.class, new ServerListener());
        Controller.addEventListener(SendDatatoClientEvent.class, new SendDataToClientListener());

        Controller.addEventListener(ReadLogMarketDataADREvent.class, new ReadLogMarketDataADRListener());
        Controller.addEventListener(ReadLogMarketDataDolarEvent.class, new ReadLogMarketDataDolarListener());
        Controller.addEventListener(ReadLogMarketDataLocalEvent.class, new ReadLogMarketDataLocalListener());
        Controller.addEventListener(ReadLogRoutingADREvent.class, new ReadLogRoutingADRListener());
        Controller.addEventListener(ReadLogRoutingLocalEvent.class, new ReadLogRoutingLocalListener());

        Controller.addEventListener(ValidateClienteEvent.class, new ValidateClienteListener());


        Controller.addEventListener(SendDolarEvent.class, new SendDolarListener());
        Controller.addEventListener(SendMkdAdrEvent.class, new SendMkdAdrListener());
        Controller.addEventListener(SendMkdLocalEvent.class, new SendMkdLocalListener());
        Controller.addEventListener(SendRoutingAdrEvent.class, new SendRoutingAdrListener());
        Controller.addEventListener(SendRoutingLocalEvent.class, new SendRoutingLocalListener());
        Controller.addEventListener(SendPositionEvent.class, new SendPositionListener());


    }


    public  static void initializaAll() throws InterruptedException {

        initializeAdrArbitrageXSGO();

    }


    private static void initializeAdrArbitrageXSGO(){

        try {

            Algo algo = new Algo();

            algo.strategyDataVO.nameAlgo = "ADRArbitrage XSGO";
            algo.strategyDataVO.mkd_dolar = "MKD_DOLAR";
            algo.strategyDataVO.mkd_adr = "MKD_NYSE";
            algo.strategyDataVO.mkd_local = "MKD_XSGO";
            algo.strategyDataVO.routing_adr = "ROUTING_ADR";
            algo.strategyDataVO.routing_local = "ROUTING_LOCAL";

            Date fechaActual = new Date();

            DateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd");
            String year = formatoFecha.format(fechaActual).replace("/", "");

            //String location = "W:\\ADRArbitrageXSGOIB\\log\\";
            String location = "src\\main\\resources\\log\\AdrArbitrageIB\\";
            String mkd_dolar = "FIX.4.4-MKDATACL2-MKDATAFHBCS2.messages_";
            String mkd_nyse = "FIX.4.4-ARBv3_EQUITY_NYS_BCS-MAMA_NYSE.messages_";
            String mkd_local = "FIX.4.4-MKDATACL2-MKDATAFHBCS2.messages_";
            String routing_local = "FIX.4.4-ARDARB_XSGO_IB-AMGTOBCS.messages_";
            String routing_nyse = "FIX.4.4-LVBSG-ADR_ARBITRAGE_IB_XNYS.messages_";
            String log = ".log";

            algo.file_mkd_dolar = new File(location + mkd_dolar + year + log);
            algo.file_mkd_local = new File(location + mkd_local + year + log);
            algo.file_mkd_adr = new File(location + mkd_nyse + year + log);
            algo.file_routing_local = new File(location + routing_local + year + log);
            algo.file_routing_adr = new File(location + routing_nyse + year + log);

            algo.fileReader();

            Repository.strategy.put(algo.strategyDataVO.nameAlgo, algo);

            algo.iniziale();


        } catch (Exception e){
            e.printStackTrace();
        }
    }







}