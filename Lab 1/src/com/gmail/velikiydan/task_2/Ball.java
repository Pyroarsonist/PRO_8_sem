package com.gmail.velikiydan.task_2;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Random;

class Ball {
    private final BallCanvas canvas;
    private static final int xSize = 20;
    private static final int ySize = 20;
    private int x;
    private int y;
    private int dx = 2;
    private int dy = 2;


    public Ball(BallCanvas c) {
        this.canvas = c;

        if (Math.random() < 0.5) {
            x = new Random().nextInt(this.canvas.getWidth());
            y = 100;
        } else {
            x = 100;
            y = new Random().nextInt(this.canvas.getHeight());
        }
    }


    public void draw(Graphics2D g2) {
        g2.setColor(Color.darkGray);
        g2.fill(new Ellipse2D.Double(x, y, xSize, ySize));
    }

    public void move() {
        x += dx;
        y += dy;
        if (x < 0) {
            x = 0;
            dx = -dx;
        }
        if (x + xSize >= this.canvas.getWidth()) {
            x = this.canvas.getWidth() - xSize;
            dx = -dx;
        }
        if (y < 0) {
            y = 0;
            dy = -dy;
        }
        if (y + ySize >= this.canvas.getHeight()) {
            y = this.canvas.getHeight() - ySize;
            dy = -dy;
        }

        this.canvas.repaint();

    }

    public BallCanvas getCanvas() {
        return canvas;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}