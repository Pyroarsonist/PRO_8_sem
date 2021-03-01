package com.gmail.velikiydan.task_2;


public class Result {
    private final int[][] matrix;
    private long tapeDuration;
    private long foxDuration;
    private long forkAndJoinFoxDuration;

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

    public long getForkAndJoinFoxDuration() {
        return forkAndJoinFoxDuration;
    }

    public void setForkAndJoinFoxDuration(long forkAndJoinFoxDuration) {
        this.forkAndJoinFoxDuration = forkAndJoinFoxDuration;
    }
}
