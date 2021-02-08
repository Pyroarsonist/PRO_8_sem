package com.gmail.velikiydan.task_4;

import javax.swing.*;
import java.awt.*;

public class BounceFrame extends JFrame {
    private final BallCanvas canvas;
    public static final int WIDTH = 450;
    public static final int HEIGHT = 350;

    public BounceFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Bounce program");
        this.canvas = new BallCanvas();
        System.out.println("In Frame Thread name = "
                + Thread.currentThread().getName());
        Container content = this.getContentPane();
        this.canvas.add(this.canvas.getCounter());
        content.add(this.canvas, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);
        JButton buttonStop = new JButton("Stop");
        this.canvas.initializeHoles(WIDTH, HEIGHT);

        buttonStop.addActionListener(e -> System.exit(0));

        buttonPanel.add(buttonStop);

        content.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void initBalls() {
        Ball redBall = new Ball(canvas, Color.RED);
        canvas.add(redBall);

        BallThread redThread = new BallThread(redBall);
        System.out.println("red");
        System.out.println(redThread.getPriority());
        System.out.println("Thread name = " +
                redThread.getName());
//        });
//        buttonBlueStart.addActionListener(e -> {
        Ball blueBall = new Ball(canvas, Color.BLUE);
        canvas.add(blueBall);

        BallThread blueThread = new BallThread(blueBall);
        System.out.println("blue");
        System.out.println(blueThread.getPriority());
        System.out.println("Thread name = " +
                blueThread.getName());
//        });

        redThread.start();
        try {
            redThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        blueThread.start();
    }
}