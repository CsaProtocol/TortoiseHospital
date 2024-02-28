package me.csaprotocol.tortoisehospital.fxmlcontrollers.steppers;

import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.mfxresources.fonts.MFXFontIcon;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import me.csaprotocol.tortoisehospital.controllers.ControllerOrchestrator;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class newMeasurement implements Initializable {
    private final MFXDatePicker measurementDateField;
    private final MFXTextField widthField;
    private final MFXTextField lengthField;
    private final MFXTextField weightField;
    private final MFXCheckbox checkbox;
    @FXML private MFXStepper stepper;
    public newMeasurement() {
        measurementDateField = new MFXDatePicker();
        widthField = new MFXTextField();
        lengthField = new MFXTextField();
        weightField = new MFXTextField();
        checkbox = new MFXCheckbox("Please tick the box to confirm the data is correct");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<MFXStepperToggle> toggles = stepperCreationAuxiliary();
        stepper.setStepperToggles(toggles);
    }

    private List<MFXStepperToggle> stepperCreationAuxiliary() {
        measurementDateField.setPromptText("Measurement Date");
        widthField.setPromptText("Width");
        lengthField.setPromptText("Length");
        weightField.setPromptText("Weight");

        MFXStepperToggle firstStep = new MFXStepperToggle("General info", new MFXFontIcon("fas-plus", 16));
        VBox firstStepContent = new VBox();
        firstStepContent.setSpacing(3);
        firstStepContent.getChildren().addAll(measurementDateField, widthField, lengthField, weightField);
        firstStep.setContent(firstStepContent);

        MFXStepperToggle secondStep = new MFXStepperToggle("Check info", new MFXFontIcon("fas-check", 16, Color.web("#39beee")));
        Node secondStepContent = createCheckInfoContent();
        secondStep.setContent(secondStepContent);
        secondStep.getValidator().constraint("Please tick the box to confirm the data is correct", checkbox.selectedProperty());

        return List.of(firstStep, secondStep);
    }

    private Node createCheckInfoContent() {
        MFXTextField immutableMeasurementDateLabel = labelText("Measurement Date: ");
        MFXTextField measurementDateLabel = labelText("");
        measurementDateLabel.textProperty().bind(
            Bindings.createStringBinding(
                () -> measurementDateField.getValue() != null ? String.valueOf(measurementDateField.getValue()) : "",
                measurementDateField.valueProperty()
            )
        );

        MFXTextField immutableWidthLabel = labelText("Width: ");
        MFXTextField widthLabel = labelText("");
        widthLabel.textProperty().bind(widthField.textProperty());

        MFXTextField immutableLengthLabel = labelText("Length: ");
        MFXTextField lengthLabel = labelText("");
        lengthLabel.textProperty().bind(lengthField.textProperty());

        MFXTextField immutableWeightLabel = labelText("Weight: ");
        MFXTextField weightLabel = labelText("");
        weightLabel.textProperty().bind(weightField.textProperty());

        HBox dateBox = new HBox(10, immutableMeasurementDateLabel, measurementDateLabel);
        dateBox.setAlignment(Pos.CENTER);
        HBox widthBox = new HBox(10, immutableWidthLabel, widthLabel);
        widthBox.setAlignment(Pos.CENTER);
        HBox lengthBox = new HBox(10, immutableLengthLabel, lengthLabel);
        lengthBox.setAlignment(Pos.CENTER);
        HBox weightBox = new HBox(10, immutableWeightLabel, weightLabel);
        weightBox.setAlignment(Pos.CENTER);

        VBox content = new VBox(10, dateBox, widthBox, lengthBox, weightBox, checkbox);
        content.setAlignment(Pos.CENTER);

        stepper.setOnLastNext(
            event -> {
                if (checkbox.isSelected()) {
                    ControllerOrchestrator co = new ControllerOrchestrator();
                    co.handleNewMeasurement(
                        String.valueOf(measurementDateField.getValue()),
                        widthField.getText(),
                        lengthField.getText(),
                        weightField.getText()
                    );
                }
            });

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
