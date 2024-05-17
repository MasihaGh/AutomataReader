package org.example;

import enums.SType;

public class State {

    private String tag;
    private SType type;

    public State(String tag, SType type) {
        this.tag = tag;
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public SType getType() {
        return type;
    }

    public void setType(SType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "(" + tag + ", " + type + ")";
    }
}
