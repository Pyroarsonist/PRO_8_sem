package com.gmail.velikiydan.task_3;

import com.gmail.velikiydan.task_3.algorithms.fox.FoxAlgo;
import com.gmail.velikiydan.task_3.algorithms.tape.TapeAlgo;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


public class Main {
    private static final int MATRIX_SIZE = 2000;
    private static final int MATRIX_SIZE_STEP = 100;


    public static void main(String[] args) {
        MultiplicationAlgorithm tapeAlgorithm = new TapeAlgo();
        MultiplicationAlgorithm foxAlgorithm = new FoxAlgo();

        Map<Integer, Result> map = new TreeMap<>();
        for (int matrixSize = 2; matrixSize <= MATRIX_SIZE; matrixSize += MATRIX_SIZE_STEP) {
            Result result = new Result(matrixSize);
            useAlgo(tapeAlgorithm, matrixSize, result);
            useAlgo(foxAlgorithm, matrixSize, result);

            map.put(matrixSize, result);
        }

        System.out.println("Matrix sizes:");
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

    private static void useAlgo(MultiplicationAlgorithm algo, int matrixSize, Result result) {
//        System.out.printf("Executing %s algorithm\n", algo.getName());
//        System.out.printf("Creating matrices with size: %d\n", matrixSize);
        int[][] aMatrix = MatrixHelper.generateRandomMatrix(matrixSize, matrixSize);
        int[][] bMatrix = MatrixHelper.generateRandomMatrix(matrixSize, matrixSize);

//        MatrixHelper.printMatrix(aMatrix);
//        System.out.println();
//        MatrixHelper.printMatrix(bMatrix);
//        System.out.println();


        long startTime = System.currentTimeMillis();
//        MatrixHelper.printMatrix(result.getMatrix());
        algo.multiplyMatrix(aMatrix, bMatrix, result.getMatrix());
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
