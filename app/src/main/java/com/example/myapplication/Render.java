package com.example.myapplication;

import java.util.ArrayList;

import java.lang.Math ;

public class Render {

    // set degx and degy and then call render then you can get new x and y

    public void setAccelerations(double Ax,double Ay, double Az){
        this.Ax = Ax;
        this.Ay = Ay;
        this.Az = Az;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public void setMuk(double muk) {
        this.muk = muk;
    }

    public void setMus(double mus) {
        this.mus = mus;
    }

    public void setMaxX(double maxX) {
        this.maxX = maxX;
    }

    public void setMaxY(double maxY) {
        this.maxY = maxY;
    }

    public void setMinX(double minX) {
        this.minX = minX;
    }

    public void setMinY(double minY) {
        this.minY = minY;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void render(double time, double omegaX, double omegaY){
        this.degX += time * omegaX;
        this.degY += time * omegaY;
        double Ax, Ay, Az;
        Ax = 9.8 * Math.sin(degX);
        Az = 9.8 * Math.cos(degX);
        ArrayList<Double> ret = render1D(Ax, Az, vx, time, x);
        this.x = ret.get(0);
        this.vx = ret.get(1);
        Ay = 9.8 * Math.sin(degY);
        Az = 9.8 * Math.cos(degY);
        ret  = render1D(Ay, Az, vy, time, y);
        this.y = ret.get(0);
        this.vy = ret.get(1);

        if(this.x > maxX){
            this.x = maxX ;
            this.vx = 0.0;
        }
        else if(this.x < minX){
            this.x = minX ;
            this.vx = 0.0;
        }
        if(this.y > maxY){
            this.y = maxY ;
            this.vy = 0.0;
        }
        else if(this.y < minY){
            this.y = minY;
            this.vy = 0.0;
        }
    }

    public void render(double time){
        ArrayList<Double> ret = render1D(Ax, Az, vx, time, x);
        this.x = ret.get(0);
        this.vx = ret.get(1);
        ret  = render1D(Ay, Az, vy, time, y);
        this.y = ret.get(0);
        this.vy = ret.get(1);


        if(this.x > maxX){
            this.x = maxX ;
            this.vx = 0.0;
        }

        else if(this.x < minX){
            this.x = minX ;
            this.vx = 0.0;
        }

        if(this.y > maxY){
            this.y = maxY ;
            this.vy = 0.0;
        }
        else if(this.y < minY){
            this.y = minY;
            this.vy = 0.0;
        }
    }

    private ArrayList<Double> render1D(double A, double Az, double v, double time, double prev){
        // mass of object has no influence so we assume it 1
        ArrayList<Double> ret = new ArrayList<Double>();
        double newV ;
        double m = 1;
        double fVertical = this.gravity * Az * m;
        double fHorizontal = this.gravity * A * m;
        double fmu ;
        if(v == 0.0){
            if( Math.abs(fVertical * this.mus) >= Math.abs(fHorizontal)){
                ret.add(0,prev);
                ret.add(1,0.0);
                return ret;
            }
            else{
                fmu = Math.abs(fVertical * this.muk);
                if(fHorizontal > 0)
                    fmu = -fmu;
                double a = fHorizontal+fmu;
                return this.move(prev,v,a,time);
            }
        }
        else{
            fmu = Math.abs(fVertical * this.muk);
            if(v > 0)
                fmu = -fmu;

            double a = fmu + fHorizontal;
            ArrayList<Double> t = this.move(prev,v,a,time);
            newV = t.get(1);
            if(newV * v > 0){
                return t;
            }
            else{
                double changeTime = (-v)/a ;
                t = this.move(prev,v,a,changeTime);
                double changePos = t.get(0) ;
                double deltaTime = time-changeTime;
                if( Math.abs(fVertical*this.mus) > Math.abs(fHorizontal)){
                    ret.add(0, changePos);
                    ret.add(1, 0.0);
                    return ret;
                }
                else{
                    fmu = -fmu;
                    a = fHorizontal + fmu;
                    return this.move(changePos,0.0,a,deltaTime);
                }
            }
        }
    }

    private ArrayList<Double>  move(double x0, double v0 , double a, double time){
        double  newV = a * time + v0;
        double newPos = (newV + v0)*time/2 + x0;
        ArrayList<Double> ret = new ArrayList<Double>();
        ret.add(0,newPos);
        ret.add(1,newV);
        return ret;
    }

    private double Ax = 0;
    private double Ay = 0;
    private double Az = 9.8;

    private double degX = 0;
    private double degY = 0;

    private double gravity = 10;
    private double muk = 0.1;
    private double mus = 0.2;

    private double vx = 0;
    private double vy = 0;

    private double x = 0;
    private double y = 0;

    private double maxX = 1020;
    private double maxY = 1720;
    private double minX = -10;
    private double minY = -10;

}
