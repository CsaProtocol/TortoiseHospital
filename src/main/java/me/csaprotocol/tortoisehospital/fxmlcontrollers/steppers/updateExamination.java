package me.csaprotocol.tortoisehospital.fxmlcontrollers.steppers;

import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.mfxresources.fonts.MFXFontIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import me.csaprotocol.tortoisehospital.controllers.ControllerOrchestrator;
import me.csaprotocol.tortoisehospital.controllers.DataController;
import me.csaprotocol.tortoisehospital.controllers.GUIUtilsController;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class updateExamination implements Initializable {
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

    public updateExamination() {
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
        List<MFXStepperToggle> toggles = StepperCreationAuxiliary();
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

    public List<MFXStepperToggle> StepperCreationAuxiliary() {
        MFXStepperToggle firstStep = new MFXStepperToggle("Examination", new MFXFontIcon("fas-plus", 16, Color.web("#f1c40f")));
        VBox firstStepContent = new VBox();
        firstStepContent.setSpacing(4);
        firstStepContent.setAlignment(Pos.CENTER);
        ObservableList<String> status = FXCollections.observableArrayList("Compromised", "DeepWounds", "SuperficialWounds", "Normal", "Perfect");
        head_statusField.setItems(status); eyes_statusField.setItems(status); beak_statusField.setItems(status); neck_statusField.setItems(status); nose_statusField.setItems(status); fins_statusField.setItems(status); tail_statusField.setItems(status);
        firstStepContent.getChildren().addAll(head_statusField, eyes_statusField, beak_statusField, neck_statusField, nose_statusField, fins_statusField, tail_statusField);
        firstStepContent.setAlignment(Pos.CENTER);
        firstStep.setContent(firstStepContent);

        MFXStepperToggle secondStep = new MFXStepperToggle("Veterinary", new MFXFontIcon("fas-stethoscope", 16, Color.web("#39beee")));
        VBox secondStepContent = new VBox();
        secondStepContent.setAlignment(Pos.CENTER);
        vetNotes.setMinHeight(200);
        vetNotes.setMinWidth(400);
        secondStepContent.getChildren().add(vetNotes);
        secondStep.setContent(secondStepContent);

        MFXStepperToggle thirdStep = new MFXStepperToggle("Check info", new MFXFontIcon("fas-check", 16, Color.web("#ff666a")));
        Node thirdStepContent = createCheckInfoContent();
        thirdStep.setContent(thirdStepContent);
        thirdStep.getValidator().constraint("Please tick the box to confirm the data is correct", checkbox.selectedProperty());

        return List.of(firstStep, secondStep, thirdStep);
    }

    private Node createCheckInfoContent() {
        GUIUtilsController guic = new GUIUtilsController();
        VBox content = guic.ExaminationAuxiliaryVBox(vetNotes, head_statusField, eyes_statusField, beak_statusField, neck_statusField, nose_statusField, fins_statusField, tail_statusField, checkbox);

        return content;
    }
}
