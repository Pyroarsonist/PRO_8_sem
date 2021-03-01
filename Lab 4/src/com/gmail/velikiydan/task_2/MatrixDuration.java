package com.gmail.velikiydan.task_2;

import java.util.ArrayList;
import java.util.Map;

public class MatrixDuration {
    private final int size;
    private final long tapeDuration;
    private final long foxDuration;
    private final long forkAndJoinFoxDuration;

    public MatrixDuration(Integer key, long tapeDuration, long foxDuration, long forkAndJoinFoxDuration) {
        this.size = key;
        this.tapeDuration = tapeDuration;
        this.foxDuration = foxDuration;
        this.forkAndJoinFoxDuration = forkAndJoinFoxDuration;

    }


    public static ArrayList<MatrixDuration> getDurations(Map<Integer, Result> map) {
        ArrayList<MatrixDuration> mds = new ArrayList<>();
        for (Map.Entry<Integer, Result> set :
                map.entrySet()) {
            MatrixDuration md = new MatrixDuration(set.getKey(), set.getValue().getTapeDuration(), set.getValue().getFoxDuration(), set.getValue().getForkAndJoinFoxDuration());
            mds.add(md);
        }
        return mds;
    }
}
