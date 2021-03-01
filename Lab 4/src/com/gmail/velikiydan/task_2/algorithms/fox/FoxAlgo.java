package com.gmail.velikiydan.task_2.algorithms.fox;

import com.gmail.velikiydan.task_2.MultiplicationAlgorithm;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FoxAlgo implements MultiplicationAlgorithm {

    private static final int
            POOL_SIZE = Runtime.getRuntime().availableProcessors();

    private final ExecutorService exec = Executors.newFixedThreadPool(POOL_SIZE);

    @Override
    public String getName() {
        return "Fox";
    }

    @Override
    public void multiplyMatrix(int[][] aMatrix, int[][] bMatrix, int[][] cResult) {
        Future f = exec.submit(new FoxThread(aMatrix, bMatrix, cResult,
                0, 0, 0, 0, 0, 0, aMatrix.length, this.exec));

        try {
            f.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
//        exec.shutdown();
    }
}
