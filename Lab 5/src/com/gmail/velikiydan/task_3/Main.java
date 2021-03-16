package com.gmail.velikiydan.task_3;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Model model = createModel();
        model.simulate();
    }

    public static Model createModel() {
        Create c = new Create("creator", "exp", 2);
        Process p1 = new Process("process1", "exp", 2.5, 10);
        c.nextElement(p1);
        p1.nextElement(null, 1);

        List<Element> list = Arrays.asList(c, p1);
        return new Model(list, 10000.0);
    }
}