package com.gmail.velikiydan.task_2;

import java.util.Random;

public class Producer extends Thread {
    private final Drop drop;
    private final int[] numbers;
    private int maxSleep;

    Producer(Drop drop, int arraySize, int maxSleep) {
        this.drop = drop;
        this.numbers = new int[arraySize];

        for (int i = 0; i < arraySize; i++) {
            this.numbers[i] = i;
        }
        this.maxSleep = maxSleep;
    }

    @Override
    public void run() {
        Random random = new Random();

        for (int number : numbers) {
            drop.put(number);
            try {
                Thread.sleep(random.nextInt(maxSleep));
            } catch (InterruptedException ignored) {
            }
        }
        drop.put(numbers.length);
    }
}