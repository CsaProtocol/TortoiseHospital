package me.csaprotocol.tortoisehospital.fxmlcontrollers.modularMenu;

import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import me.csaprotocol.tortoisehospital.Main;
import me.csaprotocol.tortoisehospital.controllers.ControllerOrchestrator;
import me.csaprotocol.tortoisehospital.entities.Measurement;
import me.csaprotocol.tortoisehospital.fxmlcontrollers.usermenu.measurementButton;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class thirdColumnTurtleMenu implements Initializable {

    //First Row
    @FXML private Pane thirdColumn;
    @FXML private Button deleteSelectedTurtleButton;
    @FXML private Button updateSelectedTurtleButton;
    @FXML private Pane selectedTurtlePanel;
    @FXML private Label turtleNameLabel;
    @FXML private Label turtleIDLabel;
    @FXML private Label centerIDLabel;
    @FXML private Label tankIDLabel;
    @FXML private Label speciesLabel;
    @FXML private ImageView genderImg;

    //Second Row
    @FXML private MFXScrollPane scrollPaneMeasurement;
    @FXML private HBox measurementBox;
    @FXML private Pane selectedMeasurementPanel;
    @FXML private Label measurementDateLabel;
    @FXML private Label lengthLabel;
    @FXML private Label weightLabel;
    @FXML private Label widthLabel;
    @FXML private Button updateSelectedMeasurementButton;
    @FXML private Button deleteSelectedMeasurementButton;
    @FXML private Button newMeasurementButton;

    public void addMeasurementButton(Measurement measurement) {
        Node newButton = new Button();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(Main.class.getResource("resources/fxml/usermenuResources/measurementButton.fxml")));
            newButton = fxmlLoader.load();
            measurementButton fxmlCo = fxmlLoader.getController();
            fxmlCo.setDateLabel(measurement.getDate().toString());
            fxmlCo.setMeasurementAssociated(measurement);
        } catch (IOException e) {
            e.printStackTrace();
        }
        measurementBox.getChildren().add(newButton);
    }

    public void clearMeasurementButtons() {
        measurementBox.getChildren().clear();
    }
    public void setCenterIDLabel(String centerID) { centerIDLabel.setText("Center: " + centerID); }
    public void setTurtleIDLabel(String turtleID) { turtleIDLabel.setText(turtleID); }
    public void setTurtleNameLabel(String turtleName) { turtleNameLabel.setText(turtleName); }
    public void setSpeciesLabel(String species) { speciesLabel.setText(species); }
    public void setTankIDLabel(String tankID) { tankIDLabel.setText("Tank: " + tankID); }
    public void setSexImgToMale() { genderImg.setImage(new Image(String.valueOf(Objects.requireNonNull(Main.class.getResource("resources/images/male.png"))))); }
    public void setSexImgToFemale() { genderImg.setImage(new Image(String.valueOf(Objects.requireNonNull(Main.class.getResource("resources/images/female.png"))))); }


    //Measurement
    public void setMeasurementDateLabel(String measurementDate) { measurementDateLabel.setText(measurementDate); }
    public void setLengthLabel(String length) { lengthLabel.setText("Length: " + length + " cm"); }
    public void setWeightLabel(String weight) { weightLabel.setText("Weight: " + weight + " g"); }
    public void setWidthLabel(String width) { widthLabel.setText("Width: " + width + " cm"); }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Create a rounded rectangle at runtime for the selectedTurtlePanel
        Rectangle clip = new Rectangle(selectedTurtlePanel.getPrefWidth(), selectedTurtlePanel.getPrefHeight());
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        selectedTurtlePanel.setClip(clip);
        //Create a rounded rectangle at runtime for the selectedMeasurementPanel
        Rectangle clip2 = new Rectangle(selectedMeasurementPanel.getPrefWidth(), selectedMeasurementPanel.getPrefHeight());
        clip2.setArcWidth(20);
        clip2.setArcHeight(20);
        selectedMeasurementPanel.setClip(clip2);

        //Set the spacing between the measurement buttons
        measurementBox.setSpacing(4);
    }


    @FXML
    void onDeleteSelectedMeasurementClick(MouseEvent event) {
        ControllerOrchestrator co = new ControllerOrchestrator();
        co.showDialogDeleteMeasurement(event, thirdColumn, measurementDateLabel.getText());
    }

    @FXML
    void onDeleteSelectedTurtleClick(MouseEvent event) {
        ControllerOrchestrator co = new ControllerOrchestrator();
        co.showDialogDeleteTurtle(event, thirdColumn);
    }

    @FXML void onNewMeasurementClick(MouseEvent event) {
        ControllerOrchestrator co = new ControllerOrchestrator();
        co.showNewMeasurementGUI(new Stage());
    }


    @FXML void onUpdateTurtleButtonClick(MouseEvent event) {
        ControllerOrchestrator co = new ControllerOrchestrator();
        co.showUpdateTurtleGUI(new Stage());
    }
}
