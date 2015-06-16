package com.larrainvial.report;

import com.larrainvial.report.event.*;
import com.larrainvial.report.utils.Control;
import com.larrainvial.report.utils.Helper;
import com.larrainvial.report.vo.MarketDataVO;
import com.larrainvial.report.vo.RoutingVO;
import com.larrainvial.trading.emp.Controller;

import java.io.FileInputStream;
import java.util.Scanner;

public class MainAppReport {

    public static void report() {

        try {

            Control.initialize();

            Algo algo = new Algo();

            String symbolRoutingLocal = "ENERSIS";
            String symbolRoutingADR = "ENI";

            String symbolMarketLocal = "ENERSIS";
            String symbolMarketADR = "ENI";


            Controller.dispatchEvent(new ReadMarketDataDolarEvent("", algo, ""));
            Controller.dispatchEvent(new ReadMarketDataAdrEvent("", algo, symbolMarketADR));
            Controller.dispatchEvent(new ReadMarketDataLocalEvent("", algo, symbolMarketLocal));

            Controller.dispatchEvent(new ReadRoutingAdrEvent("", algo, symbolRoutingADR));
            Controller.dispatchEvent(new ReadRoutingLocalEvent("", algo, symbolRoutingLocal));

            while(true){

                if(algo.mkdDolar && algo.mkdLocal && algo.mkdAdr && algo.routingLocal && algo.routingAdr){
                    Helper.setValuesFromMKD();
                    break;
                }

            }


            while(true){

                if(Repository.printer){
                    System.out.println("printer");
                    Helper.printReport();
                    break;
                }

            }


        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static long getTime(String message) throws Exception {

        return Helper.parseTimestamp(Helper.getDatefromLog(message)).getTimeInMillis();
    }

}
