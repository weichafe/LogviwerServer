package com.larrainvial.logviewer.listener.readlog;

import com.larrainvial.logviewer.bo.Algo;
import com.larrainvial.logviewer.event.readlog.ReadLogMkdLocalEvent;
import com.larrainvial.logviewer.event.stringtofix.MarketDataLocalEvent;
import com.larrainvial.trading.emp.Controller;
import com.larrainvial.trading.emp.Event;
import com.larrainvial.trading.emp.Listener;
import java.util.Scanner;
import java.util.logging.Level;

public class ReadLogMkdLocalListener implements Listener {

    public Algo algo;

    public ReadLogMkdLocalListener(Algo algo){
        this.algo = algo;
    }

    @Override
    public synchronized void eventOccurred(Event event){

        try {

            ReadLogMkdLocalEvent ev = (ReadLogMkdLocalEvent) event;

            if(!ev.algo.nameAlgo.equals(algo.nameAlgo)) return;

            Scanner sc = new Scanner(algo.inputStreamMkdLocal, "UTF-8");

            while (sc.hasNextLine()) {
                String message = sc.nextLine();
                Controller.dispatchEvent(new MarketDataLocalEvent(algo, message));
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public boolean verifyMessageFix(String message){

        if (message.indexOf("8=FIX.4.4") > -1 && message.indexOf("10=") > -1){
            return true;

        } else {
            return false;
        }

    }



}
