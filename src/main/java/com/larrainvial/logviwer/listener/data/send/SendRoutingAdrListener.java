package com.larrainvial.logviwer.listener.data.send;


import com.larrainvial.logviwer.Algo;
import com.larrainvial.logviwer.Repository;
import com.larrainvial.logviwer.event.senddata.SendMkdLocalEvent;
import com.larrainvial.logviwer.event.senddata.SendRoutingAdrEvent;
import com.larrainvial.logviwer.vo.ClientVO;
import com.larrainvial.trading.emp.Event;
import com.larrainvial.trading.emp.Listener;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;

public class SendRoutingAdrListener implements Listener {

    @Override
    public void eventOccurred(Event event) {

        try {

            SendRoutingAdrEvent ev = (SendRoutingAdrEvent) event;

            Algo algo = Repository.strategy.get(ev.nameAlgo);

            synchronized(algo.strategyDataVO.routingAdrMasterListArray) {
                algo.strategyDataVO.routingAdrMasterListArray.add(ev.modelRoutingData);

                for (Map.Entry<String, ClientVO> e : Repository.cliente.entrySet()) {

                    try {

                        ClientVO clientVO = Repository.cliente.get(e.getKey());

                        while(true) {

                            if(clientVO.sendDataInicial) {
                                ObjectOutputStream ooStream = new ObjectOutputStream(clientVO.socket.getOutputStream());
                                ooStream.writeObject(ev.modelRoutingData);
                                ooStream.flush();
                                break;
                            }
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }



        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
