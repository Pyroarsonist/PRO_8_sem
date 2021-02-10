package com.gmail.velikiydan.task_3;


public class Assistant extends Thread {

    private final Journal journal;
    private final String name;

    public Assistant(Journal j, String name) {
        this.journal = j;
        this.name = name;
    }

    public void run() {
        while (!journal.isFull()) {
            journal.setNextMark();
        }
        System.out.printf("%s ended\n", name);
    }
}
