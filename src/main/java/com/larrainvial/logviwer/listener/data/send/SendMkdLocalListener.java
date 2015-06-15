package com.larrainvial.logviwer.listener.data.send;

import com.larrainvial.logviwer.Algo;
import com.larrainvial.logviwer.Repository;
import com.larrainvial.logviwer.event.senddata.SendMkdAdrEvent;
import com.larrainvial.logviwer.event.senddata.SendMkdLocalEvent;
import com.larrainvial.logviwer.vo.ClientVO;
import com.larrainvial.trading.emp.Event;
import com.larrainvial.trading.emp.Listener;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;


public class SendMkdLocalListener implements Listener {


    @Override
    public void eventOccurred(Event event) {

        try {

            SendMkdLocalEvent ev = (SendMkdLocalEvent) event;

            Algo algo = Repository.strategy.get(ev.nameAlgo);

            synchronized (algo.strategyDataVO.mkdLocalMasterListArray) {

                algo.strategyDataVO.mkdLocalMasterListArray.add(ev.modelMarketData);

                for (Map.Entry<String, ClientVO> e : Repository.cliente.entrySet()) {

                    try {

                        ClientVO clientVO = Repository.cliente.get(e.getKey());

                        while(true) {

                            if(clientVO.sendDataInicial) {
                                ObjectOutputStream ooStream = new ObjectOutputStream(clientVO.socket.getOutputStream());
                                ooStream.writeObject(ev.modelMarketData);
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
