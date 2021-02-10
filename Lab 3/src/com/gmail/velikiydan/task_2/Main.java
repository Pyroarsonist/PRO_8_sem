package com.gmail.velikiydan.task_2;

public class Main {
    private static final int SIZE = 100;
    private static final int MAX_SLEEP = 300;

    public static void main(String[] args) {

        Drop drop = new Drop();
        Producer p = new Producer(drop, SIZE, MAX_SLEEP);
        Consumer c = new Consumer(drop, SIZE, MAX_SLEEP);
        p.start();
        c.start();
    }
}
