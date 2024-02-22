package me.csaprotocol.tortoisehospital.fxmlcontrollers.steppers;

import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.mfxresources.fonts.MFXFontIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import me.csaprotocol.tortoisehospital.controllers.ControllerOrchestrator;
import me.csaprotocol.tortoisehospital.controllers.DaoController;
import me.csaprotocol.tortoisehospital.controllers.DataController;
import me.csaprotocol.tortoisehospital.entities.Center;
import me.csaprotocol.tortoisehospital.entities.Tank;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class turtleManagementGUI implements Initializable {
    //First step
    private final MFXTextField turtleNameField;
    private final MFXComboBox<String> speciesField;
    private final MFXComboBox<String> sexField;

    //Second step
    private final MFXComboBox<String> tankField;
    private final MFXComboBox<String> centerField;
    private final MFXDatePicker accessDateField;
    private final MFXTextField latitudeField;
    private final MFXTextField longitudeField;

    //Third step
    private final MFXComboBox<String> head_statusField;
    private final MFXComboBox<String> eyes_statusField;
    private final MFXComboBox<String> beak_statusField;
    private final MFXComboBox<String> neck_statusField;
    private final MFXComboBox<String> nose_statusField;
    private final MFXComboBox<String> fins_statusField;
    private final MFXComboBox<String> tail_statusField;
    private final MFXTextField vetNotes;




    @FXML private MFXStepper stepper;

    public turtleManagementGUI() {
        turtleNameField = new MFXTextField();
        speciesField = new MFXComboBox<>();
        sexField = new MFXComboBox<>();
        tankField = new MFXComboBox<>();
        centerField = new MFXComboBox<>();
        accessDateField = new MFXDatePicker();
        latitudeField = new MFXTextField();
        longitudeField = new MFXTextField();
        head_statusField = new MFXComboBox<>();
        eyes_statusField = new MFXComboBox<>();
        beak_statusField = new MFXComboBox<>();
        neck_statusField = new MFXComboBox<>();
        nose_statusField = new MFXComboBox<>();
        fins_statusField = new MFXComboBox<>();
        tail_statusField = new MFXComboBox<>();
        vetNotes = new MFXTextField();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        turtleNameField.setPromptText("Turtle Name");
        speciesField.setItems(FXCollections.observableArrayList("Chelonia mydas","Eretmochelys imbricata","Dermochelys coriacea","Caretta caretta","Natator depressus","Testudo graeca","Chrysemys picta","Trachemys scripta","Emys orbicularis","Kinosternon scorpioides"));
        sexField.setItems(FXCollections.observableArrayList("Male", "Female"));
        List<MFXStepperToggle> toggles = stepperCreationAuxiliary();
        stepper.setStepperToggles(toggles);
    }

    private List<MFXStepperToggle> stepperCreationAuxiliary() {
        MFXStepperToggle firstStep = new MFXStepperToggle("Turtle Info", new MFXFontIcon("fas-stethoscope", 16, Color.web("#f1c40f")));
        VBox firstStepContent = new VBox();
        turtleNameField.setPromptText("Name");
        speciesField.setPromptText("Species");
        sexField.setPromptText("Sex");
        firstStepContent.setAlignment(Pos.CENTER);
        firstStepContent.getChildren().addAll(turtleNameField, speciesField, sexField);
        firstStep.setContent(firstStepContent);

        MFXStepperToggle secondStep = new MFXStepperToggle("General info", new MFXFontIcon("fas-user", 16, Color.web("#49a6d7")));
        VBox secondStepContent = new VBox();

        ArrayList<Center> centersArray = new ArrayList<>(new DaoController().getCentersByEmployeeID());
        for (Center center : centersArray) {
            centerField.getItems().add(center.getID());
        }
        centerField.valueProperty().addListener((observable, oldValue, newValue) -> {
            tankField.getItems().clear();
            String selectedItem = (String) newValue;
            DaoController dC = new DaoController();
            ArrayList<Tank> tanksArray = new ArrayList<>(dC.getTanksByCenterID(selectedItem));

            for (Tank tank : tanksArray) {
                tankField.getItems().add(String.valueOf(tank.getTankID()));
            }
        });
        centerField.setPromptText("Center ID");
        tankField.setPromptText("Tank ID");
        accessDateField.setPromptText("Access Date");
        latitudeField.setPromptText("Latitude");
        longitudeField.setPromptText("Longitude");

        secondStepContent.getChildren().addAll(centerField, tankField, accessDateField, latitudeField, longitudeField);
        secondStepContent.setAlignment(Pos.CENTER);
        secondStep.setContent(secondStepContent);

        MFXStepperToggle thirdStep = new MFXStepperToggle("Turtle Examination", new MFXFontIcon("fas-stethoscope", 16, Color.web("#f1c40f")));
        VBox thirdStepContent = new VBox();
        thirdStepContent.setAlignment(Pos.CENTER);
        ObservableList<String> status = FXCollections.observableArrayList("Compromised", "DeepWounds", "SuperficialWounds", "Normal", "Perfect");
        head_statusField.setItems(status); eyes_statusField.setItems(status); beak_statusField.setItems(status); neck_statusField.setItems(status); nose_statusField.setItems(status); fins_statusField.setItems(status); tail_statusField.setItems(status);
        thirdStepContent.getChildren().addAll(head_statusField, eyes_statusField, beak_statusField, neck_statusField, nose_statusField, fins_statusField, tail_statusField, vetNotes);
        thirdStepContent.setAlignment(Pos.CENTER);
        thirdStep.setContent(thirdStepContent);

        return List.of(firstStep, secondStep, thirdStep);
    }

}
