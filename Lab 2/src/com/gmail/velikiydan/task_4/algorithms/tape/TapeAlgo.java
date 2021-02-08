package com.gmail.velikiydan.task_4.algorithms.tape;

import com.gmail.velikiydan.task_4.MultiplicationAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TapeAlgo implements MultiplicationAlgorithm {

    @Override
    public String getName() {
        return "Tape";
    }

    @Override
    public void multiplyMatrix(int[][] aMatrix, int[][] bMatrix, int[][] cMatrix, int poolSize) {
        ExecutorService exec = Executors.newFixedThreadPool(poolSize);

        int rowsCount = aMatrix.length;

        ArrayList<Future> futures = new ArrayList<>();

        for (int i = 0; i < rowsCount; i++) {
            int[] row = aMatrix[i];


            for (int j = 0; j < rowsCount; j++) {
                int[] column = getColumn(bMatrix, j);

                TapeThread thread = new TapeThread(i, row, cMatrix);
                thread.setColumn(column);
                thread.setJ(j);

                Future f = exec.submit(thread);
                futures.add(f);

            }
        }


        for (Future f : futures) {
            try {
                f.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    private int[] getColumn(int[][] matrix, int column) {
        return Arrays.stream(matrix).mapToInt(ints -> ints[column]).toArray();
    }
}
