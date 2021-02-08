package com.gmail.velikiydan.task_4;

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
                try {
                    b.move();
                } catch (Exception e) {
                    break;
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