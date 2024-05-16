package org.example;

public class Transition {

    private State source;
    private State destination;
    private Character label;

    public Transition(State source, State destination, Character label) {
        this.source = source;
        this.destination = destination;
        this.label = label;
    }
}
