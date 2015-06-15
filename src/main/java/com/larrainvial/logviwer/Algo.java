package com.larrainvial.logviwer;

import com.larrainvial.logviwer.event.NewClientEvent;
import com.larrainvial.logviwer.event.readlog.*;
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


    public void  fileReader() throws Exception {

        inputStream_mkd_dolar = new FileInputStream(file_mkd_dolar);
        inputStream_mkd_local = new FileInputStream(file_mkd_local);
        inputStream_mkd_adr = new FileInputStream(file_mkd_adr);
        inputStream_routing_local = new FileInputStream(file_routing_local);
        inputStream_routing_adr = new FileInputStream(file_routing_adr);


    }

    public void iniziale() {

        try {

            timerTask = new TimerTask(){

                public void run() {

                    Controller.dispatchEvent(new ReadLogMarketDataDolarEvent(this, strategyDataVO.nameAlgo, strategyDataVO.mkd_dolar, inputStream_mkd_dolar));
                    Controller.dispatchEvent(new ReadLogMarketDataLocalEvent(this, strategyDataVO.nameAlgo, strategyDataVO.mkd_local, inputStream_mkd_local));
                    Controller.dispatchEvent(new ReadLogMarketDataADREvent(this, strategyDataVO.nameAlgo, strategyDataVO.mkd_adr, inputStream_mkd_adr));
                    Controller.dispatchEvent(new ReadLogRoutingLocalEvent(this, strategyDataVO.nameAlgo, strategyDataVO.routing_local, inputStream_routing_local));
                    Controller.dispatchEvent(new ReadLogRoutingADREvent(this, strategyDataVO.nameAlgo, strategyDataVO.routing_adr, inputStream_routing_adr));




                }

            };

            Timer timer = new Timer();
            timer.scheduleAtFixedRate(timerTask, 1, 1);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}