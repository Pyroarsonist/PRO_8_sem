package com.gmail.velikiydan.task_4;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BallCanvas extends JPanel {
    private final ArrayList<Ball> balls = new ArrayList<>();
    private final ArrayList<Hole> holes = new ArrayList<>();
    private final JLabel counter = new JLabel("Balls count: " + this.getBalls().size());

    public void updateCounter() {
        this.counter.setText("Balls count: " + this.getBalls().size());
    }

    public void add(Ball b) {
        this.balls.add(b);
        this.updateCounter();
    }

    public void initializeHoles(int width, int height) {
        Hole leftTopHole = new Hole(0, height);
        Hole rightTopHole = new Hole(width, height);
        Hole leftBottomHole = new Hole(0, 0);
        Hole rightBottomHole = new Hole(width, 0);
        holes.add(leftTopHole);
        holes.add(rightTopHole);
        holes.add(leftBottomHole);
        holes.add(rightBottomHole);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        paintHoles(g2);
        paintBalls(g2);
    }

    private void paintHoles(Graphics2D g2) {
        for (Hole h : holes) {
            h.draw(g2);
        }
    }

    private void paintBalls(Graphics2D g2) {
        for (Ball b : balls) {
            b.draw(g2);
        }
    }

    public ArrayList<Hole> getHoles() {
        return holes;
    }

    public ArrayList<Ball> getBalls() {
        return balls;
    }

    public JLabel getCounter() {
        return counter;
    }
}