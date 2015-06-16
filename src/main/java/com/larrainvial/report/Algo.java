package com.larrainvial.report;

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
    public boolean printer = false;

    public FileInputStream inputStream_mkd_dolar;
    public FileInputStream inputStream_mkd_local;
    public FileInputStream inputStream_mkd_adr;
    public FileInputStream inputStream_routing_local;
    public FileInputStream inputStream_routing_adr;

    public Algo() {

        try {

            String location = "src\\main\\resources\\log\\AdrArbitrageIB\\";
            String mkd_dolar = "FIX.4.4-LVMDG-BLODPENNA7.messages_";
            String mkd_nyse = "FIX.4.4-ARBv3_EQUITY_NYS_BCS-MAMA_NYSE.messages_";
            String mkd_local = "FIX.4.4-MKDATACL2-MKDATAFHBCS2.messages_";
            String routing_local = "FIX.4.4-ARDARB_XSGO_IB-AMGTOBCS.messages_";
            String routing_nyse = "FIX.4.4-LVBSG-ADR_ARBITRAGE_IB_XNYS.messages_";
            String log = ".log";

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
