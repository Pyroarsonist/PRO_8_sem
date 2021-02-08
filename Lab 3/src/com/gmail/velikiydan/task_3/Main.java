package com.gmail.velikiydan.task_3;

public class Main {
    public static void main(String[] args) {
        Journal j = new Journal("PRO", 9, 90);
        Lector l = new Lector(j);
        Assistant a1 = new Assistant(j);
        Assistant a2 = new Assistant(j);
        Assistant a3 = new Assistant(j);

        System.out.println(j);
        l.start();
        a1.start();
        a2.start();
        a3.start();
    }
}
