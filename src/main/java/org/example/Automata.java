package org.example;

import Exceptions.WTF_Exception;
import enums.SType;

import java.util.ArrayList;
import java.util.List;

public class Automata {

    private List<Character> alphabets;
    private State initialState;
    private List<State> finalStates;
    private List<State> states;
    private List<Transition> transitions;

    public Automata() {
        this.alphabets = new ArrayList<>();
        this.finalStates = new ArrayList<>();
        this.states = new ArrayList<>();
        this.transitions = new ArrayList<>();
    }

    public List<State> getStates() {
        return states;
    }

    public List<State> getFinalStates() {
        return finalStates;
    }

    public State getInitialState() {
        return initialState;
    }

    public void setInitialState(String initialStateTag) {
        this.initialState = new State(initialStateTag, SType.INIT);

        for (State s : getStates()) {
            if (s.getTag().equals(initialStateTag))
                s.setType(SType.INIT);
        }
    }

    public List<Character> getAlphabets() {
        return alphabets;
    }

    public void addFinalState(String finalStateTag) {
        this.finalStates.add(new State(finalStateTag, SType.FINAL));

        for (State s : getStates()) {
            if (s.getTag().equals(finalStateTag))
                s.setType(SType.FINAL);
        }
    }

    public void addTransition(String sourceName, String destinationName, Character label) throws WTF_Exception {
        State source = null;
        State destination = null;

        for (State s : getStates()) {
            if (s.getTag().equals(sourceName))
                source = s;
            else if (s.getTag().equals(destinationName))
                destination = s;
            else throw new WTF_Exception("WTF!\nStates are not found!");
        }

        this.transitions.add(new Transition(source, destination, label));
    }

    public Integer getStateIndex(String tag) {
        for (int i = 0; i < getStates().size(); i++) {
            if (getStates().get(i).getTag().equals(tag))
                return i;
        }
        return -1;
    }
}
