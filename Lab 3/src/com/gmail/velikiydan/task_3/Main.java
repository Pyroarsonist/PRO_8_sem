package com.gmail.velikiydan.task_3;


public class Main {
    public static void main(String[] args) {
        Journal j = new Journal("PRO", 9, 3, 30);
        Lector l = new Lector(j, "Lector");
        Assistant a1 = new Assistant(j, "Assistant 1");
        Assistant a2 = new Assistant(j, "Assistant 2");
        Assistant a3 = new Assistant(j, "Assistant 3");

        Assistant[] assistants = new Assistant[]{l, a1, a2, a3};
        for (var a : assistants) {
            a.start();
        }


        try {
            for (var a : assistants) {
                a.join();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Journal is done");
        j.print();

    }
}
