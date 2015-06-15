package com.larrainvial.logviwer.listener.server;

import com.larrainvial.logviwer.Repository;
import com.larrainvial.logviwer.event.ValidateClienteEvent;
import com.larrainvial.trading.emp.Event;
import com.larrainvial.trading.emp.Listener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;


public class ValidateClienteListener implements Listener {

    @Override
    public void eventOccurred(Event event) {

        try {

            ValidateClienteEvent ev = (ValidateClienteEvent) event;

            System.out.println("Cliente nuevo : " + ev.users + " " + ev.password);
            System.out.println("port Server " + ev.socket.getLocalPort());
            System.out.println("ip cliente  " + ev.socket.getInetAddress());
            System.out.println("port cliente " + ev.socket.getPort());


            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(ev.socket.getInputStream()));

            while (true) {

                if (bufferedReader.readLine().equals("by")) {

                    Repository.cliente.remove(ev.socket.getInetAddress().toString());
                    System.out.println("Cliente desconectado : " + ev.users);
                    bufferedReader.close();
                    ev.socket.close();

                    break;

                }
            }


        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
