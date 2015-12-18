package com.larrainvial.logviewer.listener.readlog;

import com.larrainvial.logviewer.bo.Algo;
import com.larrainvial.logviewer.event.readlog.ReadLogMkdAdrEvent;
import com.larrainvial.logviewer.event.stringtofix.MarketDataADREvent;
import com.larrainvial.trading.emp.Controller;
import com.larrainvial.trading.emp.Event;
import com.larrainvial.trading.emp.Listener;
import java.util.Scanner;
import java.util.logging.Level;

public class ReadLogMkdAdrListener implements Listener {

    public Algo algo;


    public ReadLogMkdAdrListener(Algo algo){
        this.algo = algo;
    }

    @Override
    public synchronized void eventOccurred(Event event){

        try {

            ReadLogMkdAdrEvent ev = (ReadLogMkdAdrEvent) event;

            if(!ev.algo.nameAlgo.equals(algo.nameAlgo)) return;

            Scanner sc = new Scanner(algo.inputStreamMkdAdr, "UTF-8");

            while (sc.hasNextLine()) {

                String message = sc.nextLine();
                Controller.dispatchEvent(new MarketDataADREvent(algo, message));
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
