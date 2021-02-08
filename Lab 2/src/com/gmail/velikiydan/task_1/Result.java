package com.gmail.velikiydan.task_1;


public class Result {
    private final int[][] matrix;

    public Result(int[][] matrix) {
        this.matrix = matrix;
    }

    public Result(int matrixSize) {
        this.matrix = new int[matrixSize][matrixSize];
    }


    public int[][] getMatrix() {
        return matrix;
    }
}
