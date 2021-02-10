package com.gmail.velikiydan.task_2;

import java.util.Random;

public class Consumer extends Thread {
    private final Drop drop;
    private int maxSize;
    private int maxSleep;

    Consumer(Drop drop, int maxSize, int maxSleep) {
        this.drop = drop;
        this.maxSize = maxSize;
        this.maxSleep=maxSleep;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int number = drop.take();
             number != ++maxSize;
             number = drop.take()) {
            System.out.format("MESSAGE RECEIVED: %s%n", number);
            try {
                Thread.sleep(random.nextInt(maxSleep));
            } catch (InterruptedException ignored) {
            }
        }
    }
}