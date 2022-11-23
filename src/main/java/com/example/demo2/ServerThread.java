package com.example.demo2;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread{
    public HelloController controller;

    public void run(){
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(1234);
            Socket s = ss.accept();
            ClientThread ct = new ClientThread();
            ct.konekcija = s;
            ct.controller = controller;
            ct.start();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
