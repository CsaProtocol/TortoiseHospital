package me.csaprotocol.tortoisehospital.fxmlcontrollers.modularmenu;

import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import me.csaprotocol.tortoisehospital.Main;
import me.csaprotocol.tortoisehospital.controllers.ControllerOrchestrator;
import me.csaprotocol.tortoisehospital.controllers.GUIUtilsController;
import me.csaprotocol.tortoisehospital.entities.Measurement;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ThirdColumnTurtleMenu implements Initializable {

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
        GUIUtilsController guic = new GUIUtilsController();
        measurementBox.getChildren().add(guic.addMeasurementButton(measurement));
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
    public void setWeightLabel(String weight) { weightLabel.setText("Weight: " + weight + " Kg"); }
    public void setWidthLabel(String width) { widthLabel.setText("Width: " + width + " cm"); }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GUIUtilsController guic = new GUIUtilsController();

        //Create a rounded rectangle at runtime for the selectedTurtlePanel
        selectedTurtlePanel.setClip(guic.setRectangleClip(selectedTurtlePanel.getPrefWidth(), selectedTurtlePanel.getPrefHeight()));
        //Create a rounded rectangle at runtime for the selectedMeasurementPanel
        selectedMeasurementPanel.setClip(guic.setRectangleClip(selectedMeasurementPanel.getPrefWidth(), selectedMeasurementPanel.getPrefHeight()));

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
