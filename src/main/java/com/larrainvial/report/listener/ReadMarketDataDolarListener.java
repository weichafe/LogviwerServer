package com.larrainvial.report.listener;

import com.larrainvial.report.Repository;
import com.larrainvial.report.event.ReadMarketDataDolarEvent;
import com.larrainvial.report.utils.Helper;
import com.larrainvial.report.vo.MarketDataVO;
import com.larrainvial.report.vo.ReporteVO;
import com.larrainvial.trading.emp.Event;
import com.larrainvial.trading.emp.Listener;
import java.util.Scanner;

public class ReadMarketDataDolarListener implements Listener {

    @Override
    public void eventOccurred(Event event) {

        int contador = 0;
        int contador2 = 0;

        ReadMarketDataDolarEvent ev = (ReadMarketDataDolarEvent)event;

        try {

            Scanner dolar = new Scanner(ev.algo.inputStream_mkd_dolar, "UTF-8");

            while (dolar.hasNextLine()) {

                String line = dolar.nextLine();
                Long timer = getTime(line);

                if(Repository.timeFirs > timer) continue;
                if(Repository.timeLast < timer) break;

                MarketDataVO marketDataVO = Helper.marketData(line, "");

                if(marketDataVO == null) continue;

                if (marketDataVO.symbol.equals("COFX INDEX")){
                    Repository.reportHashmapMkdDolarCof.put(timer, marketDataVO);


                } else {

                    /*
                    ReporteVO reporteVO = new ReporteVO();

                    reporteVO.time = marketDataVO.time;
                    reporteVO.symbolDolarCAD = marketDataVO.symbol;
                    reporteVO.messageByTypeDolarCAD = marketDataVO.messageByType;
                    reporteVO.timeDolarCAD = marketDataVO.time;
                    reporteVO.buyPxDolarCAD = marketDataVO.buyPx;
                    reporteVO.buyQtyDolarCAD = marketDataVO.buyQty;
                    reporteVO.sellPxDolarCAD = marketDataVO.sellPx;
                    reporteVO.sellQtyDolarCAD = marketDataVO.sellQty;
                    reporteVO.closePxDolarCAD = marketDataVO.closePx;

                    Repository.reportHashmap.put(timer, reporteVO);
                    */

                    Repository.reportHashmapMkdDolarCad.put(timer, marketDataVO);

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
