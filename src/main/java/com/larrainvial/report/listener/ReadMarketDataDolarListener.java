package com.larrainvial.report.listener;

import com.larrainvial.report.Repository;
import com.larrainvial.report.event.ReadMarketDataDolarEvent;
import com.larrainvial.report.utils.Helper;
import com.larrainvial.report.vo.MarketDataVO;
import com.larrainvial.trading.emp.Event;
import com.larrainvial.trading.emp.Listener;
import java.util.Scanner;

public class ReadMarketDataDolarListener implements Listener {

    @Override
    public void eventOccurred(Event event) {

        ReadMarketDataDolarEvent ev = (ReadMarketDataDolarEvent)event;

        try {

            Scanner dolar = new Scanner(ev.algo.inputStream_mkd_dolar, "UTF-8");

            while (dolar.hasNextLine()) {

                String line = dolar.nextLine();

                MarketDataVO marketDataVO = Helper.marketData(line, "");

                if(marketDataVO == null) continue;

                if (marketDataVO.symbol.equals("COFX INDEX")){
                    Repository.reportHashmapMkdDolarCof.put(getTime(line), marketDataVO);

                } else {
                    Repository.reportHashmapMkdDolarCad.put(getTime(line), marketDataVO);
                }

            }

            ev.algo.mkdDolar = true;
            System.out.println("termino mkd ADR dolar");

        } catch (Exception e){
            e.printStackTrace();
        }


    }

    public long getTime(String message) throws Exception {
        return Helper.parseTimestamp(Helper.getDatefromLog(message)).getTimeInMillis();
    }
}
