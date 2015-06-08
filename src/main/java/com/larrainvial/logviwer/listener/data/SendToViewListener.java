package com.larrainvial.logviwer.listener.data;

import com.larrainvial.logviwer.Algo;
import com.larrainvial.logviwer.Repository;
import com.larrainvial.logviwer.event.SendToViewEvent;
import com.larrainvial.logviwer.model.ModelPositions;
import com.larrainvial.logviwer.utils.Helper;
import com.larrainvial.trading.emp.Event;
import com.larrainvial.trading.emp.Listener;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;

public class SendToViewListener implements Listener {

    @Override
    public void eventOccurred(Event event) {

        SendToViewEvent ev = (SendToViewEvent) event;

        try {

            Algo algo = Repository.strategy.get(ev.nameAlgo);

            if (ev.typeMarket.equals(algo.strategyDataVO.mkd_dolar)) {

                algo.strategyDataVO.dolarMasterListArray.add(ev.modelMarketData);

                for (Map.Entry<String, Socket> e: Repository.cliente.entrySet()) {

                    try {

                        Socket socket = Repository.cliente.get(e.getKey());
                        ObjectOutputStream ooStream = new ObjectOutputStream(socket.getOutputStream());
                        //ooStream.writeObject(ev.modelMarketData);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

            }else if (ev.typeMarket.equals(algo.strategyDataVO.mkd_adr)) {
                algo.strategyDataVO.mkdAdrMasterListArray.add(ev.modelMarketData);

            }else if (ev.typeMarket.equals(algo.strategyDataVO.mkd_local)) {
                algo.strategyDataVO.mkdLocalMasterListArray.add(ev.modelMarketData);

            }else if (ev.typeMarket.equals(algo.strategyDataVO.routing_adr)) {
                algo.strategyDataVO.routingAdrMasterListArray.add(ev.modelRoutingData);

            }else if (ev.typeMarket.equals(algo.strategyDataVO.routing_local)) {
                algo.strategyDataVO.routingLocalMasterListArray.add(ev.modelRoutingData);
            }

            if(!ev.marketData) return;

            for (Map.Entry<String, ModelPositions> e: algo.strategyDataVO.positionsMasterListHash.entrySet()) {

                try {

                    if (algo.strategyDataVO.positionsMasterListHash.containsKey(e.getKey())) {

                        if (e.getKey().equals(Helper.adrToLocal(ev.modelRoutingData.symbol))) {

                            algo.strategyDataVO.positionsMasterListArray.remove(e.getKey());
                            algo.strategyDataVO.positionsMasterListArray.add(algo.strategyDataVO.positionsMasterListHash.get(e.getKey()));
                        }
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}