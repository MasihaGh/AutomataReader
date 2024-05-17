package org.example;

import Exceptions.WTF_Exception;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Automata automata;
        try {
            automata = AutomataParser.readAutomata("input.xml");
        } catch (IOException | ParserConfigurationException | SAXException | WTF_Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println(Util.isAccepted(automata, "abb"));
    }
}