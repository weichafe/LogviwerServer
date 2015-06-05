package com.larrainvial.logviwer.listener;

import com.larrainvial.logviwer.Algo;
import com.larrainvial.logviwer.Repository;
import com.larrainvial.logviwer.event.SendToViewEvent;
import com.larrainvial.logviwer.model.ModelPositions;
import com.larrainvial.logviwer.utils.Helper;
import com.larrainvial.trading.emp.Event;
import com.larrainvial.trading.emp.Listener;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;

public class SendToViewListener implements Listener {

    @Override
    public void eventOccurred(Event event) {

        SendToViewEvent ev = (SendToViewEvent) event;

        try {

            Algo algo = Repository.strategy.get(ev.nameAlgo);

            if (ev.typeMarket.equals(algo.getMkd_dolar())) {

                algo.dolarMasterList.add(ev.modelMarketData);

                for (Map.Entry<String, Socket> e: Repository.cliente.entrySet()) {

                    try {

                        Socket socket = Repository.cliente.get(e.getKey());
                        OutputStream oStream = socket.getOutputStream();
                        ObjectOutputStream ooStream = new ObjectOutputStream(oStream);
                        ooStream.writeObject(ev.modelMarketData);
                        ooStream.reset();



                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

            }else if (ev.typeMarket.equals(algo.getMkd_adr())) {
                algo.mkdAdrMasterList.add(ev.modelMarketData);

            }else if (ev.typeMarket.equals(algo.getMkd_local())) {
                algo.mkdLocalMasterList.add(ev.modelMarketData);

            }else if (ev.typeMarket.equals(algo.getRouting_adr())) {
                algo.routingAdrMasterList.add(ev.modelRoutingData);

            }else if (ev.typeMarket.equals(algo.getRouting_local())) {
                algo.routingLocalMasterList.add(ev.modelRoutingData);
            }

            if(!ev.marketData) return;

            for (Map.Entry<String, ModelPositions> e: algo.positionsMasterListHash.entrySet()) {

                try {

                    if (algo.positionsMasterListHash.containsKey(e.getKey())) {

                        if (e.getKey().equals(Helper.adrToLocal(ev.modelRoutingData.symbol))) {

                            algo.positionsMasterList.remove(e.getKey());
                            algo.positionsMasterList.add(algo.positionsMasterListHash.get(e.getKey()));
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