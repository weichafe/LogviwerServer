package com.larrainvial;

import com.larrainvial.report.MainAppReport;

public class MainApp {

    public static void main(String arg[]) {

        try {


            //Repository.serverSocket = new ServerSocket(10578);
            //Control.initialize();
            //Control.initializaAll();
            //com.larrainvial.sellside.MainApp.sellside();
             MainAppReport.report();


        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void initializeClient(){


    }

}