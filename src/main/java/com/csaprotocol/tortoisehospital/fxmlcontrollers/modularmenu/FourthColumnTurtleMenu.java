package me.csaprotocol.tortoisehospital.fxmlcontrollers.modularmenu;

import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import lombok.Getter;
import me.csaprotocol.tortoisehospital.controllers.ControllerOrchestrator;
import me.csaprotocol.tortoisehospital.controllers.GUIUtilsController;
import me.csaprotocol.tortoisehospital.entities.Examination;
import me.csaprotocol.tortoisehospital.entities.MedicalRecord;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class FourthColumnTurtleMenu implements Initializable {

    //First row: Medical Records
    @FXML private Pane fourthColumn;
    @FXML private MFXScrollPane scrollPaneMedicalRecord;
    @FXML private HBox medicalRecordBox;
    @FXML private Pane selectedMedicalRecordPanel;
    @FXML private Label internalIDLabel;
    @FXML private Label admissionLabel;
    @FXML private Label dischargeLabel;
    @FXML private Label locationLabel;

    //Second row: Examinations
    @FXML private MFXScrollPane scrollPaneExamination;
    @FXML private HBox examinationBox;
    @FXML private Pane selectedExaminationPanel;
    @FXML private Label examinationDateLabel;
    @FXML private Label vetNotesLabel;
    @FXML @Getter private Circle beakCircle;
    @FXML @Getter private Circle noseCircle;
    @FXML @Getter private Circle eyesCircle;
    @FXML @Getter private Circle headCircle;
    @FXML @Getter private Circle tailCircle;
    @FXML @Getter private Circle neckCircle;
    @FXML @Getter private Circle finsCircle;

    public void addMedicalRecordButton(MedicalRecord medicalRecordToDisplay) {
        GUIUtilsController guic = new GUIUtilsController();
        medicalRecordBox.getChildren().add(guic.addMedicalRecordButton(medicalRecordToDisplay));
    }

    public void clearMedicalRecordButtons() {
        medicalRecordBox.getChildren().clear();
    }
    public void setMedicalRecordInternalIDLabel(String internalID) {
        internalIDLabel.setText(internalID);
    }
    public void setAdmissionDateLabel(String admissionDate) {
        admissionLabel.setText("Admission: " + admissionDate);
    }
    public void setDischargeDateLabel(LocalDate dischargeDate) {
        if(dischargeDate != null)
            dischargeLabel.setText("Discharge: " + dischargeDate);
        else
            dischargeLabel.setText("Discharge: N/A");
    }
    public void setLocationLabel(String location) {
        locationLabel.setText("Turtle found at: " + location);
    }

    public void addExaminationButton(Examination examinationToDisplay) {
        GUIUtilsController guic = new GUIUtilsController();
        examinationBox.getChildren().add(guic.addExaminationButton(examinationToDisplay));
    }
    public void clearExaminationButtons() {
        examinationBox.getChildren().clear();
    }
    public void setExaminationDateLabel(String examinationDate) { examinationDateLabel.setText("Exam date: " + examinationDate); }
    public void setVetNotesLabel(String vetNotes) {
        vetNotesLabel.setText(vetNotes);
    }
    public void setExaminationCirclesColor(Circle bodyPart, Color color) {
        bodyPart.setFill(color);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GUIUtilsController guic = new GUIUtilsController();

        selectedExaminationPanel.setClip(guic.setRectangleClip(selectedExaminationPanel.getPrefWidth(), selectedExaminationPanel.getPrefHeight()));
        selectedMedicalRecordPanel.setClip(guic.setRectangleClip(selectedMedicalRecordPanel.getPrefWidth(), selectedMedicalRecordPanel.getPrefHeight()));

        examinationBox.setSpacing(4);
        medicalRecordBox.setSpacing(4);

        //PopOver for the Examination circles: explain what the part is
        guic.setExaminationCirclesPopOver(beakCircle, "beak");
        guic.setExaminationCirclesPopOver(noseCircle, "nose");
        guic.setExaminationCirclesPopOver(eyesCircle, "eyes");
        guic.setExaminationCirclesPopOver(headCircle, "head");
        guic.setExaminationCirclesPopOver(tailCircle, "tail");
        guic.setExaminationCirclesPopOver(neckCircle, "neck");
        guic.setExaminationCirclesPopOver(finsCircle, "fins");
    }

    @FXML
    void onUpdateExaminationButtonClick(MouseEvent event) {
        ControllerOrchestrator co = new ControllerOrchestrator();
        co.showUpdateExaminationGUI(new Stage());
    }

    @FXML
    void onMedRecordAddButtonClick(MouseEvent event) {
        ControllerOrchestrator co = new ControllerOrchestrator();
        co.showNewMedicalRecordGUI(new Stage());
    }


    @FXML
    void onMedRecordReleaseButtonClick(MouseEvent event) {
        ControllerOrchestrator co = new ControllerOrchestrator();
        co.showDialogReleaseTurtle(event, fourthColumn);
    }


    @FXML
    void onMedButtonDeleteButtonClick(MouseEvent event) {
        ControllerOrchestrator co = new ControllerOrchestrator();
        co.showDialogDeleteMedicalRecord(event, fourthColumn);
    }

    @FXML void onNewExaminationClick(MouseEvent event) {
        ControllerOrchestrator co = new ControllerOrchestrator();
        co.showNewExaminationGUI(new Stage());
    }

    @FXML
    void onDeleteExaminationButtonClick(MouseEvent event) {
        ControllerOrchestrator co = new ControllerOrchestrator();
        co.showDialogDeleteExamination(event, fourthColumn);
    }

}