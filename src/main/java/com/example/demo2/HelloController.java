package com.example.demo2;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class HelloController {
    private static Socket s;
    private static ObjectOutputStream oos;
    public Circle palica2;
    @FXML
    Pane podloga;
    @FXML
    private Rectangle desniGol;
    @FXML
    private Circle palica;
    @FXML
    private Circle pak;
    @FXML
    private ImageView slikaGol;
    private double speedX=0,speedY=0;
    private double koefTrenja=0.6;
    private double deltaSpeedX = 0.1;
    private double deltaSpeedY = 0.1;
    private double masaPaka=2;
    private final double gravity=9.81;
    private AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            if(pak.getCenterX()+pak.getRadius()>=HelloApplication.stage.getWidth()  || (pak.getCenterX()-pak.getRadius()<=0)){
                speedX *= -1;
            }
            if(pak.getCenterY()+pak.getRadius()>=HelloApplication.stage.getHeight()  || (pak.getCenterY()-pak.getRadius()<=0)){
                speedY *= -1;
            }
            if(pak.intersects(desniGol.getBoundsInParent())){
                System.out.println("GOLLLLL");
                slikaGol.setVisible(true);
            }
            pak.setCenterX(pak.getCenterX()+speedX);
            pak.setCenterY(pak.getCenterY()+speedY);


            if(Math.abs(speedX)<Math.abs(deltaSpeedX)*1.5)
                speedX=0;
            else
                speedX+= -Math.signum(speedX) * deltaSpeedX;

            if(Math.abs(speedY)<Math.abs(deltaSpeedY)*1.5)
                speedY=0;
            else
                speedY+= -Math.signum(speedY) * deltaSpeedY;

            if(speedX==0 && speedY==0) timer.stop();

            //System.out.println(speedX);
            //System.out.println(speedY);

        }
    };

    public static void konektujSe() {
        try {
            s = new Socket("10.91.1.212",1234);
            oos = new ObjectOutputStream(s.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void crtaj(ActionEvent actionEvent) {
        Line l1 = new Line(100,100,50,150);
        Line l2 = new Line(100,100,150,150);
        podloga.getChildren().addAll(l1,l2);

    }
    private boolean sudar(MouseEvent e, Circle pak){
        double d = Math.sqrt(Math.pow(e.getX()-pak.getCenterX(),2) + Math.pow(e.getY()-pak.getCenterY(),2));

        if(d<palica.getRadius()+pak.getRadius())
            return true;
        else
            return false;
    }
    public void pomeriPalicu(MouseEvent e) {
        palica.setCenterX(e.getX());
        palica.setCenterY(e.getY());
        try {
            if(oos!=null)
                oos.writeObject(new Tacka2D(e.getX(),e.getY()));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        //Circle c = new Circle(0,0,5);
        if(sudar(e,pak)){
            //System.out.println("SUDAR!!");
            slikaGol.setVisible(false);
            speedX=(pak.getCenterX() - e.getX())*0.5;
            speedY=(pak.getCenterY() - e.getY())*0.5;
            //speedX=5;
            //speedY=5;
            timer.start();

        }
    }

    public void pomeriPalicu2(Tacka2D pozicijaMisha) {
        palica2.setCenterX(pozicijaMisha.x);
        palica2.setCenterY(pozicijaMisha.y);
    }


    public void konektujSe(ActionEvent actionEvent) {
        konektujSe();
    }

    public void hoverNadDugmetomKonekcijeUlaz(MouseEvent mouseEvent) {
        HelloApplication.scene.setCursor(Cursor.OPEN_HAND);
    }

    public void hoverNadDugmetomKonekcijeIzlaz(MouseEvent mouseEvent) {
        HelloApplication.scene.setCursor(Cursor.NONE);
    }
}