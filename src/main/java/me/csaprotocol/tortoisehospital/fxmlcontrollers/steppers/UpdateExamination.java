package me.csaprotocol.tortoisehospital.fxmlcontrollers.steppers;

import io.github.palexdev.materialfx.controls.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import me.csaprotocol.tortoisehospital.controllers.ControllerOrchestrator;
import me.csaprotocol.tortoisehospital.controllers.DataController;
import me.csaprotocol.tortoisehospital.controllers.GUIUtilsController;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UpdateExamination implements Initializable {
    private final MFXComboBox<String> head_statusField;
    private final MFXComboBox<String> eyes_statusField;
    private final MFXComboBox<String> beak_statusField;
    private final MFXComboBox<String> neck_statusField;
    private final MFXComboBox<String> nose_statusField;
    private final MFXComboBox<String> fins_statusField;
    private final MFXComboBox<String> tail_statusField;
    private final TextArea vetNotes;
    private final MFXCheckbox checkbox;

    @FXML
    private MFXStepper stepper;

    public UpdateExamination() {
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
        DataController dc = DataController.getInstance();
        GUIUtilsController guic = new GUIUtilsController();

        List<MFXStepperToggle> toggles = guic.ExaminationAuxStepperCreation(vetNotes, head_statusField, eyes_statusField, beak_statusField, neck_statusField, nose_statusField, fins_statusField, tail_statusField, checkbox);

        head_statusField.setFloatingText("Head status");

        head_statusField.setPromptText(String.valueOf(dc.getSelectedExamination().getHead_status()));
        eyes_statusField.setFloatingText("Eyes status");

        eyes_statusField.setPromptText(String.valueOf(dc.getSelectedExamination().getEyes_status()));
        beak_statusField.setFloatingText("Beak status");

        beak_statusField.setPromptText(String.valueOf(dc.getSelectedExamination().getBeak_status()));
        neck_statusField.setFloatingText("Neck status");

        neck_statusField.setPromptText(String.valueOf(dc.getSelectedExamination().getNeck_status()));
        nose_statusField.setFloatingText("Nose status");

        nose_statusField.setPromptText(String.valueOf(dc.getSelectedExamination().getNose_status()));
        fins_statusField.setFloatingText("Fins status");

        fins_statusField.setPromptText(String.valueOf(dc.getSelectedExamination().getFins_status()));
        tail_statusField.setFloatingText("Tail status");

        tail_statusField.setPromptText(String.valueOf(dc.getSelectedExamination().getTail_status()));
        vetNotes.setText(dc.getSelectedExamination().getVet_notes());
        stepper.getStepperToggles().addAll(toggles);

        stepper.setOnLastNext(
            event -> {
                if(checkbox.isSelected()) {
                    ControllerOrchestrator co = new ControllerOrchestrator();
                    co.handleExaminationUpdate(head_statusField.getValue(), eyes_statusField.getValue(), beak_statusField.getValue(), neck_statusField.getValue(), nose_statusField.getValue(), fins_statusField.getValue(), tail_statusField.getValue(), vetNotes.getText());
                }
            }
        );
    }
}
