package com.gmail.velikiydan.task_4;

import java.util.concurrent.ThreadLocalRandom;

public class MatrixHelper {

    private static final int MAXIMUM_INT = 10;

    public static int[][] generateRandomMatrix(int n, int m) {
        int[][] matrix = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = ThreadLocalRandom.current().nextInt(0, MAXIMUM_INT + 1);
            }
        }

        return matrix;
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] matrix1 : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix1[j] + " ");
            }
            System.out.println();
        }
    }

}
