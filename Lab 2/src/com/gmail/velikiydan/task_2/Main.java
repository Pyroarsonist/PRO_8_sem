package com.gmail.velikiydan.task_2;

import com.gmail.velikiydan.task_2.algorithms.fox.FoxAlgo;
import com.gmail.velikiydan.task_2.algorithms.tape.TapeAlgo;


public class Main {
    private static final int MATRIX_SIZE = 1_000;


    public static void main(String[] args) {
//        MultiplicationAlgorithm tapeAlgorithm = new TapeAlgo();
        MultiplicationAlgorithm foxAlgorithm = new FoxAlgo();

//        useAlgo(tapeAlgorithm);
        useAlgo(foxAlgorithm);
    }

    private static void useAlgo(MultiplicationAlgorithm algo) {
        System.out.printf("Executing %s algorithm\n", algo.getName());
        System.out.printf("Creating matrices with size: %d\n", MATRIX_SIZE);
        Result result = new Result(MATRIX_SIZE);
        int[][] aMatrix = MatrixHelper.generateRandomMatrix(MATRIX_SIZE, MATRIX_SIZE);
        int[][] bMatrix = MatrixHelper.generateRandomMatrix(MATRIX_SIZE, MATRIX_SIZE);

//        MatrixHelper.printMatrix(aMatrix);
//        System.out.println();
//        MatrixHelper.printMatrix(bMatrix);
//        System.out.println();


        long startTime = System.currentTimeMillis();
//        MatrixHelper.printMatrix(result.getMatrix());
        algo.multiplyMatrix(aMatrix, bMatrix, result.getMatrix());
        long endTime = System.currentTimeMillis();
        long difference = endTime - startTime;
        System.out.printf("Time taken: %dms\n", difference);

//        MatrixHelper.printMatrix(result.getMatrix());
    }
}
