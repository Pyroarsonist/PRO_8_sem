package com.gmail.velikiydan.task_2;

import java.util.ArrayList;

public class BallThread extends Thread {
    private final Ball b;

    public BallThread(Ball ball) {
        b = ball;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i < 10000; i++) {
                b.move();

                ArrayList<Hole> holes = this.b.getCanvas().getHoles();
                for (Hole h : holes) {
                    if (this.b.getX() > h.getX() - Hole.getXSize() && this.b.getX() < h.getX() + Hole.getXSize() && this.b.getY() > h.getY() - Hole.getYSize() && this.b.getY() < h.getY() + Hole.getYSize()) {
                        this.b.getCanvas().getBalls().remove(this.b);
                        this.b.getCanvas().updateCounter();
                        this.b.getCanvas().repaint();
                        break;
                    }
                }

//                System.out.println("Thread name = "
//                        + Thread.currentThread().getName());
                Thread.sleep(5);
            }
        } catch (InterruptedException ex) {
            System.err.println(ex.getMessage());
        }
    }
}