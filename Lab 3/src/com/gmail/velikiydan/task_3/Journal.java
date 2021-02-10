package com.gmail.velikiydan.task_3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

public class Journal {
    private static final int MAX_MARK = 100;
    private static final int MIN_MARK = 0;
    private final String discipline;
    private final ArrayList<Student> students;
    private final ArrayList<HashMap<Integer, Integer>> weeks;
    private final ReentrantLock lock = new ReentrantLock();

    public Journal(String discipline, int weeksCount, int groupsCount, int studentsCount) {
        this.discipline = discipline;

        this.students = new ArrayList<>();
        for (int groupNum = 1; groupNum <= groupsCount; groupNum++) {
            for (int studentI = 1; studentI <= studentsCount; studentI++) {
                this.students.add(new Student(groupNum));
            }
        }

        this.weeks = new ArrayList<>();
        for (int i = 0; i < weeksCount; i++) {
            this.weeks.add(new HashMap<>());
        }
    }

    public void print() {
        System.out.printf("Journal of %s\n", discipline);
        for (int i = 0; i < weeks.size(); i++) {
            HashMap<Integer, Integer> marks = weeks.get(i);
            System.out.printf("Week %d\n", i + 1);
            for (Map.Entry<Integer, Integer> set :
                    marks.entrySet()) {
                Integer id = set.getKey();
                Integer mark = set.getValue();
                System.out.printf("id %3d: mark - %3d,\t", id, mark);
            }
            System.out.println();
        }
    }


    public boolean isFull() {
        int marksCount = 0;
        for (HashMap<Integer, Integer> week :
                this.weeks) {
            marksCount += week.size();
        }

        return marksCount == students.size() * weeks.size();
    }

    public void setNextMark() {
        try {
            lock.lock();

            weekLoop:
            for (HashMap<Integer, Integer> marks :
                    this.weeks) {

                if (marks.size() == students.size()) continue;

                for (Student s :
                        this.students) {
                    if (marks.containsKey(s.getId())) continue;

                    marks.put(s.getId(), generateMark());
                    break weekLoop;

                }

            }
        } finally {
            lock.unlock();
        }

    }

    private Integer generateMark() {
        return ThreadLocalRandom.current().nextInt(MIN_MARK, MAX_MARK + 1);
    }
}
