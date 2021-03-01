package com.gmail.velikiydan.task_2;

import com.gmail.velikiydan.task_2.algorithms.fox.FoxAlgo;
import com.gmail.velikiydan.task_2.algorithms.fox_fork_and_join.ForkAndJoinFoxAlgo;
import com.gmail.velikiydan.task_2.algorithms.tape.TapeAlgo;
import com.google.gson.Gson;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    private static final int MATRIX_SIZE = 2000;
    private static final int MATRIX_SIZE_STEP = 5;


    public static void main(String[] args) {
        MultiplicationAlgorithm tapeAlgorithm = new TapeAlgo();
        MultiplicationAlgorithm foxAlgorithm = new FoxAlgo();
        MultiplicationAlgorithm forkAndJoinFoxAlgo = new ForkAndJoinFoxAlgo();

        Map<Integer, Result> map = new TreeMap<>();
        for (int matrixSize = 2; matrixSize <= MATRIX_SIZE; matrixSize += MATRIX_SIZE_STEP) {
//            System.out.println(matrixSize);
            Result result = new Result(matrixSize);
            useAlgo(tapeAlgorithm, matrixSize, result);
            useAlgo(foxAlgorithm, matrixSize, result);
            useAlgo(forkAndJoinFoxAlgo, matrixSize, result);

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

        System.out.println("\nFox (fork and join) durations (in ms):");

        for (Result result : map.values()) {
            System.out.printf("%4d ", result.getForkAndJoinFoxDuration());
        }

        ArrayList<MatrixDuration> mds = MatrixDuration.getDurations(map);

        String json = new Gson().toJson(mds);
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("data.json"), StandardCharsets.UTF_8))) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\nfinished");


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
        } else if (algo.getName().equals("ForkAndJoinFox")) {
            result.setForkAndJoinFoxDuration(difference);
        } else {
            result.setTapeDuration(difference);
        }
//        System.out.printf("Time taken: %dms\n", difference);

//        MatrixHelper.printMatrix(result.getMatrix());

    }
}
