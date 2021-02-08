package com.gmail.velikiydan.task_5;

public class VerThread extends Thread {

    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                System.out.print("|");
                Thread.sleep(5);
            }
        } catch (InterruptedException ex) {
            System.err.println(ex.getMessage());
        }
    }
}