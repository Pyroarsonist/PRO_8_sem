package com.gmail.velikiydan.task_5;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        HorThread h = new HorThread();
        VerThread v = new VerThread();


        h.setPriority(3);

        v.start();

//        h.join();
//        System.out.println("waited for horizontal thread");
        Thread.sleep(5000);
        h.start();


//        System.out.println("Main end");
    }
}
