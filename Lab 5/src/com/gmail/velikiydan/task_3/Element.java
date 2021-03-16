package com.gmail.velikiydan.task_3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Element {
    private static int nextId = 0;
    public Random Rand;
    private final int id;
    private final String name;
    private double tcurr;
    private double tnext = 0.0;
    private double deleyAvg = 1.0;
    private final double delayDev = 1.0;
    private String distribution = "";
    private int quantity = 0;
    private final List<Element> nextElements = new ArrayList<>();
    private List<Double> probabilities = new ArrayList<>();
    private final List<Double> probId = new ArrayList<>();

    public Element() {
        id = nextId++;
        name = "element_" + id;
        Rand = new Random();
    }

    public Element(String name) {
        id = nextId++;
        this.name = name;
        Rand = new Random();
    }

    public Element(String name, String distribution, double avgDelay) {
        id = nextId++;
        this.name = name;
        Rand = new Random();
        this.distribution = distribution;
        deleyAvg = avgDelay;

    }

    public Element(String name, double tnext) {
        id = nextId++;
        this.name = name;
        Rand = new Random();
        this.tnext = tnext;
    }


    public static int getNextId() {
        return nextId;
    }

    public Random getRand() {
        return Rand;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getTcurr() {
        return tcurr;
    }

    public double getTnext() {
        return tnext;
    }

    public double getDeleyAvg() {
        return deleyAvg;
    }

    public double getDelayDev() {
        return delayDev;
    }

    public String getDistribution() {
        return distribution;
    }

    public int getQuantity() {
        return quantity;
    }

    public List<Element> getNextElements() {
        return nextElements;
    }

    public List<Double> getProbabilities() {
        return probabilities;
    }

    public List<Double> getProbId() {
        return probId;
    }

    public double delay() {

        double delay;

        switch (distribution) {
            case "exp" -> {
                delay = Distributions.exp(deleyAvg);
            }
            case "norm" -> {
                delay = Distributions.norm(deleyAvg, delayDev);
            }
            case "unif" -> {
                delay = Distributions.uniform(deleyAvg, delayDev);
            }
            case "erl" -> {
                delay = Distributions.erlang(deleyAvg, delayDev);
            }
            default -> {
                delay = deleyAvg;
            }
        }
        return delay;
    }


    public int Quantity() {
        return quantity;
    }

    public void setTcurr(double tcurr) {
        this.tcurr = tcurr;
    }

    public void nextElement(Element nextElement) {
        nextElement(nextElement, 1.0);
    }

    public void nextElement(Element nextElement, double probabilityIndex) {
        nextElements.add(nextElement);
        probId.add(probabilityIndex);
        double indexSum = probId.stream().mapToDouble(p -> p).sum();
        probabilities = probId.stream().map(x -> x / indexSum).collect(Collectors.toList());
    }

    public void inAct(int nextElement) {

    }

    public void outAct(int element) {
        quantity++;
        Element nextElement;
        nextElement = chooseNextElement();
        if (nextElement != null) {
            nextElement.inAct(element);
        }
    }


    public void setTnext(double tnext) {
        this.tnext = tnext;
    }


    public void statistics(double delta) {

    }

    public Element chooseNextElement() {
        double rand = Math.random();
        Element nextElement = null;

        for (int i = 0; i < probabilities.size(); i++) {
            if (rand < probabilities.get(i)) {
                nextElement = nextElements.get(i);
                break;
            } else {
                rand -= probabilities.get(i);
            }
        }
        return nextElement;
    }
}