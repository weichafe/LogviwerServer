package com.larrainvial.logviwer;

import com.larrainvial.logviwer.event.NewClientEvent;
import com.larrainvial.logviwer.event.TriggerReadFileEvent;
import com.larrainvial.logviwer.model.ModelMarketData;
import com.larrainvial.logviwer.utils.FileRepository;
import com.larrainvial.logviwer.vo.StrategyDataVO;
import com.larrainvial.trading.emp.Controller;

import java.io.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Algo implements Serializable {

    private static final long serialVersionUID = 8799656478674226639L;

    public String nameAlgo;
    public String mkd_dolar;
    public String mkd_local;
    public String mkd_adr;
    public String routing_local;
    public String routing_adr;

    public String mkd_dolar_file = null;
    public String mkd_local_file = null;
    public String mkd_adr_file = null;
    public String routing_local_file = null;
    public String routing_adr_file = null;

    public StrategyDataVO strategyDataVO = new StrategyDataVO();


    transient public File file_mkd_dolar;
    transient public File file_mkd_local;
    transient public File file_mkd_adr;
    transient public File file_routing_local;
    transient public File file_routing_adr;

    transient public FileInputStream inputStream_mkd_dolar;
    transient public FileInputStream inputStream_mkd_local;
    transient public FileInputStream inputStream_mkd_adr;
    transient public FileInputStream inputStream_routing_local;
    transient public FileInputStream inputStream_routing_adr;

    transient public FileRepository marketDataListOutput;
    transient public ObjectOutputStream routingDataListOutput;

    transient public ArrayList<ModelMarketData> marketDataListInput;
    transient public ObjectInputStream routingDataListInput;

    transient public TimerTask timerTask;

    transient public ArrayList<String> typeMarket = new ArrayList<String>();
    transient public ArrayList<FileInputStream> typeFile = new ArrayList<FileInputStream>();


    public void  fileReader()  {

        try {

            inputStream_mkd_dolar = new FileInputStream(file_mkd_dolar);
            inputStream_mkd_local = new FileInputStream(file_mkd_local);
            inputStream_mkd_adr = new FileInputStream(file_mkd_adr);
            inputStream_routing_local = new FileInputStream(file_routing_local);
            inputStream_routing_adr = new FileInputStream(file_routing_adr);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void iniziale() {

        try {

            typeMarket = new ArrayList<String>();
            typeFile = new ArrayList<FileInputStream>();

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

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}