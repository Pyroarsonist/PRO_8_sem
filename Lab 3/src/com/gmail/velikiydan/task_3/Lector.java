package com.gmail.velikiydan.task_3;

public class Lector extends Assistant {
    public Lector(Journal j, String name) {
        super(j, name);
        setPriority(Thread.NORM_PRIORITY + 1);
    }
}
