package com.gmail.velikiydan.task_3;

import java.util.ArrayList;
import java.util.Map;

public class MatrixDuration {
    private final int size;
    private final long tapeDuration;
    private final long foxDuration;

    public MatrixDuration(Integer key, long tapeDuration, long foxDuration) {
        this.size = key;
        this.tapeDuration = tapeDuration;
        this.foxDuration = foxDuration;

    }


    public static ArrayList<MatrixDuration> getDurations(Map<Integer, Result> map) {
        ArrayList<MatrixDuration> mds = new ArrayList<>();
        for (Map.Entry<Integer, Result> set :
                map.entrySet()) {
            MatrixDuration md = new MatrixDuration(set.getKey(), set.getValue().getTapeDuration(), set.getValue().getFoxDuration());
            mds.add(md);
        }
        return mds;
    }
}
