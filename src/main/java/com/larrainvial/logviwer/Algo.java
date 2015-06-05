package com.larrainvial.logviwer;

import com.larrainvial.logviwer.event.NewClientEvent;
import com.larrainvial.logviwer.event.TriggerReadFileEvent;
import com.larrainvial.logviwer.model.ModelMarketData;
import com.larrainvial.logviwer.model.ModelPositions;
import com.larrainvial.logviwer.model.ModelRoutingData;
import com.larrainvial.trading.emp.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.*;

public class Algo implements Serializable {

    private static final long serialVersionUID = 8799656478674226639L;

    private String nameAlgo;
    private String mkd_dolar;
    private String mkd_local;
    private String mkd_adr;
    private String routing_local;
    private String routing_adr;

    public ArrayList<ModelMarketData> dolarMasterList = new ArrayList<ModelMarketData>();
    public ArrayList<ModelMarketData> mkdAdrMasterList = new ArrayList<ModelMarketData>();
    public ArrayList<ModelMarketData> mkdLocalMasterList = new ArrayList<ModelMarketData>();

    public ArrayList<ModelRoutingData> routingAdrMasterList = new ArrayList<ModelRoutingData>();
    public ArrayList<ModelRoutingData> routingLocalMasterList = new ArrayList<ModelRoutingData>();
    public ArrayList<ModelRoutingData> routingBlotterMasterList = new ArrayList<ModelRoutingData>();

    public ArrayList<ModelPositions> positionsMasterList = new ArrayList<ModelPositions>();
    public Map<String,ModelPositions> positionsMasterListHash = Collections.synchronizedMap(new LinkedHashMap<String, ModelPositions>());

    private TimerTask timerTask;

    private File file_mkd_dolar;
    private File file_mkd_local;
    private File file_mkd_adr;
    private File file_routing_local;
    private File file_routing_adr;

    private FileInputStream inputStream_mkd_dolar;
    private FileInputStream inputStream_mkd_local;
    private FileInputStream inputStream_mkd_adr;
    private FileInputStream inputStream_routing_local;
    private FileInputStream inputStream_routing_adr;

    public Algo(){

        try {

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void fileReader() {

        try {

            inputStream_mkd_dolar = new FileInputStream(file_mkd_dolar);
            inputStream_mkd_local = new FileInputStream(file_mkd_local);
            inputStream_mkd_adr = new FileInputStream(file_mkd_adr);
            inputStream_routing_local = new FileInputStream(file_routing_local);
            inputStream_routing_adr = new FileInputStream(file_routing_adr);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void iniziale() throws Exception {

        ArrayList<String> typeMarket = new ArrayList<String>();
        ArrayList<FileInputStream> typeFile = new ArrayList<FileInputStream>();

        typeMarket.add(0, mkd_dolar);
        typeMarket.add(1, mkd_local);
        typeMarket.add(2, mkd_adr);
        typeMarket.add(3, routing_local);
        typeMarket.add(4, routing_adr);

        typeFile.add(0, inputStream_mkd_dolar);
        typeFile.add(1, inputStream_mkd_local);
        typeFile.add(2, inputStream_mkd_adr);
        typeFile.add(3, inputStream_routing_local);
        typeFile.add(4, inputStream_routing_adr);

        timerTask = new TimerTask(){

            public void run() {
                Controller.dispatchEvent(new TriggerReadFileEvent(this, nameAlgo, typeMarket, typeFile));
                Controller.dispatchEvent(new NewClientEvent(this));
            }

        };

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 1, 1);

    }



    public void setFile_mkd_dolar(File file_mkd_dolar) {
        this.file_mkd_dolar = file_mkd_dolar;
    }

    public void setFile_mkd_local(File file_mkd_local) {
        this.file_mkd_local = file_mkd_local;
    }

    public void setFile_mkd_adr(File file_mkd_adr) {
        this.file_mkd_adr = file_mkd_adr;
    }

    public void setFile_routing_local(File file_routing_local) {
        this.file_routing_local = file_routing_local;
    }

    public void setFile_routing_adr(File file_routing_adr) {
        this.file_routing_adr = file_routing_adr;
    }

    public String getNameAlgo() {
        return nameAlgo;
    }

    public void setNameAlgo(String nameAlgo) {
        this.nameAlgo = nameAlgo;
    }

    public String getMkd_dolar() {
        return mkd_dolar;
    }

    public void setMkd_dolar(String mkd_dolar) {
        this.mkd_dolar = mkd_dolar;
    }

    public String getMkd_local() {
        return mkd_local;
    }

    public void setMkd_local(String mkd_local) {
        this.mkd_local = mkd_local;
    }

    public String getMkd_adr() {
        return mkd_adr;
    }

    public void setMkd_adr(String mkd_adr) {
        this.mkd_adr = mkd_adr;
    }

    public String getRouting_local() {
        return routing_local;
    }

    public void setRouting_local(String routing_local) {
        this.routing_local = routing_local;
    }

    public String getRouting_adr() {
        return routing_adr;
    }

    public void setRouting_adr(String routing_adr) {
        this.routing_adr = routing_adr;
    }

}