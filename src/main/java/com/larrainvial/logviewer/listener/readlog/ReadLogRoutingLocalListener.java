package com.larrainvial.logviewer.listener.readlog;

import com.larrainvial.logviewer.bo.Algo;
import com.larrainvial.logviewer.event.readlog.ReadLogRoutingLocalEvent;
import com.larrainvial.logviewer.event.stringtofix.RoutingLocalEvent;
import com.larrainvial.trading.emp.Controller;
import com.larrainvial.trading.emp.Event;
import com.larrainvial.trading.emp.Listener;
import java.util.Scanner;

public class ReadLogRoutingLocalListener implements Listener {

    public Algo algo;

    public ReadLogRoutingLocalListener(Algo algo){
        this.algo = algo;
    }

    @Override
    public synchronized void eventOccurred(Event event){

        try {

            ReadLogRoutingLocalEvent ev = (ReadLogRoutingLocalEvent) event;

            if (!ev.algo.nameAlgo.equals(algo.nameAlgo)) return;

            Scanner sc = new Scanner(algo.inputStreamRoutingLocal, "UTF-8");

            while (sc.hasNextLine()) {

                String message = sc.nextLine();
                Controller.dispatchEvent(new RoutingLocalEvent(algo, message));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
