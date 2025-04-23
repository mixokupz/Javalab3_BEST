package ru.nsu.ccfit.kupzov.lab3.model;

public class Point {
    int x;
    int y;
    Class<?> clazzObj;

    Point(int x, int y, Class<?> clazzObj){
        this.x = x;
        this.y = y;
        this.clazzObj = clazzObj;
    }
    Point(int x, int y){
        this.x = x;
        this.y = y;
        this.clazzObj = null;
    }
    void setClazzObj(Class<?> clazzObj){
        this.clazzObj = clazzObj;
    }

    public Class<?> getClazzObj() {
        return clazzObj;
    }
    int getX(){
        return x;
    }
    int getY(){
        return y;
    }
}
