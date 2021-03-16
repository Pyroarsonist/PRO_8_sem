package com.gmail.velikiydan.task_2;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {

        int threads = 10;
        ArrayList<Future<ArrayList<Double>>> tasks = new ArrayList<>();
        ExecutorService pool = Executors.newFixedThreadPool(threads);

        double queue = 0;
        double failure = 0;
        for (int i = 0; i < threads; i++) {
            tasks.add(pool.submit(createModel()));
        }

        for (Future<ArrayList<Double>> task : tasks) {
            try {
                var t = task.get();
                queue += t.get(0);
                failure += t.get(1);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Queue avg length: %f\n", queue / tasks.size());
        System.out.printf("Failure probability: %f\n", failure / tasks.size());
        pool.shutdown();
    }

    public static Model createModel() {
        Create c = new Create("creator", "exp", 2);
        Process p1 = new Process("process1", "exp", 2.5, 1);
        c.nextElement(p1);
        p1.nextElement(null, 1);

        List<Element> list = Arrays.asList(c, p1);
        return new Model(list, 10000.0);
    }
}