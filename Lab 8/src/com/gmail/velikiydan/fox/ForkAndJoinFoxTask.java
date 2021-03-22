package com.gmail.velikiydan.fox;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class ForkAndJoinFoxTask extends RecursiveAction {

    private final int[][] a;
    private final int[][] b;
    private final int[][] c;
    private final int a_i;
    private final int a_j;
    private final int b_i;
    private final int b_j;
    private final int c_i;
    private final int c_j;
    private final int size;

    private static final int MINIMUM_THRESHOLD = 64;

    ForkAndJoinFoxTask(int[][] a, int[][] b, int[][] c, int a_i, int a_j, int b_i, int b_j, int c_i, int c_j, int size) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.a_i = a_i;
        this.a_j = a_j;
        this.b_i = b_i;
        this.b_j = b_j;
        this.c_i = c_i;
        this.c_j = c_j;
        this.size = size;
    }

    public void compute() {
        int h = size / 2;

        if (size <= MINIMUM_THRESHOLD) {
            computeDirectly();
        } else {
            ForkJoinTask.invokeAll(createSubtasks(h));
        }
    }

    private void computeDirectly() {
        int[][] matrix = this.multiplyMatrices(copyPartOfArray(a, a_i, a_j, size), copyPartOfArray(b, b_i, b_j, size));

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                c[c_i + i][c_j + j] += matrix[i][j];
            }
        }
    }

    private List<ForkAndJoinFoxTask> createSubtasks(int h) {
        List<ForkAndJoinFoxTask> subtasks = new ArrayList<>();

        subtasks.add(new ForkAndJoinFoxTask(a, b, c, a_i, a_j, b_i, b_j, c_i, c_j, h));
        subtasks.add(new ForkAndJoinFoxTask(a, b, c, a_i, a_j, b_i, b_j + h, c_i, c_j + h, h));
        subtasks.add(new ForkAndJoinFoxTask(a, b, c, a_i + h, a_j + h, b_i + h, b_j, c_i + h, c_j, h));
        subtasks.add(new ForkAndJoinFoxTask(a, b, c, a_i + h, a_j + h, b_i + h, b_j + h, c_i + h, c_j + h, h));

        subtasks.add(new ForkAndJoinFoxTask(a, b, c, a_i, a_j + h, b_i + h, b_j, c_i, c_j, h));
        subtasks.add(new ForkAndJoinFoxTask(a, b, c, a_i, a_j + h, b_i + h, b_j + h, c_i, c_j + h, h));
        subtasks.add(new ForkAndJoinFoxTask(a, b, c, a_i + h, a_j, b_i, b_j, c_i + h, c_j, h));
        subtasks.add(new ForkAndJoinFoxTask(a, b, c, a_i + h, a_j, b_i, b_j + h, c_i + h, c_j + h, h));

        return subtasks;
    }

    private int[][] multiplyMatrices(int[][] firstMatrix, int[][] secondMatrix) {
        int[][] result = new int[firstMatrix.length][secondMatrix[0].length];

        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                result[row][col] = multiplyMatricesCell(firstMatrix, secondMatrix, row, col);
            }
        }

        return result;
    }

    private int multiplyMatricesCell(int[][] firstMatrix, int[][] secondMatrix, int row, int col) {
        int cell = 0;
        for (int i = 0; i < secondMatrix.length; i++) {
            cell += firstMatrix[row][i] * secondMatrix[i][col];
        }
        return cell;
    }

    private int[][] copyPartOfArray(int[][] oldArray, int i, int j, int size) {
        int[][] newArr = new int[size][size];

        for (int k = 0; k < size; k++) {
            System.arraycopy(oldArray[k + i], j, newArr[k], 0, size);
        }

        return newArr;
    }
}