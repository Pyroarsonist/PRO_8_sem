package com.gmail.velikiydan.task_2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class Model implements Callable<ArrayList<Double>> {
    private List<Element> list;
    double tnext = 0.0;
    int event;
    double tcurr = 0.0;
    double timeModeling;

    public Model(List<Element> elements, double timeModeling) {
        list = elements;
        this.timeModeling = timeModeling;
    }

    @Override
    public ArrayList<Double> call() throws Exception {
        while (tcurr < timeModeling) {
            tnext = Double.MAX_VALUE;
            for (Element e : list) {
                if (e.getTnext() < tnext) {
                    tnext = e.getTnext();
                    event = e.getId();
                }
            }

            for (Element e : list) {
                e.statistics(tnext - tcurr);
            }
            tcurr = tnext;
            for (Element e : list) {
                e.setTcurr(tcurr);
            }
            for (Element e : list) {
                if (e.getTnext() == tcurr) {
                    e.outAct(0);
                }
            }
        }

        ArrayList<Double> result = new ArrayList<>();
        for (Element e : list) {
            if (e instanceof Process) {
                Process p = (Process) e;
                result.add(p.GetMeanQueue() / p.getTcurr());
                result.add((double) p.GetFailure() / p.Quantity());
            }
        }

        return result;
    }

    public void printResult() {
        for (Element e : list) {
            if (e instanceof Process) {
                Process p = (Process) e;

                System.out.println(p.getName() + "\nCurrent time = " + p.getTcurr() + ", average queue = " + p.GetMeanQueue() / p.getTcurr() + ", failure = "
                        + p.GetFailure() / (p.Quantity() == 0 ? 1 : p.Quantity()));
            }
        }
    }
}