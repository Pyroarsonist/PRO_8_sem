package com.gmail.velikiydan.task_6;


public class AtomicCounter implements ICounter {
    private volatile int counter = 0;

    public synchronized void increment() {
        counter++;
    }

    public synchronized void decrement() {
        counter--;
    }

    public int getCounter() {
        return counter;
    }
}
