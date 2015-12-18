package com.larrainvial.logviewer.listener.readlog;

import com.larrainvial.logviewer.bo.Algo;
import com.larrainvial.logviewer.event.readlog.ReadFromDolarEvent;
import com.larrainvial.logviewer.event.stringtofix.DolarEvent;
import com.larrainvial.trading.emp.Controller;
import com.larrainvial.trading.emp.Event;
import com.larrainvial.trading.emp.Listener;
import java.util.Scanner;
import java.util.logging.Level;

public class ReadFromDolarListener implements Listener {

    public Algo algo;

    public ReadFromDolarListener(Algo algo){
        this.algo = algo;
    }

    @Override
    public synchronized void eventOccurred(Event event){

        try {

            ReadFromDolarEvent ev = (ReadFromDolarEvent) event;

            if(!ev.algo.nameAlgo.equals(algo.nameAlgo)) return;

            Scanner sc = new Scanner(algo.inputStreamDolar, "UTF-8");

            while (sc.hasNextLine()) {
                String message = sc.nextLine();
                Controller.dispatchEvent(new DolarEvent(algo, message));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}

