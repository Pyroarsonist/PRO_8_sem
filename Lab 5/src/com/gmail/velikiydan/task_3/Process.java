package com.gmail.velikiydan.task_3;

import java.util.ArrayList;
import java.util.List;

public class Process extends Element {

    private final int maxqueue;
    private int failure = 0;
    private double avgQueue = 0.0;
    protected int state = 0;
    protected int maxParallel = 1;
    private double workload = 0.0;
    protected List<Integer> nextElements = new ArrayList<>();
    private final List<Integer> queue = new ArrayList<>();

    protected List<Double> nextElTime = new ArrayList<>();


    public Process(String name, String distribution, double avgDelay, int maxqueue) {
        super(name, distribution, avgDelay);
        this.maxqueue = maxqueue;
    }


    public void inAct(int elNext) {
        super.inAct(elNext);

        if (state < maxParallel) {

            double newTNext = getTcurr() + delay();
            state++;
            nextElements.add(elNext);
            nextElTime.add(newTNext);

            if (getTnext() > newTNext)
                setTnext(newTNext);

        } else {

            if (queue.size() < maxqueue) {
                queue.add(elNext);
            } else {
                failure++;
            }
        }
    }

    public void outAct(int element) {

        double newTNext = Double.MAX_VALUE;

        if (nextElTime.size() != 0) {
            List<Integer> elementsToAct = new ArrayList<>();

            for (int i = 0; i < nextElTime.size(); i++) {

                if (nextElTime.get(i) <= getTcurr()) {
                    super.outAct(nextElements.get(i));
                    elementsToAct.add(i);
                }
            }

            for (int i = elementsToAct.size() - 1; i >= 0; i--) {
                int elementToRemove = elementsToAct.get(i);
                nextElTime.remove(elementToRemove);
                nextElements.remove(elementToRemove);
            }

            for (double temp : nextElTime) {
                if (temp < newTNext)
                    newTNext = temp;
            }

        }
        setTnext(newTNext);
        state = nextElTime.size();
        ProcessFromQueue();
    }


    public void MaxParallel(int parallel) {
        if (parallel > 0)
            maxParallel = parallel;
    }


    public void statistics(double delta) {
        avgQueue = GetMeanQueue() + queue.size() * delta;
        workload = GetMeanState() + state * delta;
    }

    public double GetMeanQueue() {
        return avgQueue;
    }

    public double GetMeanState() {
        return workload;
    }

    public int GetFailure() {
        return failure;
    }

    protected void ProcessFromQueue() {
        if (queue.size() > 0) {
            int element = queue.get(0);
            queue.remove(0);
            inAct(element);
        }
    }

}