package com.larrainvial.logviwer.listener;

import com.larrainvial.logviwer.Repository;
import com.larrainvial.logviwer.event.NewClientEvent;
import com.larrainvial.logviwer.event.ServerEvent;
import com.larrainvial.trading.emp.Controller;
import com.larrainvial.trading.emp.Event;
import com.larrainvial.trading.emp.Listener;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class NewClienteListener implements Listener {

    private Socket socket;
    private ServerSocket serverSocket;
    private DataOutputStream server;
    private DataInputStream cliente;
    private int idSessio;

    @Override
    public void eventOccurred(Event event){

        try {

            NewClientEvent ev = (NewClientEvent) event;

            System.out.print("Inicializando servidor... ");

            serverSocket = Repository.serverSocket;

            System.out.println("\t[OK]");
            int idSession = 0;

            socket = serverSocket.accept();

            Repository.cliente.put(socket.getInetAddress().toString(), socket);

            System.out.println("Nueva conexión entrante: " + socket);
            Controller.dispatchEvent(new ServerEvent(this, socket, idSession));
            idSession++;

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
