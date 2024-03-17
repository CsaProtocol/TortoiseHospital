package me.csaprotocol.tortoisehospital.controllers;

import eu.hansolo.fx.charts.ChartType;
import eu.hansolo.fx.charts.ConcentricRingChart;
import eu.hansolo.fx.charts.CoxcombChart;
import eu.hansolo.fx.charts.Symbol;
import eu.hansolo.fx.charts.data.ChartItem;
import eu.hansolo.fx.charts.data.XYChartItem;
import eu.hansolo.fx.charts.series.XYSeries;
import eu.hansolo.fx.charts.series.XYSeriesBuilder;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXStepperToggle;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.mfxresources.fonts.MFXFontIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import me.csaprotocol.tortoisehospital.Main;
import me.csaprotocol.tortoisehospital.entities.Examination;
import me.csaprotocol.tortoisehospital.entities.Measurement;
import me.csaprotocol.tortoisehospital.entities.MedicalRecord;
import me.csaprotocol.tortoisehospital.exceptions.CoreException;
import me.csaprotocol.tortoisehospital.fxmlcontrollers.usermenu.ExaminationButton;
import me.csaprotocol.tortoisehospital.fxmlcontrollers.usermenu.MeasurementButton;
import me.csaprotocol.tortoisehospital.fxmlcontrollers.usermenu.MedicalRecordButton;
import me.csaprotocol.tortoisehospital.fxmlcontrollers.usermenu.TurtleButton;
import org.controlsfx.control.PopOver;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class GUIUtilsController {
    //Add center button
    public Button addCenterButton(String centerID) {
        Button newButton = new Button();
        try {
            newButton = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("resources/fxml/usermenuResources/centerButton.fxml")));
            newButton.setText(centerID);
        } catch (IOException e) {
            e.printStackTrace();
        } return newButton;
    }

    //Add tank button
    public Button addTankButton(String tankID) {
        Button newButton = new Button();
        try {
            newButton = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("resources/fxml/usermenuResources/tankButton.fxml")));
            newButton.setText(tankID);
        } catch (IOException e) {
            e.printStackTrace();
        } return newButton;
    }

    //Third column: turtle button
    public Node addTurtleButton(String TurtleID, String TurtleName) {
        Node newButton = new Button();
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(Main.class.getResource("resources/fxml/usermenuResources/turtleButton.fxml")));
            newButton = loader.load();
            TurtleButton co = loader.getController();
            co.setIdTurtleLabel(TurtleID);
            co.setNameTurtleLabel(TurtleName);
        } catch (IOException e) {
            e.printStackTrace();
        } return newButton;
    }

    //Third Column: measurement Button
    public Node addMeasurementButton(Measurement measurement) {
        Node newButton = new Button();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(Main.class.getResource("resources/fxml/usermenuResources/measurementButton.fxml")));
            newButton = fxmlLoader.load();
            MeasurementButton fxmlCo = fxmlLoader.getController();
            fxmlCo.setDateLabel(measurement.getDate().toString());
            fxmlCo.setMeasurementAssociated(measurement);
        } catch (IOException e) {
            e.printStackTrace();
        } return newButton;
    }

    //Fourth column: Medical Record Button
    public Pane addMedicalRecordButton(MedicalRecord medicalRecordToDisplay) {
        Pane newButton;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(Main.class.getResource("resources/fxml/usermenuResources/medicalRecordButton.fxml")));
            newButton = fxmlLoader.load();
            MedicalRecordButton fxmlCo = fxmlLoader.getController();
            fxmlCo.setMedicalRecord(medicalRecordToDisplay);
            fxmlCo.setAdmissionDateLabel(medicalRecordToDisplay.getAccess_date().toString());
            if(medicalRecordToDisplay.getRelease_date() != null)
                fxmlCo.setDischargeDateLabel(medicalRecordToDisplay.getRelease_date().toString());
            else
                fxmlCo.setDischargeDateLabel("N/A");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } return newButton;
    }

    //Fourth column: Examination Button
    public Pane addExaminationButton(Examination examinationToDisplay) {
        Pane newButton;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(Main.class.getResource("resources/fxml/usermenuResources/examinationButton.fxml")));
            newButton = fxmlLoader.load();
            ExaminationButton fxmlCo = fxmlLoader.getController();
            fxmlCo.setAssociatedExamination(examinationToDisplay);
            fxmlCo.setExaminationDate(examinationToDisplay.getDate().toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } return newButton;
    }

    //Fourth column: Turtle Hospital
    public void setExaminationCirclesPopOver(Circle bodyPart, String bodyPartName) {
        PopOver explanationPopOver = new PopOver(new Label(" State of the " + bodyPartName));
        explanationPopOver.setArrowLocation(PopOver.ArrowLocation.RIGHT_TOP);
        bodyPart.setOnMouseEntered(event -> explanationPopOver.show(bodyPart));
        bodyPart.setOnMouseExited(event -> explanationPopOver.hide());
    }

    //RectangleClip
    public Rectangle setRectangleClip(double width, double height) {
        Rectangle clip = new Rectangle(width, height);
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        return clip;
    }

    //Fourth column: Turtle Stats
    public XYSeries<XYChartItem> createData(LocalDate startDate, LocalDate endDate) throws CoreException {
        ControllerOrchestrator co = new ControllerOrchestrator();

            @SuppressWarnings("unchecked")
            XYSeries<XYChartItem> xySeries = XYSeriesBuilder.create()
                .items(co.handleTurtleStats(startDate, endDate))
                .chartType(ChartType.SMOOTH_LINE)
                .fill(Color.web("#00AEF520"))
                .stroke(Color.web("#00AEF5"))
                .symbolFill(Color.web("#00AEF5"))
                .symbolStroke(Color.web("#293C47"))
                .symbolSize(6)
                .strokeWidth(2)
                .animated(true)
                .symbol(Symbol.CIRCLE)
                .symbolsVisible(true)
                .build();

            return xySeries;
    }

    //Third column: Center stats
    public ConcentricRingChart buildConcentricRingChartCenter(Integer[] values) {

        ConcentricRingChart chart = new ConcentricRingChart();
        ChartItem compromised = new ChartItem("Compromised", values[0]);
        ChartItem deepWounds = new ChartItem("Deep wounds", values[1]);
        ChartItem lightWounds = new ChartItem("Light wounds", values[2]);
        ChartItem normal = new ChartItem("Normal", values[3]);
        ChartItem perfect = new ChartItem("Perfect", values[4]);

        compromised.setFill(Color.web("#de425b"));
        deepWounds.setFill(Color.web("#f1a372"));
        lightWounds.setFill(Color.web("#fef0c4"));
        normal.setFill(Color.web("#99bc88"));
        perfect.setFill(Color.web("#488f31"));

        chart.setItems(compromised, deepWounds, lightWounds, normal, perfect);
        return chart;
    }

    public CoxcombChart buildCoxCombChartCenter(Integer[] values) {
        CoxcombChart chart = new CoxcombChart();
        ChartItem compromised = new ChartItem("Compromised", values[0]);
        ChartItem deepWounds = new ChartItem("Deep wounds", values[1]);
        ChartItem lightWounds = new ChartItem("Light wounds", values[2]);
        ChartItem normal = new ChartItem("Normal", values[3]);
        ChartItem perfect = new ChartItem("Perfect", values[4]);

        compromised.setFill(Color.web("#de425b"));
        deepWounds.setFill(Color.web("#f1a372"));
        lightWounds.setFill(Color.web("#fef0c4"));
        normal.setFill(Color.web("#99bc88"));
        perfect.setFill(Color.web("#488f31"));

        chart.setItems(compromised, deepWounds, lightWounds, normal, perfect);
        return chart;
    }

    //NewExamination Auxiliary Code
    public VBox ExaminationAuxiliaryVBox(TextArea vetNotes,
                                         MFXComboBox<String> head_statusField,
                                         MFXComboBox<String> eyes_statusField,
                                         MFXComboBox<String> beak_statusField,
                                         MFXComboBox<String> neck_statusField,
                                         MFXComboBox<String> nose_statusField,
                                         MFXComboBox<String> fins_statusField,
                                         MFXComboBox<String> tail_statusField,
                                         MFXCheckbox checkbox) {

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

        return content;
    }

    public List<MFXStepperToggle> ExaminationAuxStepperCreation(TextArea vetNotes,
                                                                MFXComboBox<String> head_statusField,
                                                                MFXComboBox<String> eyes_statusField,
                                                                MFXComboBox<String> beak_statusField,
                                                                MFXComboBox<String> neck_statusField,
                                                                MFXComboBox<String> nose_statusField,
                                                                MFXComboBox<String> fins_statusField,
                                                                MFXComboBox<String> tail_statusField,
                                                                MFXCheckbox checkbox) {

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
        Node thirdStepContent = ExaminationAuxiliaryVBox(vetNotes, head_statusField, eyes_statusField, beak_statusField, neck_statusField, nose_statusField, fins_statusField, tail_statusField, checkbox);
        thirdStep.setContent(thirdStepContent);
        thirdStep.getValidator().constraint("Please tick the box to confirm the data is correct", checkbox.selectedProperty());

        return List.of(firstStep, secondStep, thirdStep);
    }

    public void textFieldCreation(MFXTextField textField, String text) {
        textField.setPromptText(text);
        textField.setMinWidth(200);
        textField.setMaxWidth(200);
        textField.setMinHeight(30);
        textField.setMaxHeight(30);
    }

    public MFXTextField labelText(String text) {
        MFXTextField label = MFXTextField.asLabel(text);
        label.setPrefWidth(200);
        label.setMinWidth(Region.USE_PREF_SIZE);
        label.setMaxWidth(Region.USE_PREF_SIZE);
        label.setMinHeight(30);
        label.setMaxHeight(30);
        return label;
    }

    public void popOverCreation(Node node, String message) {
        Label explanationLabel = new Label(message);
        PopOver explanationPopOver = new PopOver(explanationLabel);
        explanationPopOver.setArrowLocation(PopOver.ArrowLocation.RIGHT_TOP);
        node.setOnMouseEntered(event -> explanationPopOver.show(node));
        node.setOnMouseExited(event -> explanationPopOver.hide());
    }

}
