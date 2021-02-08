package com.gmail.velikiydan.task_2;


public class BlockThread extends Thread {

    private int x;
    private int y;
    private int aValue = 0;
    private int a = 0;
    private int b = 0;
    private int c = 0;

    public BlockThread(int x, int y, int a, int b, int c) {
        this.setX(x);
        this.setY(y);

        this.setaValue(a);

        this.setA(a);
        this.setB(b);
        this.setC(c);
    }


    @Override
    public void run() {
        setC(getC() + getA() * getB());
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getaValue() {
        return aValue;
    }

    public void setaValue(int aValue) {
        this.aValue = aValue;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }
}
