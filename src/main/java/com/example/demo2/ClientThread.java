package com.example.demo2;

import javafx.scene.input.MouseEvent;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientThread extends Thread{
    public Socket konekcija;
    public HelloController controller;
    private Tacka2D pozicijaMisha;
    ObjectInputStream ois;
    public void run(){
        try{
            ois = new ObjectInputStream(konekcija.getInputStream());
            while ((pozicijaMisha = (Tacka2D) ois.readObject()) != null) {
                System.out.println(pozicijaMisha);
                controller.pomeriPalicu2(pozicijaMisha);
            }
        }catch (Exception e){
            System.out.println(e);
        }

    }
}
