package com.gmail.velikiydan.task_3;


public class Assistant extends Thread {
    private final Journal journal;

    public Assistant(Journal j) {
        this.journal = j;
    }

    public void run() {
        while (!journal.isFull()) {
            journal.setNextMark();
        }
    }
}
