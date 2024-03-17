package me.csaprotocol.tortoisehospital.fxmlcontrollers.steppers;

import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.mfxresources.fonts.MFXFontIcon;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
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
import me.csaprotocol.tortoisehospital.controllers.DataController;
import me.csaprotocol.tortoisehospital.entities.enums.Sex;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UpdateTurtle implements Initializable {
    private final MFXTextField turtleNameField;
    private final MFXComboBox<String> speciesField;
    private final MFXComboBox<String> sexField;
    private final MFXCheckbox checkbox;
    @FXML private MFXStepper stepper;
    @FXML private GridPane grid;

    public UpdateTurtle() {
        turtleNameField = new MFXTextField();
        speciesField = new MFXComboBox<>();
        sexField = new MFXComboBox<>();
        checkbox = new MFXCheckbox("Please tick the box to confirm the data is correct");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DataController dataController = DataController.getInstance();

        turtleNameField.setPrefWidth(200);
        turtleNameField.setMinWidth(Region.USE_PREF_SIZE);
        turtleNameField.setMaxWidth(Region.USE_PREF_SIZE);
        turtleNameField.setMinHeight(40);
        turtleNameField.setMaxHeight(40);

        turtleNameField.setFloatingText("Turtle Name");
        turtleNameField.setPromptText(dataController.getSelectedTurtle().getName());


        speciesField.setPrefWidth(200);
        speciesField.setMinWidth(Region.USE_PREF_SIZE);
        speciesField.setMaxWidth(Region.USE_PREF_SIZE);
        speciesField.setMinHeight(40);
        speciesField.setMaxHeight(40);

        speciesField.setItems(FXCollections.observableArrayList("Chelonia mydas","Eretmochelys imbricata","Dermochelys coriacea","Caretta caretta","Natator depressus","Testudo graeca","Chrysemys picta","Trachemys scripta","Emys orbicularis","Kinosternon scorpioides"));
        speciesField.setFloatingText("Species");
        speciesField.setPromptText(dataController.getSelectedTurtle().getSpecies());


        sexField.setPrefWidth(200);
        sexField.setMinWidth(Region.USE_PREF_SIZE);
        sexField.setMaxWidth(Region.USE_PREF_SIZE);
        sexField.setMinHeight(40);
        sexField.setMaxHeight(40);

        sexField.setFloatingText("Sex");
        sexField.setItems(FXCollections.observableArrayList("Male", "Female"));
        sexField.setPromptText(dataController.getSelectedTurtle().getSex().toString());

        List<MFXStepperToggle> toggles = stepperCreationAuxiliary();
        stepper.getStepperToggles().addAll(toggles);
    }

    private List<MFXStepperToggle> stepperCreationAuxiliary() {
        MFXStepperToggle firstStep = new MFXStepperToggle("Update info", new MFXFontIcon("fas-plus", 16, Color.web("#58f46c")));
        VBox firstStepContent = new VBox(20, turtleNameField, speciesField, sexField);
        firstStepContent.setSpacing(5);
        firstStepContent.setAlignment(Pos.CENTER);
        firstStep.setContent(firstStepContent);
        firstStep.getValidator().dependsOn(turtleNameField.getValidator().dependsOn(sexField.getValidator()));

        MFXStepperToggle secondStep = new MFXStepperToggle("Check info", new MFXFontIcon("fas-check", 16, Color.web("#39beee")));
        Node secondStepContent = createCheckInfoContent();
        secondStep.setContent(secondStepContent);
        secondStep.getValidator().constraint("Please tick the box to confirm the data is correct", checkbox.selectedProperty());

        return List.of(firstStep, secondStep);
    }

    private Node createCheckInfoContent() {
        MFXTextField immutableTurtleLabel = labelText("Turtle: ");
        MFXTextField turtleLabel = labelText("");
        turtleLabel.textProperty().bind(turtleNameField.textProperty());

        MFXTextField immutableSpeciesLabel = labelText("Species: ");
        MFXTextField speciesLabel = labelText("");
        speciesLabel.textProperty().bind(
            Bindings.createStringBinding(
                () -> speciesField.getValue() != null ? speciesField.getValue() : "",
                speciesField.valueProperty()
            )
        );

        MFXTextField immutableSexLabel = labelText("Sex: ");
        MFXTextField sexLabel = labelText("");
        sexLabel.textProperty().bind(
            Bindings.createStringBinding(
                () -> sexField.getValue() != null ? sexField.getValue() : "",
                sexField.valueProperty()
            )
        );

        HBox turtleBox = new HBox(10, immutableTurtleLabel, turtleLabel);
        turtleBox.setAlignment(Pos.CENTER);
        HBox speciesBox = new HBox(10, immutableSpeciesLabel, speciesLabel);
        speciesBox.setAlignment(Pos.CENTER);
        HBox sexBox = new HBox(10, immutableSexLabel, sexLabel);
        sexBox.setAlignment(Pos.CENTER);

        VBox content = new VBox(10, turtleBox, speciesBox, sexBox, checkbox);
        content.setAlignment(Pos.CENTER);

        stepper.setOnLastNext(
            event -> {
                if (checkbox.isSelected()) {
                    Sex sex;
                    if(sexField.getValue().equals("Male")) {
                        sex = Sex.Male;
                    } else { sex = Sex.Female; }

                    ControllerOrchestrator co = new ControllerOrchestrator();
                    co.handleTurtleUpdate(turtleNameField.getText(), speciesField.getValue(), sex);
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
