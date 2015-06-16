package com.larrainvial.report.listener;

import com.larrainvial.report.Repository;
import com.larrainvial.report.event.ReadRoutingAdrEvent;
import com.larrainvial.report.utils.Helper;
import com.larrainvial.report.vo.RoutingVO;
import com.larrainvial.trading.emp.Event;
import com.larrainvial.trading.emp.Listener;
import java.util.Scanner;


public class ReadRoutingAdrListener  implements Listener {

    @Override
    public void eventOccurred(Event event) {

        ReadRoutingAdrEvent ev = (ReadRoutingAdrEvent)event;

        try {

            Scanner routinAdr = new Scanner(ev.algo.inputStream_routing_adr, "UTF-8");

            while (routinAdr.hasNextLine()) {

                String line = routinAdr.nextLine();

                RoutingVO routingVO = Helper.routing(line, ev.filter);

                if(routingVO == null) continue;

                Repository.reportHashmapRoutingAdr.put(getTime(line), routingVO);

            }

            ev.algo.routingAdr = true;
            System.out.println("termino r adr");

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public long getTime(String message) throws Exception {
        return Helper.parseTimestamp(Helper.getDatefromLog(message)).getTimeInMillis();
    }
}
