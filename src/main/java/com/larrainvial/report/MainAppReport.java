package com.larrainvial.report;

import com.larrainvial.report.vo.ReportVO;
import quickfix.field.MsgType;
import quickfix.fix44.Message;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MainAppReport {

    public static void report() {

        try {

            Algo algo = new Algo();

            Date fechaActual = new Date();
            DateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd");
            String year = formatoFecha.format(fechaActual).replace("/", "");

            String location = "src\\main\\resources\\log\\AdrArbitrageXTSE\\";
            String mkd_dolar = "FIX.4.4-LVMDG-BLODPENNA7.messages_";
            String mkd_nyse = "FIX.4.4-LVMDG-BLODPENNA6.messages_";
            String mkd_local = "FIX.4.4-BOGCURNCY-LVMDG.messages_";
            String routing_local = "FIX.4.4-ADRARB_XTSE_TOBVC-AMGTOBVC.messages_";
            String routing_nyse = "FIX.4.4-LVBSG-CL04.messages_";
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

            Scanner sc = new Scanner(algo.inputStream_mkd_dolar, "UTF-8");

            while (sc.hasNextLine()) {
                setKeyHashMap(sc.nextLine());
            }


        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static void setKeyHashMap(String message) {

        try {

            String[] dateString = message.split("8=")[0].split("-");
            String date;

            ReportVO reportVO = new ReportVO();

            date = dateString[1];
            date.trim();
            date = date.replace(".", ",");
            String[] date2 =  date.split(",");
            date2[1] = date2[1].replace(":","");
            date = date2[0] + "." + date2[1];
            date = date.trim();

            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
            Date dates = dateFormat.parse(date);


            if(!Repository.reportHashmap.containsKey(dates)){
                Repository.reportHashmap.put(dates, reportVO);
            }




            /*
            if(!Repository.reportHashmap.containsKey(date)){
                Repository.reportHashmap.put(date, reportVO);
            }
            */




        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void routing(String message) {

        try {

            MsgType typeOfMessage = Message.identifyType(message);
            String[] date = message.split("8=")[0].split("-");


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}