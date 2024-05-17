package org.example;

import Exceptions.WTF_Exception;
import enums.AType;
import enums.SType;

import java.util.ArrayList;
import java.util.List;

public class Automata {

    private List<Character> alphabets;
    private State initialState;
    private List<State> finalStates;
    private List<State> states;
    private List<Transition> transitions;
    private AType type;

    public Automata() {
        this.alphabets = new ArrayList<>();
        this.finalStates = new ArrayList<>();
        this.states = new ArrayList<>();
        this.transitions = new ArrayList<>();
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public AType getType() {
        return type;
    }

    public void setType(AType type) {
        this.type = type;
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

            if (s.getTag().equals(destinationName))
                destination = s;
        }
        if (source == null || destination == null)
            throw new WTF_Exception("WTF!\nStates are not found!");

        this.transitions.add(new Transition(source, destination, label));
    }

    public Integer getStateIndex(String tag) {
        for (int i = 0; i < getStates().size(); i++) {
            if (getStates().get(i).getTag().equals(tag))
                return i;
        }
        return -1;
    }

    public Transition findTransition(Character ch, State st) {
        for (Transition tmp : getTransitions()) {
            if (tmp.getLabel() == ch && tmp.getSource().getTag().equals(st.getTag()))
                return tmp;
        }
        return null;
    }
}
