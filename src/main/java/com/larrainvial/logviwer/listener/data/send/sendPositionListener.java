package com.larrainvial.logviwer.listener.data.send;

import com.larrainvial.logviwer.Algo;
import com.larrainvial.logviwer.Repository;
import com.larrainvial.logviwer.event.senddata.SendPositionEvent;
import com.larrainvial.logviwer.model.ModelPositions;
import com.larrainvial.logviwer.utils.Helper;
import com.larrainvial.logviwer.vo.ClientVO;
import com.larrainvial.trading.emp.Event;
import com.larrainvial.trading.emp.Listener;
import java.io.ObjectOutputStream;
import java.util.Map;

public class SendPositionListener implements Listener {

    @Override
    public void eventOccurred(Event event) {

        try {

            SendPositionEvent ev = (SendPositionEvent) event;

            Algo algo = Repository.strategy.get(ev.nameAlgo);

            synchronized (algo.strategyDataVO.positionsMasterListHash) {

                for (Map.Entry<String, ModelPositions> e : algo.strategyDataVO.positionsMasterListHash.entrySet()) {

                    try {

                        if (algo.strategyDataVO.positionsMasterListHash.containsKey(e.getKey())) {

                            if (e.getKey().equals(Helper.adrToLocal(ev.modelRoutingData.symbol))) {

                                algo.strategyDataVO.positionsMasterListArray.remove(e.getKey());
                                algo.strategyDataVO.positionsMasterListArray.add(algo.strategyDataVO.positionsMasterListHash.get(e.getKey()));


                                for (Map.Entry<String, ClientVO> ex : Repository.cliente.entrySet()) {

                                    try {

                                        ClientVO clientVO = Repository.cliente.get(ex.getKey());

                                        while(true) {

                                            if(clientVO.sendDataInicial) {
                                                ObjectOutputStream ooStream = new ObjectOutputStream(clientVO.socket.getOutputStream());
                                                ooStream.writeObject(algo.strategyDataVO.positionsMasterListHash.get(e.getKey()));
                                                ooStream.flush();
                                                break;
                                            }
                                        }

                                    } catch (Exception exx) {
                                        exx.printStackTrace();
                                    }
                                }

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
