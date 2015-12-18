package com.larrainvial.logviewer.listener.readlog;

import com.larrainvial.logviewer.bo.Algo;
import com.larrainvial.logviewer.event.readlog.ReadlogRoutingAdrEvent;
import com.larrainvial.logviewer.event.stringtofix.RoutingAdrEvent;
import com.larrainvial.trading.emp.Controller;
import com.larrainvial.trading.emp.Event;
import com.larrainvial.trading.emp.Listener;
import java.util.Scanner;
import java.util.logging.Level;

public class ReadlogRoutingAdrListener implements Listener {

    public Algo algo;

    public ReadlogRoutingAdrListener(Algo algo){
        this.algo = algo;
    }

    @Override
    public synchronized void eventOccurred(Event event){

        try {

            ReadlogRoutingAdrEvent ev = (ReadlogRoutingAdrEvent) event;

            if(!ev.algo.nameAlgo.equals(algo.nameAlgo)) return;

            Scanner sc = new Scanner(algo.inputStreamRoutingAdr, "UTF-8");

            while (sc.hasNextLine()) {

                String message = sc.nextLine();
                Controller.dispatchEvent(new RoutingAdrEvent(algo, message));
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    
}
