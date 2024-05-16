package org.example;

import Exceptions.WTF_Exception;
import enums.AType;
import enums.SType;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;
import java.util.List;

public class AutomataParser {

    public Automata readAutomata(String filePath) throws IOException, ParserConfigurationException, SAXException, WTF_Exception {
        File xmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        Automata automata = new Automata();

        // Parse Automata attributes
        Element automataElement = doc.getDocumentElement();

        // Static automata type
        automata.setType(AType.DFA);

        parseAlphabets(doc, automata.getAlphabets());
        parseStates(doc, automata.getStates());
        parseInitialState(doc, automata);
        parseFinalStates(doc, automata);
        parseTransitions(doc, automata);

        return automata;
    }

    private void parseAlphabets(Document doc, List<Character> alphabets) {
        NodeList alphabetList = doc.getElementsByTagName("alphabet");
        for (int i = 0; i < alphabetList.getLength(); i++) {
            Node alphabetNode = alphabetList.item(i);
            if (alphabetNode.getNodeType() == Node.ELEMENT_NODE) {
                Element alphabetElement = (Element) alphabetNode;
                char alphabet = alphabetElement.getTextContent().charAt(0);
                alphabets.add(alphabet);
            }
        }
    }

    private void parseStates(Document doc, List<State> states) {
        NodeList stateList = doc.getElementsByTagName("state");
        for (int i = 0; i < stateList.getLength(); i++) {
            Node stateNode = stateList.item(i);
            if (stateNode.getNodeType() == Node.ELEMENT_NODE) {
                Element stateElement = (Element) stateNode;
                states.add(new State(stateElement.getAttribute("name"),SType.NORMAL));
            }
        }
    }

    private void parseInitialState(Document doc, Automata automata) {
        NodeList initialStateList = doc.getElementsByTagName("initailState");
        for (int i = 0; i < initialStateList.getLength(); i++) {
            Node initialStateNode = initialStateList.item(i);
            if (initialStateNode.getNodeType() == Node.ELEMENT_NODE) {
                Element initialStateElement = (Element) initialStateNode;
                automata.setInitialState(initialStateElement.getAttribute("name"));
            }
        }
    }

    private void parseFinalStates(Document doc, Automata automata) {
        NodeList finalStateList = doc.getElementsByTagName("finalstate");
        for (int i = 0; i < finalStateList.getLength(); i++) {
            Node finalStateNode = finalStateList.item(i);
            if (finalStateNode.getNodeType() == Node.ELEMENT_NODE) {
                Element finalStateElement = (Element) finalStateNode;
                automata.addFinalState(finalStateElement.getAttribute("name"));
            }
        }
    }

    private void parseTransitions(Document doc, Automata automata) throws WTF_Exception {
        NodeList transitionList = doc.getElementsByTagName("transition");
        for (int i = 0; i < transitionList.getLength(); i++) {
            Node transitionNode = transitionList.item(i);
            if (transitionNode.getNodeType() == Node.ELEMENT_NODE) {
                Element transitionElement = (Element) transitionNode;
                String source = transitionElement.getAttribute("source");
                String destination = transitionElement.getAttribute("destination");
                String label = transitionElement.getAttribute("label");
                char labelChar = label.charAt(0);
                automata.addTransition(source, destination, labelChar);
            }
        }
    }
}