package com.larrainvial.logviewer.bo;

import com.larrainvial.logviewer.Repository;
import com.larrainvial.logviewer.event.readlog.*;
import com.larrainvial.logviewer.event.stringtofix.*;
import com.larrainvial.logviewer.listener.readlog.*;
import com.larrainvial.logviewer.listener.stringtofix.*;
import com.larrainvial.logviewer.vo.ModelXml;
import com.larrainvial.trading.emp.Controller;
import org.w3c.dom.Element;
import quickfix.fix44.Message;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class Algo {

    public File fileMkdDolar;
    public File fileMkdLocal;
    public File fileMkdAdr;
    public File fileRoutingLocal;
    public File fileRoutingAdr;

    public FileInputStream inputStreamDolar;
    public FileInputStream inputStreamMkdLocal;
    public FileInputStream inputStreamMkdAdr;
    public FileInputStream inputStreamRoutingLocal;
    public FileInputStream inputStreamRoutingAdr;

    public String nameAlgo;

    public Map<String, Message> messageFixRoutingLOCAL = Collections.synchronizedMap(new LinkedHashMap<String, Message>());
    public Map<String, Message> messageFixRoutingADR = Collections.synchronizedMap(new LinkedHashMap<String, Message>());

    public ReadFromDolarListener readFromDolarListener;
    public ReadLogMkdAdrListener readLogMkdAdrListener;
    public ReadLogMkdLocalListener readLogMkdLocalListener;
    public ReadlogRoutingAdrListener readlogRoutingAdrListener;
    public ReadLogRoutingLocalListener readLogRoutingLocalListener;

    public DolarListener dolarListener;
    public MarketDataAdrListener marketDataAdrListener;
    public MarketDataLocalListener marketDataLocalListener;
    public RoutingAdrListener routingAdrListener;
    public RoutingLocalListener routingLocalListener;

    public TimerTask timerTask;
    public ModelXml modelXml;

    public Algo(Element elem){

        try {

            modelXml = new ModelXml();
            readXML(elem);
            fileReader(modelXml);

            nameAlgo = modelXml.nameAlgo;

            readFromDolarListener = new ReadFromDolarListener(this);
            readLogMkdAdrListener = new ReadLogMkdAdrListener(this);
            readLogMkdLocalListener = new ReadLogMkdLocalListener(this);
            readlogRoutingAdrListener = new ReadlogRoutingAdrListener(this);
            readLogRoutingLocalListener = new ReadLogRoutingLocalListener(this);

            dolarListener =  new DolarListener(this);
            marketDataAdrListener = new MarketDataAdrListener(this);
            marketDataLocalListener = new MarketDataLocalListener(this);
            routingAdrListener = new RoutingAdrListener(this);
            routingLocalListener = new RoutingLocalListener(this);

            Controller.addEventListener(ReadFromDolarEvent.class, readFromDolarListener);
            Controller.addEventListener(ReadLogMkdAdrEvent.class, readLogMkdAdrListener);
            Controller.addEventListener(ReadLogMkdLocalEvent.class, readLogMkdLocalListener);
            Controller.addEventListener(ReadlogRoutingAdrEvent.class, readlogRoutingAdrListener);
            Controller.addEventListener(ReadLogRoutingLocalEvent.class, readLogRoutingLocalListener);

            Controller.addEventListener(DolarEvent.class, dolarListener);
            Controller.addEventListener(MarketDataADREvent.class, marketDataAdrListener);
            Controller.addEventListener(MarketDataLocalEvent.class, marketDataLocalListener);
            Controller.addEventListener(RoutingAdrEvent.class, routingAdrListener);
            Controller.addEventListener(RoutingLocalEvent.class, routingLocalListener);

            Repository.strategy.put(modelXml.nameAlgo, this);

            start(modelXml);

        } catch (Exception ex){
            ex.printStackTrace();
        }


    }

    public void start(final ModelXml xmlVO) throws Exception {

        timerTask = new TimerTask(){

            public void run() {

                Algo algo = Repository.strategy.get(xmlVO.nameAlgo);

                if (xmlVO.booleanDolar) {
                    Controller.dispatchEvent(new ReadFromDolarEvent(algo));
                }

                if (xmlVO.booleanMAdr) {
                    //Controller.dispatchEvent(new ReadLogMkdAdrEvent(algo));
                }

                if (xmlVO.booleanMLocal) {
                    //Controller.dispatchEvent(new ReadLogMkdLocalEvent(algo));
                }

                if (xmlVO.booleanRLocal) {
                    //Controller.dispatchEvent(new ReadLogRoutingLocalEvent(algo));
                }

                if (xmlVO.booleanRAdr) {
                    //Controller.dispatchEvent(new ReadlogRoutingAdrEvent(algo));
                }
            }

        };

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 1, 1);

    }

    public void fileReader(ModelXml xmlVO) throws Exception {

        if (xmlVO.booleanDolar) {
            inputStreamDolar = new FileInputStream(fileMkdDolar);
        }

        if (xmlVO.booleanMLocal) {
            inputStreamMkdLocal = new FileInputStream(fileMkdLocal);
        }

        if (xmlVO.booleanMAdr) {
            inputStreamMkdAdr = new FileInputStream(fileMkdAdr);
        }

        if (xmlVO.booleanRLocal) {
            inputStreamRoutingLocal = new FileInputStream(fileRoutingLocal);
        }

        if (xmlVO.booleanRAdr) {
            inputStreamRoutingAdr = new FileInputStream(fileRoutingAdr);
        }

    }



    public void readXML(Element elem){

        modelXml.location = elem.getElementsByTagName("location").item(0).getChildNodes().item(0).getNodeValue();
        modelXml.mkd_dolar = elem.getElementsByTagName("mkd_dolar").item(0).getChildNodes().item(0).getNodeValue();
        modelXml.mkd_nyse = elem.getElementsByTagName("mkd_nyse").item(0).getChildNodes().item(0).getNodeValue();
        modelXml.mkd_local = elem.getElementsByTagName("mkd_local").item(0).getChildNodes().item(0).getNodeValue();
        modelXml.routing_local = elem.getElementsByTagName("routing_local").item(0).getChildNodes().item(0).getNodeValue();
        modelXml.routing_nyse = elem.getElementsByTagName("routing_nyse").item(0).getChildNodes().item(0).getNodeValue();

        modelXml.nameAlgo = elem.getElementsByTagName("nameAlgo").item(0).getChildNodes().item(0).getNodeValue();
        modelXml.mkdDolar = elem.getElementsByTagName("mkdDolar").item(0).getChildNodes().item(0).getNodeValue();
        modelXml.mkdLocal = elem.getElementsByTagName("mkdLocal").item(0).getChildNodes().item(0).getNodeValue();
        modelXml.mkdAdr = elem.getElementsByTagName("mkdAdr").item(0).getChildNodes().item(0).getNodeValue();
        modelXml.routingLocal = elem.getElementsByTagName("routingLocal").item(0).getChildNodes().item(0).getNodeValue();
        modelXml.routingAdr = elem.getElementsByTagName("routingAdr").item(0).getChildNodes().item(0).getNodeValue();
        modelXml.time = Integer.valueOf(elem.getElementsByTagName("time").item(0).getChildNodes().item(0).getNodeValue());

        modelXml.booleanDolar = Boolean.valueOf(elem.getElementsByTagName("booleanDolar").item(0).getChildNodes().item(0).getNodeValue());
        modelXml.booleanMLocal = Boolean.valueOf(elem.getElementsByTagName("booleanMLocal").item(0).getChildNodes().item(0).getNodeValue());
        modelXml.booleanMAdr = Boolean.valueOf(elem.getElementsByTagName("booleanMAdr").item(0).getChildNodes().item(0).getNodeValue());
        modelXml.booleanRLocal = Boolean.valueOf(elem.getElementsByTagName("booleanRLocal").item(0).getChildNodes().item(0).getNodeValue());
        modelXml.booleanRAdr = Boolean.valueOf(elem.getElementsByTagName("booleanRAdr").item(0).getChildNodes().item(0).getNodeValue());

        fileMkdDolar = new File(modelXml.location + modelXml.mkd_dolar + Repository.year + ".log");
        fileMkdLocal = new File(modelXml.location + modelXml.mkd_local + Repository.year + ".log");
        fileMkdAdr = new File(modelXml.location + modelXml.mkd_nyse + Repository.year + ".log");
        fileRoutingLocal = new File(modelXml.location + modelXml.routing_local + Repository.year + ".log");
        fileRoutingAdr = new File(modelXml.location + modelXml.routing_nyse + Repository.year + ".log");

    }
}
