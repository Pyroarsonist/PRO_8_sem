package com.gmail.velikiydan.task_6;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounterWithAtomicValue implements ICounter {
    private AtomicInteger counter = new AtomicInteger(0);

    public  void increment() {
        counter.incrementAndGet();
    }

    public  void decrement() {
        counter.decrementAndGet();
    }

    public int getCounter() {
        return counter.get();
    }
}
