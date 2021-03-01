package com.gmail.velikiydan.task_2.algorithms.fox_fork_and_join;

import com.gmail.velikiydan.task_2.MultiplicationAlgorithm;

import java.util.concurrent.*;

public class ForkAndJoinFoxAlgo implements MultiplicationAlgorithm {

    private static final int
            POOL_SIZE = Runtime.getRuntime().availableProcessors();

    @Override
    public String getName() {
        return "ForkAndJoinFox";
    }

    @Override
    public void multiplyMatrix(int[][] firstMatrix, int[][] secondMatrix, int[][] resultMatrix) {
        ForkJoinPool pool = new ForkJoinPool(POOL_SIZE);

        pool.submit(new ForkAndJoinFoxTask(firstMatrix, secondMatrix, resultMatrix,
                0, 0, 0, 0, 0, 0, firstMatrix.length)).join();
    }

}
