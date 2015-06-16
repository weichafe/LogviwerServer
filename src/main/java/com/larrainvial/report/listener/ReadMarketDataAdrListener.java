package com.larrainvial.report.listener;

import com.larrainvial.report.Repository;
import com.larrainvial.report.event.ReadMarketDataAdrEvent;
import com.larrainvial.report.utils.Helper;
import com.larrainvial.report.vo.MarketDataVO;
import com.larrainvial.trading.emp.Event;
import com.larrainvial.trading.emp.Listener;
import java.util.Scanner;


public class ReadMarketDataAdrListener  implements Listener {

    @Override
    public void eventOccurred(Event event) {

        ReadMarketDataAdrEvent ev = (ReadMarketDataAdrEvent)event;

        try {

            Scanner dolar = new Scanner(ev.algo.inputStream_mkd_adr, "UTF-8");

            while (dolar.hasNextLine()) {

                String line = dolar.nextLine();

                MarketDataVO marketDataVO = Helper.marketData(line, ev.filter);

                if(marketDataVO == null) continue;

                Repository.reportHashmapMkdADR.put(getTime(line), marketDataVO);
            }

            ev.algo.mkdAdr = true;
            System.out.println("termino mkd ADR");

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public long getTime(String message) throws Exception {
        return Helper.parseTimestamp(Helper.getDatefromLog(message)).getTimeInMillis();
    }

}
