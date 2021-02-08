package com.gmail.velikiydan.task_4;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Hole {
    private static final int xSize = 30;
    private static final int ySize = 30;
    private final int x;
    private final int y;


    public Hole(int width, int height) {
        this.x = width != 0 ? Math.abs(width - xSize) : width;
        this.y = height != 0 ? Math.abs(height - 4 * ySize) : height;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fill(new Ellipse2D.Double(x, y, xSize, ySize));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static int getXSize() {
        return xSize;
    }

    public static int getYSize() {
        return ySize;
    }
}
