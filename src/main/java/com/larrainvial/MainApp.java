package com.larrainvial;

import com.larrainvial.logviwer.Repository;
import com.larrainvial.logviwer.event.NewClientEvent;
import com.larrainvial.logviwer.utils.Control;
import com.larrainvial.report.MainAppReport;
import com.larrainvial.trading.emp.Controller;

import java.net.ServerSocket;

public class MainApp {

    public static void main(String arg[]) {

        try {

            /*
            Repository.serverSocket = new ServerSocket(10578);
            Control.initialize();
            Control.initializaAll();
            Controller.dispatchEvent(new NewClientEvent(""));
            */

            MainAppReport.report();
            //com.larrainvial.sellside.MainApp.sellside();



        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void initializeClient(){


    }

}