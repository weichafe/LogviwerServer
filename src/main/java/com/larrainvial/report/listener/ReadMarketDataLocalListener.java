package com.larrainvial.report.listener;

import com.larrainvial.report.Repository;
import com.larrainvial.report.event.ReadMarketDataLocalEvent;
import com.larrainvial.report.utils.Helper;
import com.larrainvial.report.vo.MarketDataVO;
import com.larrainvial.trading.emp.Event;
import com.larrainvial.trading.emp.Listener;
import java.util.Scanner;


public class ReadMarketDataLocalListener  implements Listener {

    @Override
    public void eventOccurred(Event event) {

        ReadMarketDataLocalEvent ev = (ReadMarketDataLocalEvent)event;

        try {

            Scanner mkdLocal = new Scanner(ev.algo.inputStream_mkd_local, "UTF-8");

            while (mkdLocal.hasNextLine()) {

                String line = mkdLocal.nextLine();
                Long timer = getTime(line);

                if(Repository.timeFirs > timer) continue;
                if(Repository.timeLast < timer) break;

                MarketDataVO marketDataVO = Helper.marketData(line, ev.filter);

                if(marketDataVO == null) continue;

                Repository.reportHashmapMkdLocal.put(timer, marketDataVO);

            }

            ev.algo.mkdLocal = true;
            System.out.println("termino mkd local");

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public long getTime(String message) throws Exception {
        return Helper.parseTimestamp(Helper.getDatefromLog(message)).getTimeInMillis();
    }

}
