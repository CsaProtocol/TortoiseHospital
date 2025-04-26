package me.csaprotocol.tortoisehospital.fxmlcontrollers.steppers;

import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.mfxresources.fonts.MFXFontIcon;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import me.csaprotocol.tortoisehospital.controllers.ControllerOrchestrator;
import me.csaprotocol.tortoisehospital.controllers.GUIUtilsController;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class NewMeasurement implements Initializable {
    private final MFXDatePicker measurementDateField;
    private final MFXTextField widthField;
    private final MFXTextField lengthField;
    private final MFXTextField weightField;
    private final MFXCheckbox checkbox;
    @FXML private MFXStepper stepper;
    public NewMeasurement() {
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
        GUIUtilsController guic = new GUIUtilsController();
        measurementDateField.setPromptText("Measurement Date");
        guic.textFieldCreation(widthField, "Width");
        widthField.measureUnitProperty().setValue("cm");
        guic.textFieldCreation(lengthField, "Length");
        lengthField.measureUnitProperty().setValue("cm");
        guic.textFieldCreation(weightField, "Weight");
        weightField.measureUnitProperty().setValue("Kg");

        MFXStepperToggle firstStep = new MFXStepperToggle("General info", new MFXFontIcon("fas-plus", 16));
        VBox firstStepContent = new VBox();
        firstStepContent.setAlignment(Pos.CENTER);
        firstStepContent.setSpacing(10);
        firstStepContent.getChildren().addAll(measurementDateField, widthField, lengthField, weightField);
        firstStep.setContent(firstStepContent);

        MFXStepperToggle secondStep = new MFXStepperToggle("Check info", new MFXFontIcon("fas-check", 16, Color.web("#39beee")));
        Node secondStepContent = createCheckInfoContent();
        secondStep.setContent(secondStepContent);
        secondStep.getValidator().constraint("Please tick the box to confirm the data is correct", checkbox.selectedProperty());

        return List.of(firstStep, secondStep);
    }

    private Node createCheckInfoContent() {
        GUIUtilsController guic = new GUIUtilsController();
        MFXTextField immutableMeasurementDateLabel = guic.labelText("Measurement Date: ");
        MFXTextField measurementDateLabel = guic.labelText("");
        measurementDateLabel.textProperty().bind(
            Bindings.createStringBinding(
                () -> measurementDateField.getValue() != null ? String.valueOf(measurementDateField.getValue()) : "",
                measurementDateField.valueProperty()
            )
        );

        MFXTextField immutableWidthLabel = guic.labelText("Width: ");
        MFXTextField widthLabel = guic.labelText("");
        widthLabel.textProperty().bind(widthField.textProperty());

        MFXTextField immutableLengthLabel = guic.labelText("Length: ");
        MFXTextField lengthLabel = guic.labelText("");
        lengthLabel.textProperty().bind(lengthField.textProperty());

        MFXTextField immutableWeightLabel = guic.labelText("Weight: ");
        MFXTextField weightLabel = guic.labelText("");
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
}
