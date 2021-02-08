package com.gmail.velikiydan.task_3;

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
        JButton buttonRedStart = new JButton("Red");
        JButton buttonBlueStart = new JButton("Blue");
        JButton buttonStop = new JButton("Stop");
        this.canvas.initializeHoles(WIDTH, HEIGHT);
        buttonRedStart.addActionListener(e -> {
            Ball b = new Ball(canvas, Color.RED);
            canvas.add(b);

            BallThread thread = new BallThread(b);
            thread.start();
            System.out.println("red");
            System.out.println(thread.getPriority());
            System.out.println("Thread name = " +
                    thread.getName());
        });
        buttonBlueStart.addActionListener(e -> {
            for (int i = 0; i < 100; i++) {
                Ball b = new Ball(canvas, Color.BLUE);
                canvas.add(b);

                BallThread thread = new BallThread(b);
                thread.start();
                System.out.println("blue");
                System.out.println(thread.getPriority());
                System.out.println("Thread name = " +
                        thread.getName());
            }
        });
        buttonStop.addActionListener(e -> System.exit(0));


        buttonPanel.add(buttonRedStart);
        buttonPanel.add(buttonBlueStart);
        buttonPanel.add(buttonStop);

        content.add(buttonPanel, BorderLayout.SOUTH);
    }
}