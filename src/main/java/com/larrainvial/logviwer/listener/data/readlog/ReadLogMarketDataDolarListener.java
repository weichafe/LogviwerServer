package com.larrainvial.logviwer.listener.data.readlog;

import com.larrainvial.logviwer.Repository;
import com.larrainvial.logviwer.event.StringToFixMessageEvent;
import com.larrainvial.logviwer.event.readlog.ReadLogMarketDataDolarEvent;
import com.larrainvial.trading.emp.Controller;
import com.larrainvial.trading.emp.Event;
import com.larrainvial.trading.emp.Listener;

import java.util.Scanner;

public class ReadLogMarketDataDolarListener implements Listener {

    @Override
    public void eventOccurred(Event event){

        try {

            ReadLogMarketDataDolarEvent ev = (ReadLogMarketDataDolarEvent) event;

            Scanner sc = new Scanner(ev.inputStream, "UTF-8");

            while (sc.hasNextLine()) {
                Controller.dispatchEvent(new StringToFixMessageEvent(this, sc.nextLine(), ev.nameAlgo, ev.typeMarket));
                Thread.sleep(Repository.timer);
            }

            System.out.println("fin 2");

        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
