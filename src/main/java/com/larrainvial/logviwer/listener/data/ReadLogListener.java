package com.larrainvial.logviwer.listener.data;

import com.larrainvial.logviwer.Algo;
import com.larrainvial.logviwer.Repository;
import com.larrainvial.logviwer.event.ReadLogEvent;
import com.larrainvial.logviwer.event.StringToFixMessageEvent;
import com.larrainvial.trading.emp.Controller;
import com.larrainvial.trading.emp.Event;
import com.larrainvial.trading.emp.Listener;

import java.util.Scanner;

public class ReadLogListener implements Listener {

    @Override
    public void eventOccurred(Event event){

        try {

            ReadLogEvent ev = (ReadLogEvent) event;

            Algo algo = Repository.strategy.get(ev.nameAlgo);

            Scanner sc = new Scanner(ev.inputStream, "UTF-8");

            while (sc.hasNextLine()) {
                Controller.dispatchEvent(new StringToFixMessageEvent(this, sc.nextLine(), ev.nameAlgo, ev.typeMarket));
            }

            if (sc.ioException() != null ) {
                sc.ioException().printStackTrace();
            }


        } catch (Exception e){
            e.printStackTrace();
        }

    }

}