package com.gmail.velikiydan.task_2;

import java.util.Vector;

public class MatrixHelper {

    public static Vector<Vector<Integer>> createMatrix(int rows, int cols) {
        Vector<Vector<Integer>> temp = new Vector<>();
        for (int i = 0; i < rows; i++) {
            Vector<Integer> row = new Vector<>();
            for (int j = 0; j < cols; j++) {
                row.add(0);
            }
            temp.add(row);
        }
        return temp;
    }

    public static Vector<Vector<Integer>> sequentialMultiply(Vector<Vector<Integer>> matrix1, Vector<Vector<Integer>> matrix2) {
        Vector<Vector<Integer>> temp = createMatrix(matrix1.size(), matrix2.firstElement().size());

        for (int row = 0; row < temp.size(); row++) {
            for (int col = 0; col < temp.get(row).size(); col++) {
                temp.get(row).set(col, multiplyMatricesCell(matrix1, matrix2, row, col));
            }
        }

        return temp;
    }

    public static int multiplyMatricesCell(Vector<Vector<Integer>> matrix1, Vector<Vector<Integer>> matrix2, int row, int col) {

        int cell = 0;
        for (int i = 0; i < matrix2.size(); i++) {
            cell += matrix1.get(row).get(i) * matrix2.get(i).get(col);
        }
        return cell;
    }

    public static Vector<Vector<Integer>> transposeMatrix(Vector<Vector<Integer>> m) {
        Vector<Vector<Integer>> temp = new Vector<>();


        for (int i = 0; i < m.size(); i++) {
            Vector<Integer> row = new Vector<>();
            for (int j = 0; j < m.firstElement().size(); j++) {
                row.add(m.get(j).get(i));
            }
            temp.add(row);
        }
        return temp;
    }

}
