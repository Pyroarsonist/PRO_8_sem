package com.gmail.velikiydan.task_4.algorithms.fox;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;

public class FoxThread extends Thread {

    private final int[][] aMatrix;
    private final int[][] bMatrix;
    private final int[][] cMatrix;
    private final int a_i;
    private final int a_j;
    private final int b_i;
    private final int b_j;
    private final int c_i;
    private final int c_j;
    private final int size;

    private static final int MINIMUM_THRESHOLD = 64;

    private final ExecutorService exec;

    FoxThread(int[][] aMatrix, int[][] bMatrix, int[][] cMatrix, int a_i, int a_j, int b_i, int b_j, int c_i, int c_j, int size, ExecutorService exec) {
        this.aMatrix = aMatrix;
        this.bMatrix = bMatrix;
        this.cMatrix = cMatrix;
        this.a_i = a_i;
        this.a_j = a_j;
        this.b_i = b_i;
        this.b_j = b_j;
        this.c_i = c_i;
        this.c_j = c_j;
        this.size = size;
        this.exec = exec;
    }

    public void run() {
        int h = size / 2;

        if (size <= MINIMUM_THRESHOLD) {
            int[][] matrix = this.multiplyMatrices(copyPartOfArray(aMatrix, a_i, a_j, size), copyPartOfArray(bMatrix, b_i, b_j, size));

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    cMatrix[c_i + i][c_j + j] += matrix[i][j];
                }
            }
        } else {
            FoxThread[] tasks = {
                    new FoxThread(aMatrix, bMatrix, cMatrix, a_i, a_j, b_i, b_j, c_i, c_j, h, this.exec),
                    new FoxThread(aMatrix, bMatrix, cMatrix, a_i, a_j, b_i, b_j + h, c_i, c_j + h, h, this.exec),
                    new FoxThread(aMatrix, bMatrix, cMatrix, a_i + h, a_j + h, b_i + h, b_j, c_i + h, c_j, h, this.exec),
                    new FoxThread(aMatrix, bMatrix, cMatrix, a_i + h, a_j + h, b_i + h, b_j + h, c_i + h, c_j + h, h, this.exec),

                    new FoxThread(aMatrix, bMatrix, cMatrix, a_i, a_j + h, b_i + h, b_j, c_i, c_j, h, this.exec),
                    new FoxThread(aMatrix, bMatrix, cMatrix, a_i, a_j + h, b_i + h, b_j + h, c_i, c_j + h, h, this.exec),
                    new FoxThread(aMatrix, bMatrix, cMatrix, a_i + h, a_j, b_i, b_j, c_i + h, c_j, h, this.exec),
                    new FoxThread(aMatrix, bMatrix, cMatrix, a_i + h, a_j, b_i, b_j + h, c_i + h, c_j + h, h, this.exec)
            };

            FutureTask[] fs = new FutureTask[tasks.length];

            for (int i = 0; i < tasks.length; i++) {
                fs[i] = new FutureTask(tasks[i], null);
                exec.execute(fs[i]);
            }

            for (int i = 0; i < fs.length; ++i) {
                fs[i].run();
            }

            for (int i = 0; i < fs.length; ++i) {
                try {
                    fs[i].get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private int[][] multiplyMatrices(int[][] aMatrix, int[][] bMatrix) {
        int[][] cMatrix = new int[aMatrix.length][bMatrix[0].length];

        for (int row = 0; row < cMatrix.length; row++) {
            for (int col = 0; col < cMatrix[row].length; col++) {
                cMatrix[row][col] = multiplyMatricesCell(aMatrix, bMatrix, row, col);
            }
        }

        return cMatrix;
    }

    private int multiplyMatricesCell(int[][] aMatrix, int[][] bMatrix, int row, int col) {
        int cell = 0;
        for (int i = 0; i < bMatrix.length; i++) {
            cell += aMatrix[row][i] * bMatrix[i][col];
        }
        return cell;
    }

    private int[][] copyPartOfArray(int[][] oldArray, int i, int j, int size) {
        int[][] newArr = new int[size][size];

        for (int k = 0; k < size; k++) {
            for (int l = 0; l < size; l++) {
                newArr[k][l] = oldArray[k + i][l + j];
            }
        }

        return newArr;
    }
}
