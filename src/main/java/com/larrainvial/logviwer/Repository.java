package com.larrainvial.logviwer;

import com.larrainvial.logviwer.vo.ClientVO;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class  Repository  {

    public static HashMap<String, Algo> strategy = new HashMap<String, Algo>();
    public static ServerSocket serverSocket;
    public static HashMap<String, ClientVO> cliente = new HashMap<String, ClientVO>();
    public static long timer = 0;
}