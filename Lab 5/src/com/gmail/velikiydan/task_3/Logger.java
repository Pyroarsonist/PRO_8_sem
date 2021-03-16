package com.gmail.velikiydan.task_3;

import java.util.ArrayList;

class Logger {
    ArrayList<String> logs;
    boolean isLogging = false;

    public synchronized void setLogs(ArrayList<String> logs) throws InterruptedException {
        while (isLogging) {
            wait();
        }
        this.logs = logs;
        isLogging = true;
        notifyAll();
    }

    public synchronized void PrintInfo() throws InterruptedException {
        while (!isLogging) {
            wait();
        }
        System.out.println("\n");
        for (String log : logs) {
            System.out.println(log);
        }
        isLogging = false;
        notifyAll();
    }
}
