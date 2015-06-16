package com.larrainvial.report.listener;

import com.larrainvial.report.Repository;
import com.larrainvial.report.event.ReadMarketDataAdrEvent;
import com.larrainvial.report.event.ReadRoutingLocalEvent;
import com.larrainvial.report.utils.Helper;
import com.larrainvial.report.vo.RoutingVO;
import com.larrainvial.trading.emp.Event;
import com.larrainvial.trading.emp.Listener;
import java.util.Scanner;


public class ReadRoutingLocalListener  implements Listener {

    @Override
    public void eventOccurred(Event event) {

        ReadRoutingLocalEvent ev = (ReadRoutingLocalEvent)event;

        try {

            Scanner routinLocal = new Scanner(ev.algo.inputStream_routing_local, "UTF-8");

            while (routinLocal.hasNextLine()) {

                String line = routinLocal.nextLine();

                RoutingVO routingVO = Helper.routing(line, ev.filter);

                if(routingVO == null) continue;

                Repository.reportHashmapRoutingLocal.put(getTime(line), routingVO);

            }

            ev.algo.routingLocal = true;
            System.out.println("termino r local");



        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public long getTime(String message) throws Exception {
        return Helper.parseTimestamp(Helper.getDatefromLog(message)).getTimeInMillis();
    }
}
