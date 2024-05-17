package org.example;

import Exceptions.WTF_Exception;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Automata automata;
        try {
            automata = AutomataParser.readAutomata("input.xml");
        } catch (IOException | ParserConfigurationException | SAXException | WTF_Exception e) {
            throw new RuntimeException(e);
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter a string with (a, b) alphabets or Exit: ");
            String input = scanner.next();

            if (input.equalsIgnoreCase("exit"))
                break;

            if (Util.isAccepted(automata, input))
                System.out.println("Accepted");
            else System.out.println("Oh no!\nNot accepted");
        }
    }
}