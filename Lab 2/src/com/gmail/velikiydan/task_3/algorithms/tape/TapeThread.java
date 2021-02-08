package com.gmail.velikiydan.task_3.algorithms.tape;

public class TapeThread extends Thread {

    private final int i;
    private int j;
    private final int[] row;
    private int[] column;
    private final int[][] cMatrix;

    TapeThread (int i, int[] row, int[][] cMatrix) {
        this.i = i;
        this.row = row;
        this.cMatrix = cMatrix;
    }


    @Override
    public void run() {
        int result = 0;

        for (int i = 0; i < row.length; i++) {
            result += row[i] * column[i];
        }

        cMatrix[i][j] = result;
    }

    public void setColumn(int[] column) {
        this.column=column;
    }

    public void setJ(int j) {
        this.j=j;
    }
}
