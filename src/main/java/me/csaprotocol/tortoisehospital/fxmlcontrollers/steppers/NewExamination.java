package me.csaprotocol.tortoisehospital.fxmlcontrollers.steppers;

import io.github.palexdev.materialfx.controls.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import me.csaprotocol.tortoisehospital.controllers.ControllerOrchestrator;
import me.csaprotocol.tortoisehospital.controllers.GUIUtilsController;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class NewExamination implements Initializable {
    private final MFXComboBox<String> head_statusField;
    private final MFXComboBox<String> eyes_statusField;
    private final MFXComboBox<String> beak_statusField;
    private final MFXComboBox<String> neck_statusField;
    private final MFXComboBox<String> nose_statusField;
    private final MFXComboBox<String> fins_statusField;
    private final MFXComboBox<String> tail_statusField;
    private final TextArea vetNotes;
    private final MFXCheckbox checkbox;

    @FXML private MFXStepper stepper;

    public NewExamination() {
        head_statusField = new MFXComboBox<>();
        eyes_statusField = new MFXComboBox<>();
        beak_statusField = new MFXComboBox<>();
        neck_statusField = new MFXComboBox<>();
        nose_statusField = new MFXComboBox<>();
        fins_statusField = new MFXComboBox<>();
        tail_statusField = new MFXComboBox<>();
        vetNotes = new TextArea();
        checkbox = new MFXCheckbox("Please tick the box to confirm the data is correct");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GUIUtilsController guic = new GUIUtilsController();
        List<MFXStepperToggle> toggles = guic.ExaminationAuxStepperCreation(vetNotes, head_statusField, eyes_statusField, beak_statusField, neck_statusField, nose_statusField, fins_statusField, tail_statusField, checkbox);
        head_statusField.setPromptText("Head status");
        eyes_statusField.setPromptText("Eyes status");
        beak_statusField.setPromptText("Beak status");
        neck_statusField.setPromptText("Neck status");
        nose_statusField.setPromptText("Nose status");
        fins_statusField.setPromptText("Fins status");
        tail_statusField.setPromptText("Tail status");
        vetNotes.setPromptText("Veterinary notes");

        stepper.getStepperToggles().addAll(toggles);
        stepper.setOnLastNext(
            event -> {
                if(checkbox.isSelected()) {
                    ControllerOrchestrator co = new ControllerOrchestrator();
                    co.handleNewExamination(head_statusField.getValue(), eyes_statusField.getValue(), beak_statusField.getValue(), neck_statusField.getValue(), nose_statusField.getValue(), fins_statusField.getValue(), tail_statusField.getValue(), vetNotes.getText());
                }
            }
        );
    }

}
