package com.gmail.velikiydan.task_6;

import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Counter counter = new Counter();
//        AtomicCounter counter = new AtomicCounter();
//        AtomicCounterWithAtomicValue counter = new AtomicCounterWithAtomicValue();

        ReentrantLock lock = new ReentrantLock();

        DecThread decThread = new DecThread(counter,lock);
        IncThread incThread = new IncThread(counter,lock);


            decThread.start();
            incThread.start();


        decThread.join();
        incThread.join();

        System.out.println(counter.getCounter());
    }
}
