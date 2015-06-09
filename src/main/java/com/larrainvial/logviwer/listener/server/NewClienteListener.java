package com.larrainvial.logviwer.listener.server;

import com.larrainvial.logviwer.Repository;
import com.larrainvial.logviwer.event.NewClientEvent;
import com.larrainvial.logviwer.event.SendDatatoClientEvent;
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
    private int idSession;

    @Override
    public void eventOccurred(Event event){

        try {

            NewClientEvent ev = (NewClientEvent) event;

            System.out.print("Inicializando servidor... ");

            serverSocket = Repository.serverSocket;

            System.out.println("\t[OK]");
            idSession = 0;

            socket = serverSocket.accept();


            //this.idSessio = ev.idSession;

            server = new DataOutputStream(socket.getOutputStream());
            cliente = new DataInputStream(socket.getInputStream());

            String accion = cliente.readUTF();


            /*
            if (accion.equals("hola")) {
                System.out.println("El cliente con idSesion " + this.idSessio + " saluda");
            }
            */



            Controller.dispatchEvent(new SendDatatoClientEvent(this, socket));

            Repository.cliente.put(socket.getInetAddress().toString(), socket);

            System.out.println("Nueva conexión entrante: " + socket);


            idSession++;

        }catch (Exception e){
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
