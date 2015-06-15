package com.larrainvial.logviwer.listener.server;

import com.larrainvial.logviwer.Repository;
import com.larrainvial.logviwer.event.NewClientEvent;
import com.larrainvial.logviwer.event.SendDatatoClientEvent;
import com.larrainvial.logviwer.event.ValidateClienteEvent;
import com.larrainvial.logviwer.utils.PropertiesFile;
import com.larrainvial.logviwer.vo.ClientVO;
import com.larrainvial.trading.emp.Controller;
import com.larrainvial.trading.emp.Event;
import com.larrainvial.trading.emp.Listener;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class NewClienteListener implements Listener {

    private Socket socket;
    private ServerSocket serverSocket;
    private DataOutputStream server;
    private DataInputStream cliente;
    PropertiesFile users;

    @Override
    public void eventOccurred(Event event){

        try {

            NewClientEvent ev = (NewClientEvent) event;

            serverSocket = Repository.serverSocket;
            System.out.println("server \t[OK]");

            socket = serverSocket.accept();
            users = new PropertiesFile("src/main/resources/users.properties");

            ClientVO clientVO = new ClientVO();
            clientVO.socket = socket;

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream printStream = new PrintStream(socket.getOutputStream());

            String user = bufferedReader.readLine().toLowerCase();
            String password = bufferedReader.readLine().toLowerCase();

            String auxUsers = users.getPropertiesString(user + ".user");
            String auxPass = users.getPropertiesString(user + ".password");



            if (user.equals(auxUsers) && password.equals(auxPass)) {

                printStream.println("true");
                printStream.flush();

                Repository.cliente.put(socket.getInetAddress().toString(), clientVO);

                Controller.dispatchEvent(new SendDatatoClientEvent(this, clientVO));
                Controller.dispatchEvent(new ValidateClienteEvent(this, socket, user, password));

                if(bufferedReader.readLine().equals("receivedAllData")){
                    clientVO.sendDataInicial = true;
                    System.out.println("sendDataInicial " + clientVO.sendDataInicial);
                }

            } else {

                printStream.println("false");
                printStream.flush();
                printStream.close();
                socket.close();
                System.out.println("Cliente no valido : " + user + " " + password + serverSocket.getLocalPort());

            }



        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
