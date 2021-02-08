package com.gmail.velikiydan.task_4.algorithms.fox;

import com.gmail.velikiydan.task_4.MultiplicationAlgorithm;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FoxAlgo implements MultiplicationAlgorithm {

    @Override
    public String getName() {
        return "Fox";
    }

    @Override
    public void multiplyMatrix(int[][] aMatrix, int[][] bMatrix, int[][] cResult, int poolSize) {
        ExecutorService exec = Executors.newFixedThreadPool(poolSize);

        Future f = exec.submit(new FoxThread(aMatrix, bMatrix, cResult,
                0, 0, 0, 0, 0, 0, aMatrix.length, exec));

        try {
            f.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
//        exec.shutdown();
    }
}
