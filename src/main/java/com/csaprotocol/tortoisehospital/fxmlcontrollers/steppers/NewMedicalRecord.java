package me.csaprotocol.tortoisehospital.fxmlcontrollers.steppers;

import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.mfxresources.fonts.MFXFontIcon;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import me.csaprotocol.tortoisehospital.controllers.ControllerOrchestrator;
import me.csaprotocol.tortoisehospital.controllers.DaoController;
import me.csaprotocol.tortoisehospital.entities.Center;
import me.csaprotocol.tortoisehospital.entities.Tank;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class NewMedicalRecord implements Initializable {
    private final MFXComboBox<String> tankField;
    private final MFXComboBox<String> centerField;
    private final MFXDatePicker accessDateField;
    private final MFXTextField latitudeField;
    private final MFXTextField longitudeField;
    private final MFXCheckbox checkbox;

    @FXML private MFXStepper stepper;
    @FXML private GridPane grid;

    public NewMedicalRecord() {
        tankField = new MFXComboBox<>();
        centerField = new MFXComboBox<>();
        accessDateField = new MFXDatePicker();
        latitudeField = new MFXTextField();
        longitudeField = new MFXTextField();
        checkbox = new MFXCheckbox("Please tick the box to confirm the data is correct");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<MFXStepperToggle> toggles = stepperCreationAuxiliary();
        stepper.setStepperToggles(toggles);
    }

    private List<MFXStepperToggle> stepperCreationAuxiliary() {
        centerField.setPromptText("Center ID");
        tankField.setPromptText("Tank ID");
        accessDateField.setPromptText("Access Date");
        latitudeField.setPromptText("Latitude");
        longitudeField.setPromptText("Longitude");

        MFXStepperToggle firstStep = new MFXStepperToggle("General info", new MFXFontIcon("fas-plus", 16, Color.web("#49a6d7")));
        VBox firstStepContent = new VBox();
        firstStepContent.setSpacing(3);
        firstStepContent.setAlignment(Pos.CENTER);
        ArrayList<Center> centersArray = new ArrayList<>(new DaoController().getCentersByEmployeeID());
        for (Center center : centersArray) {
            centerField.getItems().add(center.getID());
        }
        centerField.valueProperty().addListener((observable, oldValue, newValue) -> {
            tankField.getItems().clear();
            DaoController dC = new DaoController();
            ArrayList<Tank> tanksArray = new ArrayList<>(dC.getTanksByCenterID((String) newValue));

            for (Tank tank : tanksArray) {
                tankField.getItems().add(String.valueOf(tank.getTankID()));
            }
        });
        firstStepContent.getChildren().addAll(centerField, tankField, accessDateField, latitudeField, longitudeField);
        firstStep.setContent(firstStepContent);

        MFXStepperToggle secondStep = new MFXStepperToggle("Check info", new MFXFontIcon("fas-check", 16, Color.web("#39beee")));
        Node secondStepContent = createCheckInfoContent();
        secondStep.setContent(secondStepContent);
        secondStep.getValidator().constraint("Please tick the box to confirm the data is correct", checkbox.selectedProperty());

        return List.of(firstStep, secondStep);
    }

    private Node createCheckInfoContent() {
        MFXTextField immutableCenterLabel = labelText("Center: ");
        MFXTextField centerLabel = labelText("");
        centerLabel.textProperty().bind(
            Bindings.createStringBinding(
                () -> centerField.getValue() != null ? centerField.getValue() : "",
                centerField.valueProperty()
            )
        );

        MFXTextField immutableTankLabel = labelText("Tank: ");
        MFXTextField tankLabel = labelText("");
        tankLabel.textProperty().bind(
            Bindings.createStringBinding(
                () -> tankField.getValue() != null ? tankField.getValue() : "",
                tankField.valueProperty()
            )
        );

        MFXTextField immutableDateLabel = labelText("Access date: ");
        MFXTextField dateLabel = labelText("");
        dateLabel.textProperty().bind(
            Bindings.createStringBinding(
                () -> accessDateField.getValue() != null ? String.valueOf(accessDateField.getValue()) : "",
                accessDateField.valueProperty()
            )
        );

        MFXTextField immutableLatitudeLabel = labelText("Latitude: ");
        MFXTextField latitudeLabel = labelText("");
        latitudeLabel.textProperty().bind(latitudeField.textProperty());

        MFXTextField immutableLongitudeLabel = labelText("Longitude: ");
        MFXTextField longitudeLabel = labelText("");
        longitudeLabel.textProperty().bind(longitudeField.textProperty());


        HBox centerBox = new HBox(10, immutableCenterLabel, centerLabel);
        centerBox.setAlignment(Pos.CENTER);
        HBox tankBox = new HBox(10, immutableTankLabel, tankLabel);
        tankBox.setAlignment(Pos.CENTER);
        HBox dateBox = new HBox(10, immutableDateLabel, dateLabel);
        dateBox.setAlignment(Pos.CENTER);
        HBox latitudeBox = new HBox(10, immutableLatitudeLabel, latitudeLabel);
        latitudeBox.setAlignment(Pos.CENTER);
        HBox longitudeBox = new HBox(10, immutableLongitudeLabel, longitudeLabel);
        longitudeBox.setAlignment(Pos.CENTER);

        VBox content = new VBox(10, dateBox, centerBox, tankBox, latitudeBox, longitudeBox, checkbox);
        content.setAlignment(Pos.CENTER);

        stepper.setOnLastNext(
            event -> {
                if (checkbox.isSelected()) {
                    ControllerOrchestrator co = new ControllerOrchestrator();
                    co.handleNewMedicalRecord(
                        centerField.getValue(),
                        Integer.parseInt(tankField.getValue()),
                        accessDateField.getValue().toString(),
                        latitudeField.getText(),
                        longitudeField.getText()
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
