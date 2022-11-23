package com.example.demo2;

import java.io.Serializable;

public class Tacka2D implements Serializable {
    public double x,y;

    public Tacka2D(double x, double y) {
        this.x=x;
        this.y=y;
    }

    @Override
    public String toString() {
        return "("+x+" , "+y+")";
    }
}
