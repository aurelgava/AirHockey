package com.example.demo2;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class HelloController {
    @FXML
    Pane podloga;
    @FXML
    private Rectangle desniGol;
    @FXML
    private Circle palica;
    @FXML
    private Circle pak;
    private double speedX=0,speedY=0;

    private AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            if(pak.getCenterX()+pak.getRadius()>=HelloApplication.stage.getWidth()  || (pak.getCenterX()-pak.getRadius()<=0)){
                speedX *= -1;
            }
            if(pak.getCenterY()+pak.getRadius()>=HelloApplication.stage.getHeight()  || (pak.getCenterY()-pak.getRadius()<=0)){
                speedY *= -1;
            }
            if(pak.intersects(desniGol.getBoundsInLocal())){
                System.out.println("GOLLLLL");
                System.out.println(desniGol.getBoundsInLocal());
            }
            pak.setCenterX(pak.getCenterX()+speedX);
            pak.setCenterY(pak.getCenterY()+speedY);
        }
    };

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
        palica.setCenterX(e.getX()-50);
        palica.setCenterY(e.getY()-50);
        //Circle c = new Circle(0,0,5);
        if(sudar(e,pak)){
            //System.out.println("SUDAR!!");
            speedX=5;
            speedY=0;
            timer.start();
        }
    }
}