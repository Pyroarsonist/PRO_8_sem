package com.gmail.velikiydan.task_4;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

class Ball {
    private final BallCanvas canvas;
    private static final int xSize = 20;
    private static final int ySize = 20;
    private final Color color;
    private int x;
    private int y;
    private int dx = 1;
    private int dy = 1;


    public Ball(BallCanvas c, Color color) {
        this.canvas = c;

        x = (int) (this.canvas.getWidth() / 2 * 0.8);
        y = this.canvas.getHeight() / 2;

        this.color=color;

//        if (Math.random() < 0.1) {
//            color = Color.RED;
//        } else {
//            color = Color.BLUE;
//        }
    }


    public void draw(Graphics2D g2) {
        g2.setColor(this.color);
        g2.fill(new Ellipse2D.Double(x, y, xSize, ySize));
    }

    public void move() throws Exception {
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

        ArrayList<Hole> holes = this.canvas.getHoles();
        for (Hole h : holes) {
            if (x > h.getX() - Hole.getXSize() && x < h.getX() + Hole.getXSize() && y > h.getY() - Hole.getYSize() && y < h.getY() + Hole.getYSize()) {
                this.canvas.getBalls().remove(this);
                this.canvas.updateCounter();
                throw new Exception("end");
            }
        }


        this.canvas.repaint();

    }

    public Color getColor() {
        return color;
    }
}