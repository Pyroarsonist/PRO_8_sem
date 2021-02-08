package com.gmail.velikiydan.task_3;

public class Lector extends Assistant {
    public Lector(Journal j) {
        super(j);
        setPriority(Thread.NORM_PRIORITY + 1);
    }
}
