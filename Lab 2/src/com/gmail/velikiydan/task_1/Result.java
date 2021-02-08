package com.gmail.velikiydan.task_1;

import java.util.Vector;

public class Result {
    private Vector<Vector<Integer>> matrix;
    private Vector<Vector<Integer>> matrix1;
    private Vector<Vector<Integer>> matrix2;

    public Result(Vector<Vector<Integer>> matrix1, Vector<Vector<Integer>> matrix2) {
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.matrix = MatrixHelper.createMatrix(matrix1.size(), matrix2.firstElement().size());
    }

    public void sequentialMultiply() {
        matrix = MatrixHelper.sequentialMultiply(matrix1, matrix2);
    }

    public void tapeMultiply() {
        int rows1 = matrix1.size();
        for (int i = 0; i < rows1; i++) {
            TapeThread thread = new TapeThread(matrix, matrix1, matrix2, i);
            thread.start();
        }
    }


    public Vector<Vector<Integer>> getMatrix() {
        return matrix;
    }
}
