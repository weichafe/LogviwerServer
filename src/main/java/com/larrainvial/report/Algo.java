package com.larrainvial.report;

import com.larrainvial.report.utils.Helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Algo implements Serializable {

    private static final long serialVersionUID = 2635519613775362759L;

    private File file_mkd_dolar;
    private File file_mkd_local;
    private File file_mkd_adr;
    private File file_routing_local;
    private File file_routing_adr;

    public boolean mkdDolar = false;
    public boolean mkdLocal = false;
    public boolean mkdAdr = false;
    public boolean routingLocal = false;
    public boolean routingAdr = false;


    public FileInputStream inputStream_mkd_dolar;
    public FileInputStream inputStream_mkd_local;
    public FileInputStream inputStream_mkd_adr;
    public FileInputStream inputStream_routing_local;
    public FileInputStream inputStream_routing_adr;

    public Algo() {

        try {

            String location = "C:\\workspaceGit\\LogviwerServer\\src\\main\\resources\\log\\AdrArbitrageXTSE\\";
            String mkd_dolar = "FIX.4.4-LVMDG-BLODPENNA7.messages_";
            String mkd_nyse = "FIX.4.4-LVMDG-BLODPENNA6.messages_";
            String mkd_local = "FIX.4.4-BOGCURNCY-LVMDG.messages_";
            String routing_local = "FIX.4.4-ADRARB_XTSE_TOBVC-AMGTOBVC.messages_";
            String routing_nyse = "FIX.4.4-LVBSG-CL04.messages_";
            String log = ".log";

            Repository.timeFirs = Helper.parseTimestamp(Helper.getDatefromLog("20150601-13:00:32.483: 8=FIX")).getTimeInMillis();;
            Repository.timeLast = Helper.parseTimestamp(Helper.getDatefromLog("20150601-19:54:49.311: 8=FIX")).getTimeInMillis();;

            Date fechaActual = new Date();
            DateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd");
            String year = formatoFecha.format(fechaActual).replace("/", "");

            file_mkd_dolar = new File(location + mkd_dolar + year + log);
            file_mkd_local = new File(location + mkd_local + year + log);
            file_mkd_adr = new File(location + mkd_nyse + year + log);
            file_routing_local = new File(location + routing_local + year + log);
            file_routing_adr = new File(location + routing_nyse + year + log);

            inputStream_mkd_dolar = new FileInputStream(file_mkd_dolar);
            inputStream_mkd_local = new FileInputStream(file_mkd_local);
            inputStream_mkd_adr = new FileInputStream(file_mkd_adr);
            inputStream_routing_local = new FileInputStream(file_routing_local);
            inputStream_routing_adr = new FileInputStream(file_routing_adr);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
