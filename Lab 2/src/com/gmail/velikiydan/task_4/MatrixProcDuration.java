package com.gmail.velikiydan.task_4;

import java.util.ArrayList;
import java.util.Map;

public class MatrixProcDuration {
    private final int poolSize;
    private final long tapeDuration;
    private final long foxDuration;

    public MatrixProcDuration(Integer poolSize, long tapeDuration, long foxDuration) {
        this.poolSize = poolSize;
        this.tapeDuration = tapeDuration;
        this.foxDuration = foxDuration;

    }


    public static ArrayList<MatrixProcDuration> getDurations(Map<Integer, Result> map) {
        ArrayList<MatrixProcDuration> mds = new ArrayList<>();
        for (Map.Entry<Integer, Result> set :
                map.entrySet()) {
            MatrixProcDuration md = new MatrixProcDuration(set.getKey(), set.getValue().getTapeDuration(), set.getValue().getFoxDuration());
            mds.add(md);
        }
        return mds;
    }
}
