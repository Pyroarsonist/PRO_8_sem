package com.gmail.velikiydan.task_1;

import java.util.Date;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {
        Vector<Vector<Integer>> matrix1 = new Vector<>();
        for (int i = 0; i < 100; i++) {
            Vector<Integer> row = new Vector<>();
            for (int j = 0; j < 100; j++) {
                row.add(10 * i + j);
            }
            matrix1.add(row);
        }
        System.out.println("Created matrix 1:");
        System.out.println(matrix1);

        Vector<Vector<Integer>> matrix2 = new Vector<>();
        for (int i = 0; i < 100; i++) {
            Vector<Integer> row = new Vector<>();
            for (int j = 0; j < 100; j++) {
                row.add(j - i);
            }
            matrix2.add(row);
        }
        System.out.println("Created matrix 2:");
        System.out.println(matrix2);

        Date start = new Date();
        Result r = new Result(matrix1, matrix2);
//        r.sequentialMultiply();
        r.tapeMultiply();
        Date end = new Date();


        System.out.println("Result:");
        System.out.println(r.getMatrix());
        System.out.printf("Time taken: %dms", end.getTime() - start.getTime());

    }
}
