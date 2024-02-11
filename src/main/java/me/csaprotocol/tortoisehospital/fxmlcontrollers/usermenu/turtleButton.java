package me.csaprotocol.tortoisehospital.fxmlcontrollers.usermenu;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class turtleButton {

    @FXML private Label idTurtleLabel;
    @FXML private Label nameTurtleLabel;
    @FXML private AnchorPane turtleID;
    public void setIdTurtleLabel(String idTurtle) {
        idTurtleLabel.setText(idTurtle);
    }
    public void setNameTurtleLabel(String turtleName) {
        nameTurtleLabel.setText(turtleName);
    }
    @FXML public void onTurtleClick(MouseEvent event) {
        turtleID.setStyle("-fx-background-color: #165DCE; -fx-background-radius: 1em");
        System.out.println("Turtle clicked");
    }
    @FXML public void onMouseEnter(MouseEvent event) {
        turtleID.setStyle("-fx-background-color: #6393E7; -fx-background-radius: 1em");
    }
    @FXML public void onMouseExit(MouseEvent event) {
        turtleID.setStyle("-fx-background-color: #1E1E1E");
    }
}