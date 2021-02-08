package com.gmail.velikiydan.task_1.algorithms.tape;

import com.gmail.velikiydan.task_1.MultiplicationAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;

public class TapeAlgo implements MultiplicationAlgorithm {

    @Override
    public String getName() {
        return "Tape";
    }

    @Override
    public void multiplyMatrix(int[][] aMatrix, int[][] bMatrix, int[][] cMatrix) {
        int rowsCount = aMatrix.length;

        ArrayList<TapeThread> threads = new ArrayList<>();

        for (int i = 0; i < rowsCount; i++) {
            int[] row = aMatrix[i];

            TapeThread thread = new TapeThread(i, row, cMatrix);
            threads.add(thread);
        }

        for (int i = 0; i < rowsCount; i++) {
            int[] column = getColumn(bMatrix, i);

            for (TapeThread thread : threads) {
                thread.setColumn(column);
                thread.setJ(i);

                thread.run();
            }

            for (TapeThread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private int[] getColumn(int[][] matrix, int column) {
        return Arrays.stream(matrix).mapToInt(ints -> ints[column]).toArray();
    }
}
