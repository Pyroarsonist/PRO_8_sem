package com.gmail.velikiydan.task_2;

import java.util.Date;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {

        int size = 2;

        Vector<Vector<Integer>> a = new Vector<>();
        for (int i = 0; i < size; i++) {
            Vector<Integer> row = new Vector<>();
            for (int j = 0; j < size; j++) {
                row.add(10 * i + j);
            }
            a.add(row);
        }
        System.out.println("Created matrix 1:");
        System.out.println(a);

        Vector<Vector<Integer>> b = new Vector<>();
        for (int i = 0; i < size; i++) {
            Vector<Integer> row = new Vector<>();
            for (int j = 0; j < size; j++) {
                row.add(j - i);
            }
            b.add(row);
        }
        System.out.println("Created matrix 2:");
        System.out.println(b);


        Date start = new Date();
        Result r = new Result(a, b);
//        r.sequentialMultiply();
        r.foxMultiply();
        Date end = new Date();


        System.out.println("Result:");
        System.out.println(r.getMatrix());
        System.out.printf("Time taken: %dms", end.getTime() - start.getTime());

    }
}
