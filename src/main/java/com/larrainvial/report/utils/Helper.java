package com.larrainvial.report.utils;

import com.javtech.javatoolkit.fix.FixConstants;
import com.javtech.javatoolkit.message.Attribute;
import com.larrainvial.report.Repository;
import com.larrainvial.report.vo.MarketDataVO;
import com.larrainvial.report.vo.ReporteVO;
import com.larrainvial.report.vo.RoutingVO;
import quickfix.field.MsgType;
import quickfix.fix44.Message;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

public class Helper {

    public static void setValuesFromMKD(){


        setCadIndex();
        System.out.println("fin set dolar CAD");

        setMKDDolarCof();
        System.out.println("fin set dolar COF");

        setMKDLocal();
        System.out.println("fin set MKD local");

        setMKDADR();
        System.out.println("fin set MKD ADR");

        setMRoutingLocal();
        System.out.println("fin set Routing Local");

        setMRoutingADR();
        System.out.println("fin set Routing ADR");

        Repository.printer = true;

    }

    public static void setCadIndex(){


        for (Map.Entry<Long, MarketDataVO> e: Repository.reportHashmapMkdDolarCad.entrySet()) {

                ReporteVO reporteVO = new ReporteVO();
                MarketDataVO marketDataVO = Repository.reportHashmapMkdDolarCad.get(e.getKey());

                reporteVO.time = marketDataVO.time;
                reporteVO.symbolDolarCAD = marketDataVO.symbol;
                reporteVO.messageByTypeDolarCAD = marketDataVO.messageByType;
                reporteVO.timeDolarCAD = marketDataVO.time;
                reporteVO.buyPxDolarCAD = marketDataVO.buyPx;
                reporteVO.buyQtyDolarCAD = marketDataVO.buyQty;
                reporteVO.sellPxDolarCAD = marketDataVO.sellPx;
                reporteVO.sellQtyDolarCAD = marketDataVO.sellQty;
                reporteVO.closePxDolarCAD = marketDataVO.closePx;

                Repository.reportHashmap.put(e.getKey(), reporteVO);

        }


        Repository.reportHashmapMkdDolarCad.clear();


    }

    public static void setMKDADR(){


        for (Map.Entry<Long, MarketDataVO> e: Repository.reportHashmapMkdADR.entrySet()) {

            if(Repository.reportHashmap.containsKey(e.getKey())){

                MarketDataVO marketDataVO = Repository.reportHashmapMkdADR.get(e.getKey());
                ReporteVO reporteVO = Repository.reportHashmap.get(e.getKey());

                reporteVO.symbolMKDAdr = marketDataVO.symbol;
                reporteVO.messageByTypeMKDAdr = marketDataVO.messageByType;
                reporteVO.timeMKDAdr = marketDataVO.time;
                reporteVO.buyPxMKDAdr = marketDataVO.buyPx;
                reporteVO.buyQtyMKDAdr = marketDataVO.buyQty;
                reporteVO.sellPxMKDAdr = marketDataVO.sellPx;
                reporteVO.sellQtyMKDAdr = marketDataVO.sellQty;
                reporteVO.closePxMKDAdr = marketDataVO.closePx;

            } else {

                MarketDataVO marketDataVO = Repository.reportHashmapMkdADR.get(e.getKey());
                ReporteVO reporteVO = new ReporteVO();

                reporteVO.symbolMKDAdr = marketDataVO.symbol;
                reporteVO.messageByTypeMKDAdr = marketDataVO.messageByType;
                reporteVO.timeMKDAdr = marketDataVO.time;
                reporteVO.time = marketDataVO.time;
                reporteVO.buyPxMKDAdr = marketDataVO.buyPx;
                reporteVO.buyQtyMKDAdr = marketDataVO.buyQty;
                reporteVO.sellPxMKDAdr = marketDataVO.sellPx;
                reporteVO.sellQtyMKDAdr = marketDataVO.sellQty;
                reporteVO.closePxMKDAdr = marketDataVO.closePx;

                Repository.reportHashmap.put(e.getKey(), reporteVO);

            }


        }

        Repository.reportHashmapMkdADR.clear();

    }


    public static void setMKDLocal(){

        for (Map.Entry<Long, MarketDataVO> e: Repository.reportHashmapMkdLocal.entrySet()) {

            if(Repository.reportHashmap.containsKey(e.getKey())){

                MarketDataVO marketDataVO = Repository.reportHashmapMkdLocal.get(e.getKey());
                ReporteVO reporteVO = Repository.reportHashmap.get(e.getKey());

                reporteVO.symbolMKDLocal = marketDataVO.symbol;
                reporteVO.messageByTypeMKDLocal = marketDataVO.messageByType;
                reporteVO.timeMKDLocal = marketDataVO.time;
                reporteVO.buyPxMKDLocal = marketDataVO.buyPx;
                reporteVO.buyQtyMKDLocal = marketDataVO.buyQty;
                reporteVO.sellPxMKDLocal = marketDataVO.sellPx;
                reporteVO.sellQtyMKDLocal = marketDataVO.sellQty;
                reporteVO.closePxMKDLocal = marketDataVO.closePx;

            } else {

                MarketDataVO marketDataVO = Repository.reportHashmapMkdLocal.get(e.getKey());
                ReporteVO reporteVO = new ReporteVO();

                reporteVO.symbolMKDLocal = marketDataVO.symbol;
                reporteVO.messageByTypeMKDLocal = marketDataVO.messageByType;
                reporteVO.timeMKDLocal = marketDataVO.time;
                reporteVO.time = marketDataVO.time;
                reporteVO.buyPxMKDLocal = marketDataVO.buyPx;
                reporteVO.buyQtyMKDLocal = marketDataVO.buyQty;
                reporteVO.sellPxMKDLocal = marketDataVO.sellPx;
                reporteVO.sellQtyMKDLocal = marketDataVO.sellQty;
                reporteVO.closePxMKDLocal = marketDataVO.closePx;

                Repository.reportHashmap.put(e.getKey(), reporteVO);

            }

        }

        Repository.reportHashmapMkdLocal.clear();

    }

    public static void  printReport() throws Exception {

        FileWriter fichero = new FileWriter("c:/Reporte/prueba.csv");
        PrintWriter pw = new PrintWriter(fichero);

        Map<Long, ReporteVO> reportHashmapSort = new TreeMap<Long, ReporteVO>(Repository.reportHashmap);

        pw.print("Time;");

        pw.print("Symbol Local;");
        pw.print("Type Local;");
        pw.print("Price Local;");
        pw.print("Order Qty Local;");
        pw.print("Exec Type Local;");
        pw.print("Last Qty Local;");
        pw.print("Last Px Local;");
        pw.print("Leaves Qty Local;");

        pw.print("Type ADR;");
        pw.print("Price ADR;");
        pw.print("Order Qty ADR;");
        pw.print("Exec Type ADR;");
        pw.print("Last Qty ADR;");
        pw.print("Last Px ADR;");
        pw.print("Leaves Qty ADR;");

        pw.print("Buy Px Dolar CAD;");
        pw.print("Sell Px Dolar CAD;");
        pw.print("Close Px Dolar CAD;");

        pw.print("Close Px Dolar COF;");

        pw.print("Buy Px MKD Local;");
        pw.print("Buy Qty MKD Local;");
        pw.print("Sell Px MKD Local;");
        pw.print("Sell Qty MKD Local;");
        pw.print("Close Px MKD Local;");

        pw.print("Buy Px MKD Adr;");
        pw.print("Buy Qty MKD Adr;");
        pw.print("Sell Px MKD Adr;");
        pw.print("Sell Qty MKD Adr;");
        pw.print("Close Px MKD Adr;");
        pw.println();



        for (Map.Entry<Long, ReporteVO> e: reportHashmapSort.entrySet()) {

            ReporteVO reporteVO = reportHashmapSort.get(e.getKey());

            pw.print(reporteVO.time + ";" );
            pw.print(reporteVO.symbolRoutingLocal + ";" );
            pw.print(reporteVO.messageByTypeRoutingLocal + ";" );
            pw.print(reporteVO.priceRoutingLocal + ";" );
            pw.print(reporteVO.orderQtyRoutingLocal + ";" );
            pw.print(reporteVO.execTypeRoutingLocal + ";" );
            pw.print(reporteVO.lastQtyRoutingLocal + ";" );
            pw.print(reporteVO.lastPxRoutingLocal + ";" );
            pw.print(reporteVO.leavesQtyRoutingLocal + ";" );

            pw.print(reporteVO.messageByTypeRoutingADR + ";" );
            pw.print(reporteVO.priceADR + ";" );
            pw.print(reporteVO.orderQtyADR + ";" );
            pw.print(reporteVO.execTypeADR + ";" );
            pw.print(reporteVO.lastQtyADR + ";" );
            pw.print(reporteVO.lastPxADR + ";" );
            pw.print(reporteVO.leavesQtyADR + ";" );

            pw.print(reporteVO.buyPxDolarCAD + ";" );
            pw.print(reporteVO.sellPxDolarCAD + ";" );
            pw.print(reporteVO.closePxDolarCAD + ";" );

            pw.print(reporteVO.closePxDolarCOF + ";" );

            pw.print(reporteVO.buyPxMKDLocal + ";" );
            pw.print(reporteVO.buyQtyMKDLocal + ";" );
            pw.print(reporteVO.sellPxMKDLocal + ";" );
            pw.print(reporteVO.sellQtyMKDLocal + ";" );
            pw.print(reporteVO.closePxMKDLocal + ";" );

            pw.print(reporteVO.buyPxMKDAdr + ";" );
            pw.print(reporteVO.buyQtyMKDAdr + ";" );
            pw.print(reporteVO.sellPxMKDAdr + ";" );
            pw.print(reporteVO.sellQtyMKDAdr + ";" );
            pw.print(reporteVO.closePxMKDAdr + ";" );

            pw.println();

        }

        fichero.close();
        System.out.println("fin printer");

    }



    public static void setMRoutingADR(){


        for (Map.Entry<Long, RoutingVO> e: Repository.reportHashmapRoutingAdr.entrySet()) {

            if (Repository.reportHashmap.containsKey(e.getKey())) {

                RoutingVO routingVO = Repository.reportHashmapRoutingAdr.get(e.getKey());
                ReporteVO reporteVO = Repository.reportHashmap.get(e.getKey());

                reporteVO.symbolRoutingADR = routingVO.symbol;
                reporteVO.messageByTypeRoutingADR  = routingVO.messageByType;
                reporteVO.timeRoutingADR = routingVO.time;

                reporteVO.priceADR  = routingVO.price;
                reporteVO.orderQtyADR = routingVO.orderQty;
                reporteVO.execTypeADR = routingVO.execType;
                reporteVO.lastQtyADR = routingVO.lastQty;
                reporteVO.lastPxADR = routingVO.lastPx;
                reporteVO.leavesQtyADR = routingVO.leavesQty;

            } else {


                RoutingVO routingVO = Repository.reportHashmapRoutingAdr.get(e.getKey());
                ReporteVO reporteVO = new ReporteVO();

                reporteVO.symbolRoutingADR = routingVO.symbol;
                reporteVO.messageByTypeRoutingADR  = routingVO.messageByType;
                reporteVO.timeRoutingADR = routingVO.time;
                reporteVO.time = routingVO.time;

                reporteVO.priceADR  = routingVO.price;
                reporteVO.orderQtyADR = routingVO.orderQty;
                reporteVO.execTypeADR = routingVO.execType;
                reporteVO.lastQtyADR = routingVO.lastQty;
                reporteVO.lastPxADR = routingVO.lastPx;
                reporteVO.leavesQtyADR = routingVO.leavesQty;

                Repository.reportHashmap.put(e.getKey(), reporteVO);


            }

        }

        Repository.reportHashmapRoutingAdr.clear();

    }

    public static void setMRoutingLocal(){


        for (Map.Entry<Long, RoutingVO> e: Repository.reportHashmapRoutingLocal.entrySet()) {

            if (Repository.reportHashmap.containsKey(e.getKey())) {

                RoutingVO routingVO = Repository.reportHashmapRoutingLocal.get(e.getKey());
                ReporteVO reporteVO = Repository.reportHashmap.get(e.getKey());

                reporteVO.symbolRoutingLocal = routingVO.symbol;
                reporteVO.messageByTypeRoutingLocal = routingVO.messageByType;
                reporteVO.timeRoutingLocal = routingVO.time;

                reporteVO.priceRoutingLocal = routingVO.price;
                reporteVO.orderQtyRoutingLocal = routingVO.orderQty;
                reporteVO.execTypeRoutingLocal = routingVO.execType;
                reporteVO.lastQtyRoutingLocal = routingVO.lastQty;
                reporteVO.lastPxRoutingLocal = routingVO.lastPx;
                reporteVO.leavesQtyRoutingLocal = routingVO.leavesQty;

            } else {

                RoutingVO routingVO = Repository.reportHashmapRoutingLocal.get(e.getKey());
                ReporteVO reporteVO = new ReporteVO();

                reporteVO.symbolRoutingLocal = routingVO.symbol;
                reporteVO.messageByTypeRoutingLocal = routingVO.messageByType;
                reporteVO.timeRoutingLocal = routingVO.time;
                reporteVO.time = routingVO.time;

                reporteVO.priceRoutingLocal = routingVO.price;
                reporteVO.orderQtyRoutingLocal = routingVO.orderQty;
                reporteVO.execTypeRoutingLocal = routingVO.execType;
                reporteVO.lastQtyRoutingLocal = routingVO.lastQty;
                reporteVO.lastPxRoutingLocal = routingVO.lastPx;
                reporteVO.leavesQtyRoutingLocal = routingVO.leavesQty;

                Repository.reportHashmap.put(e.getKey(), reporteVO);

            }

        }

        Repository.reportHashmapRoutingLocal.clear();

    }


    public static void setMKDDolarCof(){

        for (Map.Entry<Long, MarketDataVO> e: Repository.reportHashmapMkdDolarCof.entrySet()) {

            if (Repository.reportHashmap.containsKey(e.getKey())) {

                MarketDataVO marketDataVO = Repository.reportHashmapMkdDolarCof.get(e.getKey());
                ReporteVO reporteVO = Repository.reportHashmap.get(e.getKey());

                reporteVO.symbolDolarCOF = marketDataVO.symbol;
                reporteVO.messageByTypeDolarCOF = marketDataVO.messageByType;
                reporteVO.timeDolarCOF = marketDataVO.time;
                reporteVO.buyPxDolarCOF = marketDataVO.buyPx;
                reporteVO.buyQtyDolarCOF = marketDataVO.buyQty;
                reporteVO.sellPxDolarCOF = marketDataVO.sellPx;
                reporteVO.sellQtyDolarCOF = marketDataVO.sellQty;
                reporteVO.closePxDolarCOF = marketDataVO.closePx;

            } else {


                MarketDataVO marketDataVO = Repository.reportHashmapMkdDolarCof.get(e.getKey());

                ReporteVO reporteVO = new ReporteVO();
                reporteVO.symbolDolarCOF = marketDataVO.symbol;
                reporteVO.messageByTypeDolarCOF = marketDataVO.messageByType;
                reporteVO.timeDolarCOF = marketDataVO.time;
                reporteVO.time = marketDataVO.time;
                reporteVO.buyPxDolarCOF = marketDataVO.buyPx;
                reporteVO.buyQtyDolarCOF = marketDataVO.buyQty;
                reporteVO.sellPxDolarCOF = marketDataVO.sellPx;
                reporteVO.sellQtyDolarCOF = marketDataVO.sellQty;
                reporteVO.closePxDolarCOF = marketDataVO.closePx;

                Repository.reportHashmap.put(e.getKey(), reporteVO);
            }

        }

        Repository.reportHashmapMkdDolarCof.clear();

    }


    public static RoutingVO routing(String message, String filter){

        RoutingVO routingVO = null;

        try {

            MsgType typeOfMessage = Message.identifyType(message);
            String[] date = message.split("8=")[0].split("-");

            Map<Object, Object> messageMap = com.larrainvial.report.utils.Helper.getFixMessageParties(message);

            if(typeOfMessage.getValue().equals("5") || typeOfMessage.getValue().equals("A") || typeOfMessage.getValue().equals("1") || typeOfMessage.getValue().equals("0")){
                return null;
            }

            routingVO = new RoutingVO();

            routingVO.messageByType = typeOfMessage.getValue();
            routingVO.symbol = messageMap.containsKey(FixConstants.Symbol) ? messageMap.get(FixConstants.Symbol).toString() : "";
            routingVO.time = date[1];

            if(!filter.equals("") && !filter.equals(routingVO.symbol)){
                return null;
            }

            routingVO.execType = messageMap.containsKey(FixConstants.ExecType) ? com.larrainvial.logviwer.utils.Helper.execType(messageMap.get(FixConstants.ExecType).toString()) : "";
            routingVO.leavesQty = messageMap.containsKey(FixConstants.LeavesQty) ? Double.valueOf(messageMap.get(FixConstants.LeavesQty).toString()) : 0d;

            routingVO.price = messageMap.containsKey(FixConstants.Price) ? Double.valueOf(messageMap.get(FixConstants.Price).toString()) : 0d;
            routingVO.orderQty = messageMap.containsKey(FixConstants.OrderQty) ? Double.valueOf(messageMap.get(FixConstants.OrderQty).toString()) : 0d;
            routingVO.lastQty = messageMap.containsKey(FixConstants.LastQty) ? Double.valueOf(messageMap.get(FixConstants.LastQty).toString().replace(".0", "")) : 0d;
            routingVO.lastPx = messageMap.containsKey(FixConstants.LastPx) ? Double.valueOf(messageMap.get(FixConstants.LastPx).toString()) : 0d;

        } catch (Exception e){
            e.printStackTrace();
        }


        return routingVO;

    }

    public static MarketDataVO marketData(String message, String filter) {

        MarketDataVO marketDataVO = null;

        try {

            ArrayList<Map> mDEntryType;
            MsgType typeOfMessage = Message.identifyType(message);
            String[] date = message.split("8=")[0].split("-");


            Map<Object, Object> messageMap = com.larrainvial.report.utils.Helper.getFixMessageAttributeFull(message);


            if(typeOfMessage.getValue().equals("5") || typeOfMessage.getValue().equals("A") || typeOfMessage.getValue().equals("1") || typeOfMessage.getValue().equals("3")
                    || typeOfMessage.getValue().equals("V") || typeOfMessage.getValue().equals("0")){
                return null;
            }

            if(!messageMap.containsKey(FixConstants.NoMDEntries)){
                return null;
            }

            marketDataVO = new MarketDataVO();
            marketDataVO.time = date[1];
            marketDataVO.messageByType = typeOfMessage.getValue();
            marketDataVO.symbol = messageMap.containsKey(FixConstants.Symbol) ? messageMap.get(FixConstants.Symbol).toString() : "";

            if(!filter.equals("") && !filter.equals(marketDataVO.symbol)){
                return null;
            }

            mDEntryType = (ArrayList<Map>) messageMap.get(FixConstants.NoMDEntries);


            for (Map map : mDEntryType) {

                if (map.get(FixConstants.MDEntryType).equals("0")) {

                    if (map.get(FixConstants.MDEntrySize).toString() != null) {
                        marketDataVO.buyQty = Double.valueOf(map.get(FixConstants.MDEntrySize).toString());
                    }

                    if (map.get(FixConstants.MDEntryPx).toString() != null) {
                        marketDataVO.buyPx = Double.valueOf(map.get(FixConstants.MDEntryPx).toString());
                    }
                }

                if (map.get(FixConstants.MDEntryType).equals("1")) {

                    if (map.get(FixConstants.MDEntrySize).toString() != null) {
                        marketDataVO.sellQty = Double.valueOf(map.get(FixConstants.MDEntrySize).toString());
                    }

                    if (map.get(FixConstants.MDEntryPx).toString() != null) {
                        marketDataVO.sellPx = Double.valueOf(map.get(FixConstants.MDEntryPx).toString());
                    }

                }

                if (map.get(FixConstants.MDEntryType).equals("5")) {

                    if (map.get(FixConstants.MDEntryPx).toString() != null) {
                        marketDataVO.closePx = Double.valueOf(map.get(FixConstants.MDEntryPx).toString());
                    }
                }

                if (map.get(FixConstants.MDEntryType).equals("A")) {
                   return null;
                }

                if (map.get(FixConstants.MDEntryType).equals("B")) {
                    return null;
                }


            }

        } catch (Exception e) {
             e.printStackTrace();
             System.out.println(message);
        }

        return marketDataVO;
    }

    public synchronized static String getDatefromLog(String date){

        String[] dateString = date.split("8=")[0].split("-");
        date = dateString[1];
        date.trim();
        date = date.replace(".", ",");
        String[] date2 = date.split(",");
        date2[1] = date2[1].replace(":", "");
        date = date2[0] + "." + date2[1];
        date = date.trim();
        dateString[0] = dateString[0].replace(":", "");

        return dateString[0] + " " + date;

    }

    public static Calendar parseTimestamp(String timestamp) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss.SSS", Locale.US);
        Date d = sdf.parse(timestamp);
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);

        return cal;

    }

    public synchronized static Map<Object, Object> getFixMessageParties(String fixMessageString) {

        try {

            Map<Object, Object> orderedFixMessage = new HashMap<Object, Object>();
            Map<Object, Object> mdParties = null;
            List<Map> parties = new ArrayList<Map>();

            Integer noPartyIDs = -1;
            Integer countId = 0;

            HashMap attributes = getHashWithAttribute();

            String[] valuesFixMessage = fixMessageString.split("\1");
            for (String tag : valuesFixMessage) {

                String[] value = tag.split("=");

                Attribute attribute = (Attribute) attributes.get(value[0]);

                if (attribute != null) {

                    if (attribute.equals(FixConstants.NoPartyIDs)) noPartyIDs = Integer.valueOf(value[1]);

                    if (attribute.equals(FixConstants.PartyID)) {

                        mdParties = new HashMap<Object, Object>();
                        mdParties.put(attribute, value[1]);
                        parties.add(mdParties);

                        countId++;
                        continue;

                    } else if (countId <= noPartyIDs
                            && attribute.equals(FixConstants.PartyID)
                            || attribute.equals(FixConstants.PartyIDSource)
                            || attribute.equals(FixConstants.PartyRole)) {

                        if (mdParties != null) mdParties.put(attribute, value[1]);

                    } else {

                        orderedFixMessage.put(attribute, value[1]);
                    }

                }
            }

            if (!parties.isEmpty())
                orderedFixMessage.put(attributes.get(String.valueOf(FixConstants.NoPartyIDs.getId())), parties);

            return orderedFixMessage;

        } catch (Exception e) {
            //new Algo().exception(e);
        }

        return null;
    }

    public static synchronized HashMap getHashWithAttribute() {

        HashMap attributes = new HashMap();

        for (Object object : FixConstants.m_attributes.getAllAttributes()) {
            Attribute attribute = (Attribute) object;
            attributes.put(String.valueOf(attribute.getId()), attribute);
        }

        return attributes;
    }

    public static synchronized Map<Object, Object> getFixMessageAttributeFull(String fixMessageString)throws Exception {

        Map<Object, Object> orderedFixMessage = new HashMap<Object, Object>();

        Map<Object, Object> mdEntries = null;
        List<Map> entries = new ArrayList<Map>();

        Integer noMDEntries = -1;
        Integer countId = 0;

        HashMap attributes = getHashWithAttribute();

        String[] valuesFixMessage = fixMessageString.split("\1");

        for(String tag : valuesFixMessage){

            String[] value = tag.split("=");

            Attribute attribute = (value[0].equals("1020")) ? (Attribute) attributes.get(String.valueOf(FixConstants.MDEntrySize.getId())) : (Attribute) attributes.get(value[0]);

            if(attribute != null){

                if(attribute.equals(FixConstants.NoMDEntries)) noMDEntries = Integer.valueOf(value[1]);

                if(attribute.equals(FixConstants.MDEntryType)){

                    mdEntries = new HashMap<Object, Object>();

                    mdEntries.put(attribute, value[1]);

                    entries.add(mdEntries);

                    countId++;
                    continue;

                }else if(countId <= noMDEntries){

                    if(mdEntries != null) mdEntries.put(attribute, value[1]);
                    orderedFixMessage.put(attribute, value[1]);

                }else{

                    orderedFixMessage.put(attribute, value[1]);
                }
            }
        }

        if(!entries.isEmpty()) orderedFixMessage.put(attributes.get(String.valueOf(FixConstants.NoMDEntries.getId())), entries);
        return orderedFixMessage;

    }


    public static Long getFirst(LinkedHashMap<Long, RoutingVO> reportHashmapRouting){

        final Map<Long, RoutingVO> orderMap = reportHashmapRouting;

        final Set<Map.Entry<Long, RoutingVO>> mapValues = orderMap.entrySet();
        final int maplength = mapValues.size();
        final Map.Entry<Long,String>[] test = new Map.Entry[maplength];
        mapValues.toArray(test);

        return Long.valueOf(test[0].getKey());

    }


}
