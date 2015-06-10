package com.larrainvial.report.utils;

import com.javtech.javatoolkit.fix.FixConstants;
import com.javtech.javatoolkit.message.Attribute;
import com.larrainvial.report.Repository;
import com.larrainvial.report.vo.MarketDataVO;
import com.larrainvial.report.vo.RoutingVO;
import quickfix.field.MsgType;
import quickfix.fix44.Message;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

public class Helper {

    public static void  printReport() throws Exception {

        FileWriter fichero = new FileWriter("c:/Reporte/prueba.txt");
        PrintWriter pw = new PrintWriter(fichero);

        for (Map.Entry<Long, RoutingVO> e: Repository.reportHashmapRouting.entrySet()) {

            RoutingVO routingVO = Repository.reportHashmapRouting.get(e.getKey());

            pw.print(routingVO.symbol + " - "  + " - " );
            pw.print(routingVO.messageByType + " - " );
            pw.print(routingVO.time + " - " );

            pw.print(routingVO.price + " - " );
            pw.print(routingVO.orderQty + " - " );
            pw.print(routingVO.execType + " - " );
            pw.print(routingVO.lastQty + " - ");
            pw.print(routingVO.lastPx + " - ");
            pw.print(routingVO.leavesQty + " - ");

            pw.print(routingVO.symbolDolar + " - ");
            pw.print(routingVO.messageByTypeDolar + " - ");
            pw.print(routingVO.timeDolar + " - ");
            pw.print(routingVO.buyPxDolar + " - ");
            pw.print(routingVO.buyQtyDolar + " - ");
            pw.print(routingVO.sellPxDolar + " - ");
            pw.print(routingVO.sellQtyDolar + " - " );
            pw.print(routingVO.closePxDolar + " - " );

            pw.print(routingVO.symbolMKDLocal + " - " );
            pw.print(routingVO.messageByTypeMKDLocal + " - ");
            pw.print(routingVO.timeMKDLocal + " - " );
            pw.print(routingVO.buyPxMKDLocal + " - " );
            pw.print(routingVO.buyQtyMKDLocal + " - " );
            pw.print(routingVO.sellPxMKDLocal + " - " );
            pw.print(routingVO.sellQtyMKDLocal + " - " );
            pw.print(routingVO.closePxMKDLocal + " - ");

            pw.print(routingVO.symbolMKDAdr + " - ");
            pw.print(routingVO.messageByTypeMKDAdr + " - ");
            pw.print(routingVO.timeMKDAdr + " - ");
            pw.print(routingVO.buyPxMKDAdr + " - ");
            pw.print(routingVO.buyQtyMKDAdr + " - ");
            pw.print(routingVO.sellPxMKDAdr + " - ");
            pw.print(routingVO.sellQtyMKDAdr + " - ");
            pw.print(routingVO.closePxMKDAdr + " - ");

            pw.println();


        }

    }


    public static void setValuesFromMKD(){
        int contador = 1;

        for (Map.Entry<Long, RoutingVO> e: Repository.reportHashmapRouting.entrySet()) {

            RoutingVO routingVO = Repository.reportHashmapRouting.get(e.getKey());


            if (Repository.reportHashmapMkdDolar.containsKey(e.getKey())) {

                MarketDataVO marketDataVO = Repository.reportHashmapMkdDolar.get(e.getKey());
                routingVO.symbolDolar = marketDataVO.symbol;
                routingVO.messageByTypeDolar = marketDataVO.messageByType;
                routingVO.timeDolar = marketDataVO.time;
                routingVO.buyPxDolar = marketDataVO.buyPx;
                routingVO.buyQtyDolar = marketDataVO.buyQty;
                routingVO.sellPxDolar = marketDataVO.sellPx;
                routingVO.sellQtyDolar = marketDataVO.sellQty;
                routingVO.closePxDolar = marketDataVO.closePx;

            } else {

                for (Long i = e.getKey(); i >= 0; i--) {

                    if (Repository.reportHashmapMkdDolar.containsKey(i)) {

                        MarketDataVO marketDataVO = Repository.reportHashmapMkdDolar.get(i);

                        try {

                            if (routingVO.symbolDolar == null) routingVO.symbolDolar = marketDataVO.symbol;
                            if (routingVO.messageByTypeDolar == null)
                                routingVO.messageByTypeDolar = marketDataVO.messageByType;
                            if (routingVO.timeDolar == null) routingVO.timeDolar = marketDataVO.time;
                            if (routingVO.buyPxDolar == null) routingVO.buyPxDolar = marketDataVO.buyPx;
                            if (routingVO.buyQtyDolar == null) routingVO.buyQtyDolar = marketDataVO.buyQty;
                            if (routingVO.sellPxDolar == null) routingVO.sellPxDolar = marketDataVO.sellPx;
                            if (routingVO.sellQtyDolar == null) routingVO.sellQtyDolar = marketDataVO.sellQty;
                            if (routingVO.closePxDolar == null) routingVO.closePxDolar = marketDataVO.closePx;

                            if (routingVO.symbolDolar != null &&
                                    routingVO.messageByTypeDolar != null &&
                                    routingVO.timeDolar != null &&
                                    routingVO.buyPxDolar != null &&
                                    routingVO.buyQtyDolar != null &&
                                    routingVO.sellPxDolar != null &&
                                    routingVO.sellQtyDolar != null &&
                                    routingVO.closePxDolar != null) {

                                System.out.println(contador++ + " / " + Repository.reportHashmapRouting.size());
                                break;
                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    } else {

                        continue;
                    }

                }
            }

        }

        Repository.reportHashmapMkdDolar.clear();
        contador = 1;

        for (Map.Entry<Long, RoutingVO> e: Repository.reportHashmapRouting.entrySet()) {

            RoutingVO routingVO = Repository.reportHashmapRouting.get(e.getKey());

            if (Repository.reportHashmapMkdLocal.containsKey(e.getKey())) {

                MarketDataVO marketDataVO = Repository.reportHashmapMkdLocal.get(e.getKey());

                routingVO.symbolMKDLocal = marketDataVO.symbol;
                routingVO.messageByTypeMKDLocal = marketDataVO.messageByType;
                routingVO.timeMKDLocal = marketDataVO.time;
                routingVO.buyPxMKDLocal = marketDataVO.buyPx;
                routingVO.buyQtyMKDLocal = marketDataVO.buyQty;
                routingVO.sellPxMKDLocal = marketDataVO.sellPx;
                routingVO.sellQtyMKDLocal = marketDataVO.sellQty;
                routingVO.closePxMKDLocal = marketDataVO.closePx;

            } else {

                for (Long i = e.getKey(); i >= 0; i--) {

                    if (Repository.reportHashmapMkdLocal.containsKey(i)) {

                        MarketDataVO marketDataVO = Repository.reportHashmapMkdLocal.get(i);

                        try {

                            if (routingVO.symbolMKDLocal == null) routingVO.symbolMKDLocal = marketDataVO.symbol;
                            if (routingVO.messageByTypeMKDLocal == null)
                                routingVO.messageByTypeMKDLocal = marketDataVO.messageByType;
                            if (routingVO.timeMKDLocal == null) routingVO.timeMKDLocal = marketDataVO.time;
                            if (routingVO.buyPxMKDLocal == null) routingVO.buyPxMKDLocal = marketDataVO.buyPx;
                            if (routingVO.buyQtyMKDLocal == null) routingVO.buyQtyMKDLocal = marketDataVO.buyQty;
                            if (routingVO.sellPxMKDLocal == null) routingVO.sellPxMKDLocal = marketDataVO.sellPx;
                            if (routingVO.sellQtyMKDLocal == null) routingVO.sellQtyMKDLocal = marketDataVO.sellQty;
                            if (routingVO.closePxMKDLocal == null) routingVO.closePxMKDLocal = marketDataVO.closePx;

                            if (routingVO.symbolMKDLocal != null &&
                                    routingVO.messageByTypeMKDLocal != null &&
                                    routingVO.timeMKDLocal != null &&
                                    routingVO.buyPxMKDLocal != null &&
                                    routingVO.buyQtyMKDLocal != null &&
                                    routingVO.sellPxMKDLocal != null &&
                                    routingVO.sellQtyMKDLocal != null &&
                                    routingVO.closePxMKDLocal != null) {

                                System.out.println(contador++ + " / " + Repository.reportHashmapRouting.size());
                                break;
                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    } else {

                        continue;
                    }

                }
            }

        }

        Repository.reportHashmapMkdLocal.clear();
        contador = 1;

        for (Map.Entry<Long, RoutingVO> e: Repository.reportHashmapRouting.entrySet()) {

            RoutingVO routingVO = Repository.reportHashmapRouting.get(e.getKey());

            if(Repository.reportHashmapMkdADR.containsKey(e.getKey())){

                MarketDataVO marketDataVO = Repository.reportHashmapMkdADR.get(e.getKey());

                routingVO.symbolMKDAdr = marketDataVO.symbol;
                routingVO.messageByTypeMKDAdr = marketDataVO.messageByType;
                routingVO.timeMKDAdr = marketDataVO.time;
                routingVO.buyPxMKDAdr = marketDataVO.buyPx;
                routingVO.buyQtyMKDAdr = marketDataVO.buyQty;
                routingVO.sellPxMKDAdr = marketDataVO.sellPx;
                routingVO.sellQtyMKDAdr = marketDataVO.sellQty;
                routingVO.closePxMKDAdr = marketDataVO.closePx;

            } else {



                for (Long i = e.getKey(); i >= 0; i--) {


                    if (Repository.reportHashmapMkdADR.containsKey(i)) {

                        MarketDataVO marketDataVO = Repository.reportHashmapMkdADR.get(i);

                        try {

                            if (routingVO.symbolMKDAdr == null) routingVO.symbolMKDAdr = marketDataVO.symbol;
                            if (routingVO.messageByTypeMKDAdr == null) routingVO.messageByTypeMKDAdr = marketDataVO.messageByType;
                            if (routingVO.timeMKDAdr == null) routingVO.timeMKDAdr = marketDataVO.time;
                            if (routingVO.buyPxMKDAdr == null) routingVO.buyPxMKDAdr = marketDataVO.buyPx;
                            if (routingVO.buyQtyMKDAdr == null) routingVO.buyQtyMKDAdr = marketDataVO.buyQty;
                            if (routingVO.sellPxMKDAdr == null) routingVO.sellPxMKDAdr = marketDataVO.sellPx;
                            if (routingVO.sellQtyMKDAdr == null) routingVO.sellQtyMKDAdr = marketDataVO.sellQty;
                            if (routingVO.closePxMKDAdr == null) routingVO.closePxMKDAdr = marketDataVO.closePx;

                            if (routingVO.symbolMKDAdr != null &&
                                    routingVO.messageByTypeMKDAdr != null &&
                                    routingVO.timeMKDAdr != null &&
                                    routingVO.buyPxMKDAdr != null &&
                                    routingVO.buyQtyMKDAdr != null &&
                                    routingVO.sellPxMKDAdr != null &&
                                    routingVO.sellQtyMKDAdr != null &&
                                    routingVO.closePxMKDAdr != null) {


                                System.out.println(contador++ + " / " + Repository.reportHashmapRouting.size());
                                break;
                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    } else {

                        continue;
                    }

                }
            }


        }

    }

    public static RoutingVO routing(String message, String filter){

        RoutingVO routingVO = null;

        try {

            MsgType typeOfMessage = Message.identifyType(message);
            String[] date = message.split("8=")[0].split("-");

            Map<Object, Object> messageMap = com.larrainvial.logviwer.utils.Helper.getFixMessageParties(message);

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


            Map<Object, Object> messageMap = com.larrainvial.logviwer.utils.Helper.getFixMessageAttributeFull(message);


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
        }

        return marketDataVO;
    }

    public static String getDatefromLog(String date){

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


}
