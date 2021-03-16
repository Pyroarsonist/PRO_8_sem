package com.gmail.velikiydan.task_2;

public class Create extends Element {


    public Create(String name, String distribution, double avgDelay) {
        super(name, distribution, avgDelay);
    }

    public void outAct(int element) {
        super.outAct(element);
        setTnext(getTcurr() + delay());
    }

}