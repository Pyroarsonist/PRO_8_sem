package com.gmail.velikiydan.task_3;

import java.awt.*;

public class BallThread extends Thread {
    private final Ball b;

    public BallThread(Ball ball) {
        b = ball;

        this.setPriority(ball.getColor() == Color.RED ? 10 : 1);
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i < 10000; i++) {
                b.move();
//                System.out.println("Thread name = "
//                        + Thread.currentThread().getName());
                Thread.sleep(20);
            }
        } catch (InterruptedException ex) {
            System.err.println(ex.getMessage());
        }
    }
}