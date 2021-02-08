package com.gmail.velikiydan.task_5;

public class HorThread extends Thread {

    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                System.out.print("-");
                Thread.sleep(5);
//                this.join(2000);
            }
        } catch (InterruptedException ex) {
            System.err.println(ex.getMessage());
        }
    }
}