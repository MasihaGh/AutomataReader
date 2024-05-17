package com.ed;

public class Transition {

    private State source;
    private State destination;
    private Character label;

    public Transition(State source, State destination, Character label) {
        this.source = source;
        this.destination = destination;
        this.label = label;
    }

    public State getSource() {
        return source;
    }

    public State getDestination() {
        return destination;
    }

    public Character getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "(" + source.getTag() + " ---" + label + "--> " + destination.getTag() + ")";
    }
}
