package com.larrainvial.logviwer;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class  Repository  {

    public static HashMap<String, Algo> strategy = new HashMap<String, Algo>();
    public static ServerSocket serverSocket;
    public static HashMap<String, Socket> cliente = new HashMap<String, Socket>();
}