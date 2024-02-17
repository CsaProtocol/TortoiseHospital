package me.csaprotocol.tortoisehospital.fxmlcontrollers.usermenu.turtlepanel;

import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import me.csaprotocol.tortoisehospital.Main;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class turtlePanel implements Initializable {

    @FXML private Pane selectedTurtlePanel;
    @FXML private Button addRecordButton;
    @FXML private MFXScrollPane scrollPaneMeasurements;
    @FXML private HBox measurementBox;

    public void addMeasurementButton() {
        Node newButton = new Button();
        try {
            newButton = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("resources/fxml/usermenuResources/turtlepanel/measurementButton.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        measurementBox.getChildren().add(newButton);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Create a rounded rectangle at runtime for the selectedTurtlePanel
        Rectangle clip = new Rectangle(selectedTurtlePanel.getPrefWidth(), selectedTurtlePanel.getPrefHeight());
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        selectedTurtlePanel.setClip(clip);
        //Disable the vertical scrollbar
        scrollPaneMeasurements.setVbarPolicy(MFXScrollPane.ScrollBarPolicy.NEVER);
        scrollPaneMeasurements.setStyle("-fx-background-color: #1e1e1e");
    }
}
