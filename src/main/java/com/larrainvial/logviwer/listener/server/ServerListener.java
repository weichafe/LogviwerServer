package com.larrainvial.logviwer.listener.server;

import com.larrainvial.logviwer.event.ServerEvent;
import com.larrainvial.logviwer.model.ModelMarketData;
import com.larrainvial.trading.emp.Event;
import com.larrainvial.trading.emp.Listener;
import java.io.*;
import java.net.Socket;

public class ServerListener implements Listener {

    private Socket socket;
    private DataOutputStream server;
    private DataInputStream cliente;
    private int idSessio;

    @Override
    public void eventOccurred(Event event) {

        try {

            ServerEvent ev = (ServerEvent) event;

            this.socket = ev.socket;
            this.idSessio = ev.idSession;

            server = new DataOutputStream(socket.getOutputStream());
            cliente = new DataInputStream(socket.getInputStream());

            String accion = cliente.readUTF();


            if (accion.equals("hola")) {
                System.out.println("El cliente con idSesion " + this.idSessio + " saluda");

            }

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void desconnectar() {

        try {

            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
