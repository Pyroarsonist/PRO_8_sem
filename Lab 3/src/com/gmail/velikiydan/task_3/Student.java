package com.gmail.velikiydan.task_3;

public class Student {
    private static int seqId = 0;
    private final int group;
    private final int id;

    public Student(int group) {
        seqId++;
        this.group = group;
        this.id = seqId;
    }

    public int getId() {
        return id;
    }

    public int getGroup() {
        return group;
    }
}
