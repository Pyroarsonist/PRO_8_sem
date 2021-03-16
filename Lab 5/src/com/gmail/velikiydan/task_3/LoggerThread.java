package com.gmail.velikiydan.task_3;

public class LoggerThread extends Thread {
    Logger logger;
    boolean stop = false;

    LoggerThread(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void run() {
        try {
            while (!stop) {
                this.logger.PrintInfo();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}