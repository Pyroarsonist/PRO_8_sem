package com.gmail.velikiydan.task_3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

public class Journal {
    private static final int MAX_MARK = 100;
    private static final int MIN_MARK = 0;
    private final String discipline;
    private final ArrayList<Student> students;
    private final ArrayList<HashMap<Integer, Integer>> weeks;
    private final ReentrantLock lock = new ReentrantLock();

    public Journal(String discipline, int weeksCount, int studentsCount) {
        this.discipline = discipline;

        this.students = new ArrayList<>();
        for (int i = 1; i <= studentsCount; i++) {
            this.students.add(new Student(i));
        }

        this.weeks = new ArrayList<>();
        for (int i = 0; i < weeksCount; i++) {
            this.weeks.add(new HashMap<>());
        }
    }

    public void print() {
        System.out.printf("Journal of %s\n", discipline);
        for (int i = 0; i < weeks.size(); i++) {
            var week = weeks.get(i);
            System.out.printf("Week %d", i + 1);
        }
    }


    public boolean isFull() {
        int marks = 0;
        for (HashMap<Integer, Integer> week :
                this.weeks) {
            marks += week.size();
        }
        return marks == students.size();
    }

    public void setNextMark() {
        lock.lock();

        weekLoop:
        for (HashMap<Integer, Integer> week :
                this.weeks) {
            if (week.size() == students.size()) continue;

            for (Student s :
                    this.students) {
                if (week.containsKey(s.getId())) continue;

                week.put(s.getId(), generateMark());
                break weekLoop;

            }

        }

        lock.unlock();
    }

    private Integer generateMark() {
        return ThreadLocalRandom.current().nextInt(MIN_MARK, MAX_MARK + 1);
    }
}
