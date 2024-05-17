package com.ed;

import Exceptions.WTF_Exception;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ViewController {

    @FXML
    private TextArea textArea;

    @FXML
    private Label words;

    @FXML
    void checkClicked(ActionEvent event) throws WTF_Exception {
        Automata automata = null;
        try {
            automata = AutomataParser.readAutomata("input.xml");
        } catch (Exception e) {
            //
        }

        if (automata == null)
            throw new WTF_Exception("Cant create automata.");

        if (Util.isAccepted(automata, textArea.getText())) {
            words.setStyle("-fx-text-fill: green");
            words.setText("Accepted");
        }
        else {
            words.setStyle("-fx-text-fill: red");
            words.setText("Oh no!  Not Accepted");
        }
    }

    @FXML
    void initialize() {
        textArea.setStyle("-fx-text-fill: white");
    }

}
