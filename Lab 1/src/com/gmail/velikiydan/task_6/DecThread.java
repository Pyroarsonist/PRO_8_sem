package com.gmail.velikiydan.task_6;

import java.util.concurrent.locks.ReentrantLock;

public class DecThread extends Thread {
    private final ICounter counter;
    private ReentrantLock lock;

    public DecThread(ICounter counter, ReentrantLock lock) {
        this.counter = counter;
        this.lock = lock;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            lock.lock();
            counter.decrement();
            lock.unlock();
        }
    }
}