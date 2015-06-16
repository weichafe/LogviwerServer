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

        setMKDDolarCad();
        System.out.println("fin set dolar CAD");

        setMKDDolarCof();
        System.out.println("fin set dolar COF");

        setMRoutingLocal();
        System.out.println("fin set Routing Local");

        setMRoutingADR();
        System.out.println("fin set Routing ADR");

        setMKDLocar();
        System.out.println("fin set MKD local");

        setMKDADR();
        System.out.println("fin set MKD ADR");

        Repository.printer = true;

    }

    public static void setMKDADR(){

        for (Map.Entry<Long, ReporteVO> e: Repository.reportHashmap.entrySet()) {


            if(Repository.reportHashmapMkdADR.containsKey(e.getKey())){

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


                for (Long i = e.getKey(); i >= 0; i--) {

                    if (Repository.reportHashmapMkdADR.containsKey(i)) {

                        MarketDataVO marketDataVO = Repository.reportHashmapMkdADR.get(i);
                        ReporteVO reporteVO = Repository.reportHashmap.get(i);

                        try {

                            if (reporteVO.symbolMKDAdr == null) reporteVO.symbolMKDAdr = marketDataVO.symbol;
                            if (reporteVO.messageByTypeMKDAdr == null) reporteVO.messageByTypeMKDAdr = marketDataVO.messageByType;
                            if (reporteVO.timeMKDAdr == null) reporteVO.timeMKDAdr = marketDataVO.time;
                            if (reporteVO.buyPxMKDAdr == null) reporteVO.buyPxMKDAdr = marketDataVO.buyPx;
                            if (reporteVO.buyQtyMKDAdr == null) reporteVO.buyQtyMKDAdr = marketDataVO.buyQty;
                            if (reporteVO.sellPxMKDAdr == null) reporteVO.sellPxMKDAdr = marketDataVO.sellPx;
                            if (reporteVO.sellQtyMKDAdr == null) reporteVO.sellQtyMKDAdr = marketDataVO.sellQty;
                            if (reporteVO.closePxMKDAdr == null) reporteVO.closePxMKDAdr = marketDataVO.closePx;

                            if (reporteVO.symbolMKDAdr != null &&
                                    reporteVO.messageByTypeMKDAdr != null &&
                                    reporteVO.timeMKDAdr != null &&
                                    reporteVO.buyPxMKDAdr != null &&
                                    reporteVO.buyQtyMKDAdr != null &&
                                    reporteVO.sellPxMKDAdr != null &&
                                    reporteVO.sellQtyMKDAdr != null &&
                                    reporteVO.closePxMKDAdr != null) {

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

    public static void setMKDLocar(){

        for (Map.Entry<Long, ReporteVO> e: Repository.reportHashmap.entrySet()) {


            if(Repository.reportHashmapMkdLocal.containsKey(e.getKey())){

                MarketDataVO marketDataVO = Repository.reportHashmapMkdLocal.get(e.getKey());
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


                for (Long i = e.getKey(); i >= 0; i--) {

                    if (Repository.reportHashmapMkdLocal.containsKey(i)) {

                        MarketDataVO marketDataVO = Repository.reportHashmapMkdLocal.get(i);
                        ReporteVO reporteVO = Repository.reportHashmap.get(i);

                        try {

                            if (reporteVO.symbolMKDAdr == null) reporteVO.symbolMKDAdr = marketDataVO.symbol;
                            if (reporteVO.messageByTypeMKDAdr == null) reporteVO.messageByTypeMKDAdr = marketDataVO.messageByType;
                            if (reporteVO.timeMKDAdr == null) reporteVO.timeMKDAdr = marketDataVO.time;
                            if (reporteVO.buyPxMKDAdr == null) reporteVO.buyPxMKDAdr = marketDataVO.buyPx;
                            if (reporteVO.buyQtyMKDAdr == null) reporteVO.buyQtyMKDAdr = marketDataVO.buyQty;
                            if (reporteVO.sellPxMKDAdr == null) reporteVO.sellPxMKDAdr = marketDataVO.sellPx;
                            if (reporteVO.sellQtyMKDAdr == null) reporteVO.sellQtyMKDAdr = marketDataVO.sellQty;
                            if (reporteVO.closePxMKDAdr == null) reporteVO.closePxMKDAdr = marketDataVO.closePx;

                            if (reporteVO.symbolMKDAdr != null &&
                                    reporteVO.messageByTypeMKDAdr != null &&
                                    reporteVO.timeMKDAdr != null &&
                                    reporteVO.buyPxMKDAdr != null &&
                                    reporteVO.buyQtyMKDAdr != null &&
                                    reporteVO.sellPxMKDAdr != null &&
                                    reporteVO.sellQtyMKDAdr != null &&
                                    reporteVO.closePxMKDAdr != null) {

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

    public static void  printReport() throws Exception {

        FileWriter fichero = new FileWriter("c:/Reporte/prueba.csv");
        PrintWriter pw = new PrintWriter(fichero);

        pw.print("Time;");

        pw.print("Symbol Local;");
        pw.print("Type Local;");
        pw.print("Price Local;");
        pw.print("Order Qty Local;");
        pw.print("Exec Type Local;");
        pw.print("Last Qty Local;");
        pw.print("Last Px Local;");
        pw.print("Leaves Qty Local;");

        pw.print("Price ADR;");
        pw.print("Order Qty ADR;");
        pw.print("Exec Type ADR;");
        pw.print("Last Qty ADR;");
        pw.print("Last Px ADR;");
        pw.print("Leaves Qty ADR;");

        pw.print("Symbol Dolar CAD;");
        pw.print("Buy Px Dolar CAD;");
        pw.print("Sell Px Dolar CAD;");
        pw.print("Close Px Dolar CAD;");

        pw.print("TIMER COF;");
        pw.print("Symbol Dolar COF;");
        pw.print("Close Px Dolar COF;");

        pw.print("Symbol MKD Local;");
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



        for (Map.Entry<Long, ReporteVO> e: Repository.reportHashmap.entrySet()) {

            ReporteVO reporteVO = Repository.reportHashmap.get(e.getKey());

            pw.print(reporteVO.time + ";" );
            pw.print(reporteVO.symbolRoutingLocal + ";" );
            pw.print(reporteVO.messageByTypeRoutingLocal + ";" );
            pw.print(reporteVO.priceRoutingLocal + ";" );
            pw.print(reporteVO.orderQtyRoutingLocal + ";" );
            pw.print(reporteVO.execTypeRoutingLocal + ";" );
            pw.print(reporteVO.lastQtyRoutingLocal + ";" );
            pw.print(reporteVO.lastPxRoutingLocal + ";" );
            pw.print(reporteVO.leavesQtyRoutingLocal + ";" );


            pw.print(reporteVO.priceADR + ";" );
            pw.print(reporteVO.orderQtyADR + ";" );
            pw.print(reporteVO.execTypeADR + ";" );
            pw.print(reporteVO.lastQtyADR + ";" );
            pw.print(reporteVO.lastPxADR + ";" );
            pw.print(reporteVO.leavesQtyADR + ";" );


            pw.print(reporteVO.symbolDolarCAD + ";" );
            pw.print(reporteVO.buyPxDolarCAD + ";" );
            pw.print(reporteVO.sellPxDolarCAD + ";" );
            pw.print(reporteVO.closePxDolarCAD + ";" );

            pw.print(reporteVO.timeDolarCOF + ";" );
            pw.print(reporteVO.symbolDolarCOF + ";" );
            pw.print(reporteVO.closePxDolarCOF + ";" );

            pw.print(reporteVO.symbolMKDLocal + ";" );
            pw.print(reporteVO.buyPxMKDLocal + ";" );
            pw.print(reporteVO.buyQtyMKDLocal + ";" );
            pw.print(reporteVO.sellPxMKDLocal + ";" );
            pw.print(reporteVO.sellQtyMKDLocal + ";" );
            pw.print(reporteVO.closePxMKDLocal + ";" );

            pw.print(reporteVO.symbolMKDAdr + ";" );
            pw.print(reporteVO.buyPxMKDAdr + ";" );
            pw.print(reporteVO.buyQtyMKDAdr + ";" );
            pw.print(reporteVO.sellPxMKDAdr + ";" );
            pw.print(reporteVO.sellQtyMKDAdr + ";" );
            pw.print(reporteVO.closePxMKDAdr + ";" );

            pw.println();


        }


        fichero.close();

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

                for (Long i = e.getKey(); i >= 0; i--) {

                    if (Repository.reportHashmap.containsKey(i)) {

                        RoutingVO routingVO = Repository.reportHashmapRoutingAdr.get(e.getKey());
                        ReporteVO reporteVO = Repository.reportHashmap.get(i);

                        try {

                            if (reporteVO.symbolRoutingLocal == null) reporteVO.symbolRoutingLocal = routingVO.symbol;
                            if (reporteVO.messageByTypeRoutingLocal == null) reporteVO.messageByTypeRoutingLocal = routingVO.messageByType;
                            if (reporteVO.timeRoutingLocal == null) reporteVO.timeRoutingLocal = routingVO.time;
                            if (reporteVO.priceRoutingLocal == null) reporteVO.priceRoutingLocal = routingVO.price;
                            if (reporteVO.orderQtyRoutingLocal == null) reporteVO.orderQtyRoutingLocal = routingVO.orderQty;
                            if (reporteVO.execTypeRoutingLocal == null) reporteVO.execTypeRoutingLocal = routingVO.execType;
                            if (reporteVO.lastQtyRoutingLocal == null) reporteVO.lastQtyRoutingLocal = routingVO.lastQty;
                            if (reporteVO.lastPxRoutingLocal == null) reporteVO.lastPxRoutingLocal = routingVO.lastPx;
                            if (reporteVO.leavesQtyRoutingLocal == null) reporteVO.leavesQtyRoutingLocal = routingVO.leavesQty;

                            if(reporteVO.symbolRoutingADR != null && reporteVO.messageByTypeRoutingADR != null
                                    && reporteVO.timeRoutingLocal != null && reporteVO.priceADR != null
                                    && reporteVO.orderQtyADR != null){
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

                for (Long i = e.getKey(); i >= 0; i--) {

                    if (Repository.reportHashmap.containsKey(i)) {

                        RoutingVO routingVO = Repository.reportHashmapRoutingLocal.get(e.getKey());
                        ReporteVO reporteVO = Repository.reportHashmap.get(i);

                        try {

                            if (reporteVO.symbolRoutingLocal == null) reporteVO.symbolRoutingLocal = routingVO.symbol;
                            if (reporteVO.messageByTypeRoutingLocal == null) reporteVO.messageByTypeRoutingLocal = routingVO.messageByType;
                            if (reporteVO.timeRoutingLocal == null) reporteVO.timeRoutingLocal = routingVO.time;
                            if (reporteVO.priceRoutingLocal == null) reporteVO.priceRoutingLocal = routingVO.price;
                            if (reporteVO.orderQtyRoutingLocal == null) reporteVO.orderQtyRoutingLocal = routingVO.orderQty;
                            if (reporteVO.execTypeRoutingLocal == null) reporteVO.execTypeRoutingLocal = routingVO.execType;
                            if (reporteVO.lastQtyRoutingLocal == null) reporteVO.lastQtyRoutingLocal = routingVO.lastQty;
                            if (reporteVO.lastPxRoutingLocal == null) reporteVO.lastPxRoutingLocal = routingVO.lastPx;
                            if (reporteVO.leavesQtyRoutingLocal == null) reporteVO.leavesQtyRoutingLocal = routingVO.leavesQty;

                            if(reporteVO.symbolRoutingLocal != null && reporteVO.messageByTypeRoutingLocal != null
                                    && reporteVO.timeRoutingLocal != null && reporteVO.priceRoutingLocal != null
                                    && reporteVO.orderQtyRoutingLocal != null){
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


    public static void setMKDDolarCad(){

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

                for (Long i = e.getKey(); i >= 0; i--) {

                    if (Repository.reportHashmap.containsKey(i)) {

                        MarketDataVO marketDataVO = Repository.reportHashmapMkdDolarCof.get(e.getKey());
                        ReporteVO reporteVO = Repository.reportHashmap.get(i);

                        try {

                            if (reporteVO.symbolDolarCOF == null) reporteVO.symbolDolarCOF = marketDataVO.symbol;
                            if (reporteVO.messageByTypeDolarCOF == null) reporteVO.messageByTypeDolarCOF = marketDataVO.messageByType;
                            if (reporteVO.timeDolarCOF == null) reporteVO.timeDolarCOF = marketDataVO.time;
                            if (reporteVO.closePxDolarCOF == null) reporteVO.closePxDolarCOF = marketDataVO.closePx;

                            if (reporteVO.closePxDolarCOF != null) {
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



/*
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
*/


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
           //e.printStackTrace();
            //System.out.println(message);
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


}
