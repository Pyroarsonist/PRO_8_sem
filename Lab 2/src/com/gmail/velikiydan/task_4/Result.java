package com.gmail.velikiydan.task_4;


public class Result {
    private final int[][] matrix;
    private long tapeDuration;
    private long foxDuration;

    public Result(int[][] matrix) {
        this.matrix = matrix;
    }

    public Result(int matrixSize) {
        this.matrix = new int[matrixSize][matrixSize];
    }


    public int[][] getMatrix() {
        return matrix;
    }

    public long getTapeDuration() {
        return tapeDuration;
    }

    public void setTapeDuration(long tapeDuration) {
        this.tapeDuration = tapeDuration;
    }

    public long getFoxDuration() {
        return foxDuration;
    }

    public void setFoxDuration(long foxDuration) {
        this.foxDuration = foxDuration;
    }

}
