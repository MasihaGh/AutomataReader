package com.ed;

import enums.SType;

public class Util {

    private static int index;
    private static String input;

    public static boolean isAccepted(Automata automata, String input) {

        Util.index = 0;
        Util.input = input;

        State currentState = automata.getInitialState();
        Transition thisTransition;

        while (Util.index < input.length()) {
            thisTransition = automata.findTransition(lookahead(), currentState);

            if (thisTransition == null)
                return false;
            else {
                currentState = thisTransition.getDestination();
                Util.index++;
            }
        }

        return currentState.getType() == SType.FINAL;
    }

    private static Character lookahead() {
        return Util.input.charAt(Util.index);
    }
}


