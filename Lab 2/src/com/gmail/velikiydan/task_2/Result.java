package com.gmail.velikiydan.task_2;

import java.util.Vector;

public class Result {
    private Vector<Vector<Integer>> matrix;
    private Vector<Vector<Integer>> a;
    private Vector<Vector<Integer>> b;

    public Result(Vector<Vector<Integer>> a, Vector<Vector<Integer>> b) {
        this.a = a;
        this.b = b;
        this.matrix = MatrixHelper.createMatrix(a.size(), b.firstElement().size());
    }

    public void sequentialMultiply() {
        matrix = MatrixHelper.sequentialMultiply(a, b);
    }


    // 1 2 3 4 5 .... n
    // 1 2 3 4 5 .... n
    // 1 2 3 4 5 .... n
    // 1 2 3 4 5 .... n
    // 1 2 3 4 5 .... n
    // 1 2 3 4 5 .... n


    public void foxMultiply() {
        Vector<BlockThread> threads = new Vector<>();
        int q = a.size();

        for (int i = 0; i < q; i++) {
            for (int j = 0; j < q; j++) {
                BlockThread t = new BlockThread(i, j, a.get(i).get(j), b.get(i).get(j), 0);
                threads.add(t);
//                t.run();
            }
        }

        for (int l = 0; l < q; l++) {
            for (int i = 0; i < q; i++) {
                int j = (i + l) % q;
                BlockThread t = threads.get(i * q + j);
                t.run();
                // change blocks
            }
        }

        for (BlockThread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    public Vector<Vector<Integer>> getMatrix() {
        return matrix;
    }
}
