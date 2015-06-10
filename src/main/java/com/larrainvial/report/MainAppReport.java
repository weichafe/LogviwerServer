package com.larrainvial.report;

import com.larrainvial.report.utils.Helper;
import com.larrainvial.report.vo.MarketDataVO;
import com.larrainvial.report.vo.RoutingVO;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class MainAppReport {

    public static void report() {

        try {

            Algo algo = new Algo();

            long time;
            MarketDataVO marketDataVO;
            RoutingVO routingVO;
            String symbolLocal = "ENERSIS";
            String symbolADR = "ENI";

            Date fechaActual = new Date();
            DateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd");
            String year = formatoFecha.format(fechaActual).replace("/", "");

            String location = "src\\main\\resources\\log\\AdrArbitrageIB\\";
            String mkd_dolar = "FIX.4.4-LVMDG-BLODPENNA7.messages_";
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

            algo.inputStream_mkd_dolar = new FileInputStream(algo.file_mkd_dolar);
            algo.inputStream_mkd_local = new FileInputStream(algo.file_mkd_local);
            algo.inputStream_mkd_adr = new FileInputStream(algo.file_mkd_adr);
            algo.inputStream_routing_local = new FileInputStream(algo.file_routing_local);
            algo.inputStream_routing_adr = new FileInputStream(algo.file_routing_adr);



            Scanner dolar = new Scanner(algo.inputStream_mkd_dolar, "UTF-8");

            while (dolar.hasNextLine()) {

                String line = dolar.nextLine();
                time = getTime(line);
                marketDataVO = Helper.marketData(line, "");

                if(marketDataVO == null) continue;

                Repository.reportHashmapMkdDolar.put(time, marketDataVO);
                //Repository.timerDolar.put(time, time);

            }

            System.out.println("FIN DOLAR");



            Scanner mkdLocal = new Scanner(algo.inputStream_mkd_local, "UTF-8");

            while (mkdLocal.hasNextLine()) {

                String line = mkdLocal.nextLine();
                time = getTime(line);
                marketDataVO = Helper.marketData(line, symbolLocal);

                if(marketDataVO == null) continue;

                Repository.reportHashmapMkdLocal.put(time, marketDataVO);
                //Repository.timerMKDLocal.put(time, time);


            }

            System.out.println("FIN MKD LOCAL");


            Scanner mkdADR = new Scanner(algo.inputStream_mkd_adr, "UTF-8");

            while (mkdADR.hasNextLine()) {

                String line = mkdADR.nextLine();
                time = getTime(line);
                marketDataVO = Helper.marketData(line, symbolADR);

                if(marketDataVO == null) continue;

                Repository.reportHashmapMkdADR.put(time, marketDataVO);
                //Repository.timerMKDADR.put(time, time);

            }

            System.out.println("FIN MKD ADK");


            Scanner routinLocal = new Scanner(algo.inputStream_routing_local, "UTF-8");

            while (routinLocal.hasNextLine()) {

                String line = routinLocal.nextLine();
                time = getTime(line);
                routingVO = Helper.routing(line, symbolLocal);

                if(routingVO == null) continue;

                Repository.reportHashmapRouting.put(time, routingVO);

            }

            System.out.println("FIN R LOCAL");

            Scanner routinAdr = new Scanner(algo.inputStream_routing_adr, "UTF-8");

            while (routinAdr.hasNextLine()) {

                String line = routinAdr.nextLine();
                time = getTime(line);
                routingVO = Helper.routing(line, symbolADR);

                if(routingVO == null) continue;

                Repository.reportHashmapRouting.put(time, routingVO);

            }

            System.out.println("FIN R ADR");

            Helper.setValuesFromMKD();

            System.out.println("FIN SET VALUES");


            Helper.printReport();






        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static long getTime(String message) throws Exception {

        return Helper.parseTimestamp(Helper.getDatefromLog(message)).getTimeInMillis();
    }

}
