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

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class newExamination implements Initializable {
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

    public newExamination() {
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
        List<MFXStepperToggle> toggles = StepperCreationAuxiliary();
        head_statusField.setPromptText("Head status");
        eyes_statusField.setPromptText("Eyes status");
        beak_statusField.setPromptText("Beak status");
        neck_statusField.setPromptText("Neck status");
        nose_statusField.setPromptText("Nose status");
        fins_statusField.setPromptText("Fins status");
        tail_statusField.setPromptText("Tail status");
        vetNotes.setPromptText("Veterinary notes");
        stepper.getStepperToggles().addAll(toggles);
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
        MFXTextField immutableVetNotes = labelText("Vet Notes: ");
        MFXTextField vetNotesLabel = labelText("");
        vetNotesLabel.textProperty().bind(vetNotes.textProperty());

        MFXTextField immutableHeadStatus = labelText("Head: ");
        MFXTextField headStatusLabel = labelText("");
        headStatusLabel.textProperty().bind(head_statusField.valueProperty());

        MFXTextField immutableEyesStatus = labelText("Eyes: ");
        MFXTextField eyesStatusLabel = labelText("");
        eyesStatusLabel.textProperty().bind(eyes_statusField.valueProperty());

        MFXTextField immutableBeakStatus = labelText("Beak: ");
        MFXTextField beakStatusLabel = labelText("");
        beakStatusLabel.textProperty().bind(beak_statusField.valueProperty());

        MFXTextField immutableNeckStatus = labelText("Neck: ");
        MFXTextField neckStatusLabel = labelText("");
        neckStatusLabel.textProperty().bind(neck_statusField.valueProperty());

        MFXTextField immutableNoseStatus = labelText("Nose: ");
        MFXTextField noseStatusLabel = labelText("");
        noseStatusLabel.textProperty().bind(nose_statusField.valueProperty());

        MFXTextField immutableFinsStatus = labelText("Fins: ");
        MFXTextField finsStatusLabel = labelText("");
        finsStatusLabel.textProperty().bind(fins_statusField.valueProperty());

        MFXTextField immutableTailStatus = labelText("Tail: ");
        MFXTextField tailStatusLabel = labelText("");
        tailStatusLabel.textProperty().bind(tail_statusField.valueProperty());

        HBox box1 = new HBox(10, immutableHeadStatus, headStatusLabel);
        HBox box2 = new HBox(10, immutableEyesStatus, eyesStatusLabel);
        HBox box3 = new HBox(10, immutableBeakStatus, beakStatusLabel);
        HBox box4 = new HBox(10, immutableNeckStatus, neckStatusLabel);
        HBox box5 = new HBox(10, immutableNoseStatus, noseStatusLabel);
        HBox box6 = new HBox(10, immutableFinsStatus, finsStatusLabel);
        HBox box7 = new HBox(10, immutableTailStatus, tailStatusLabel);
        HBox box8 = new HBox(10, immutableVetNotes, vetNotesLabel);
        box1.setAlignment(Pos.CENTER); box2.setAlignment(Pos.CENTER); box3.setAlignment(Pos.CENTER); box4.setAlignment(Pos.CENTER); box5.setAlignment(Pos.CENTER); box6.setAlignment(Pos.CENTER); box7.setAlignment(Pos.CENTER); box8.setAlignment(Pos.CENTER);

        VBox content = new VBox(10, box1, box2, box3, box4, box5, box6, box7, box8, checkbox);
        content.setAlignment(Pos.CENTER);
        content.setSpacing(5);

        stepper.setOnLastNext(
            event -> {
                if(checkbox.isSelected()) {
                    ControllerOrchestrator co = new ControllerOrchestrator();
                    co.handleNewExamination(head_statusField.getValue(), eyes_statusField.getValue(), beak_statusField.getValue(), neck_statusField.getValue(), nose_statusField.getValue(), fins_statusField.getValue(), tail_statusField.getValue(), vetNotes.getText());
                }
            }
        );
        return content;
    }
    private MFXTextField labelText(String text) {
        MFXTextField label = MFXTextField.asLabel(text);
        label.setPrefWidth(200);
        label.setMinWidth(Region.USE_PREF_SIZE);
        label.setMaxWidth(Region.USE_PREF_SIZE);
        label.setMinHeight(30);
        label.setMaxHeight(30);
        return label;
    }
}
