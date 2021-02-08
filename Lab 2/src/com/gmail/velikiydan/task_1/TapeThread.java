package com.gmail.velikiydan.task_1;

import java.util.Vector;

public class TapeThread extends Thread {

    private Vector<Vector<Integer>> matrix;
    private Vector<Vector<Integer>> matrix1;
    private Vector<Vector<Integer>> matrix2;
    private final int row;

    public TapeThread(Vector<Vector<Integer>> matrix, Vector<Vector<Integer>> matrix1, Vector<Vector<Integer>> matrix2, int row) {
        this.matrix = matrix;
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.row = row;
    }


    @Override
    public void run() {
        for (int col = 0; col < matrix2.firstElement().size(); col++) {
            matrix.get(row).set(col, MatrixHelper.multiplyMatricesCell(matrix1, matrix2, row, col));
        }
    }
}
