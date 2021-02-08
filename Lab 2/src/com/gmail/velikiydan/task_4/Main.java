package com.gmail.velikiydan.task_4;


import com.gmail.velikiydan.task_4.algorithms.fox.FoxAlgo;
import com.gmail.velikiydan.task_4.algorithms.tape.TapeAlgo;

import java.util.Map;
import java.util.TreeMap;


public class Main {
    private static final int MATRIX_SIZE = 1000;
    private static final int
            POOL_SIZE = Runtime.getRuntime().availableProcessors();


    public static void main(String[] args) {
        MultiplicationAlgorithm tapeAlgorithm = new TapeAlgo();
        MultiplicationAlgorithm foxAlgorithm = new FoxAlgo();

        Map<Integer, Result> map = new TreeMap<>();
        for (int poolSize = 1; poolSize <= POOL_SIZE; poolSize++) {
            Result result = new Result(MATRIX_SIZE);
            useAlgo(tapeAlgorithm, poolSize, result);
            useAlgo(foxAlgorithm, poolSize, result);

            map.put(poolSize, result);
        }

        System.out.println("Proc numbers:");
        for (Integer matrixSize : map.keySet()) {
            System.out.printf("%4d ", matrixSize);
        }
        System.out.println("\nTape durations (in ms):");

        for (Result result : map.values()) {
            System.out.printf("%4d ", result.getTapeDuration());
        }

        System.out.println("\nFox durations (in ms):");

        for (Result result : map.values()) {
            System.out.printf("%4d ", result.getFoxDuration());
        }


    }

    private static void useAlgo(MultiplicationAlgorithm algo, int poolSize, Result result) {
//        System.out.printf("Executing %s algorithm\n", algo.getName());
//        System.out.printf("Creating matrices with size: %d\n", MATRIX_SIZE);
        int[][] aMatrix = MatrixHelper.generateRandomMatrix(MATRIX_SIZE, MATRIX_SIZE);
        int[][] bMatrix = MatrixHelper.generateRandomMatrix(MATRIX_SIZE, MATRIX_SIZE);

//        MatrixHelper.printMatrix(aMatrix);
//        System.out.println();
//        MatrixHelper.printMatrix(bMatrix);
//        System.out.println();


        long startTime = System.currentTimeMillis();
//        MatrixHelper.printMatrix(result.getMatrix());
        algo.multiplyMatrix(aMatrix, bMatrix, result.getMatrix(), poolSize);
        long endTime = System.currentTimeMillis();
        long difference = endTime - startTime;
        if (algo.getName().equals("Fox")) {
            result.setFoxDuration(difference);
        } else {
            result.setTapeDuration(difference);
        }
//        System.out.printf("Time taken: %dms\n", difference);

//        MatrixHelper.printMatrix(result.getMatrix());

    }
}
