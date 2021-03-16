package com.gmail.velikiydan.task_3;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private final List<Element> list;
    double tnext = 0.0;
    int event;
    double tcurr = 0.0;
    double timeModeling;

    public Model(List<Element> elements, double timeModeling) {
        list = elements;
        this.timeModeling = timeModeling;
    }

    public void simulate() {
        Logger logger = new Logger();
        LoggerThread thread = new LoggerThread(logger);
        thread.start();
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

            try {
                logger.setLogs(getInfo());
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        thread.stop = true;
    }

    public ArrayList<String> getInfo() {
        ArrayList<String> logs = new ArrayList<>();
        for (Element e : list) {
            if (e instanceof Process) {
                Process p = (Process) e;


                logs.add(p.getName() + "\nCurrent time = " + p.getTcurr() + ", average queue = " + p.GetMeanQueue() / p.getTcurr() + ", failure = "
                        + (double) p.GetFailure() / (p.Quantity() == 0 ? 1 : p.Quantity()));
            }
        }

        return logs;
    }

    public void PrintInfo() {
        for (Element e : list) {
            if (e instanceof Process) {
                Process p = (Process) e;

                System.out.println(p.getName() + "\nCurrent time = " + p.getTcurr() + ", average queue = " + p.GetMeanQueue() / p.getTcurr() + ", failure = "
                        + p.GetFailure() / (p.Quantity() == 0 ? 1 : p.Quantity()));
            }
        }
    }
}