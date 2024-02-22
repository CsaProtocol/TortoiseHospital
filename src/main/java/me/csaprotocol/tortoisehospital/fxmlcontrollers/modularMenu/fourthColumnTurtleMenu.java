package me.csaprotocol.tortoisehospital.fxmlcontrollers.modularMenu;

import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import lombok.Getter;
import me.csaprotocol.tortoisehospital.Main;
import me.csaprotocol.tortoisehospital.entities.Examination;
import me.csaprotocol.tortoisehospital.entities.MedicalRecord;
import me.csaprotocol.tortoisehospital.fxmlcontrollers.usermenu.examinationButton;
import me.csaprotocol.tortoisehospital.fxmlcontrollers.usermenu.medicalRecordButton;
import org.controlsfx.control.PopOver;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

public class fourthColumnTurtleMenu implements Initializable {

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

    public void addMedicalRecordButton(MedicalRecord medicalRecord) {
        Pane newButton;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(Main.class.getResource("resources/fxml/usermenuResources/medicalRecordButton.fxml")));
            newButton = fxmlLoader.load();
            medicalRecordButton fxmlCo = fxmlLoader.getController();
            fxmlCo.setMedicalRecord(medicalRecord);
            fxmlCo.setAdmissionDateLabel(medicalRecord.getAccess_date().toString());
            if(medicalRecord.getRelease_date() != null)
                fxmlCo.setDischargeDateLabel(medicalRecord.getRelease_date().toString());
            else
                fxmlCo.setDischargeDateLabel("N/A");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        medicalRecordBox.getChildren().add(newButton);
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
        Pane newButton;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(Main.class.getResource("resources/fxml/usermenuResources/examinationButton.fxml")));
            newButton = fxmlLoader.load();
            examinationButton fxmlCo = fxmlLoader.getController();
            fxmlCo.setAssociatedExamination(examinationToDisplay);
            fxmlCo.setExaminationDate(examinationToDisplay.getDate().toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        examinationBox.getChildren().add(newButton);
    }

    public void clearExaminationButtons() {
        examinationBox.getChildren().clear();
    }

    public void setExaminationDateLabel(String examinationDate) {
        examinationDateLabel.setText("Examination date: " + examinationDate);
    }
    public void setVetNotesLabel(String vetNotes) {
        vetNotesLabel.setText(vetNotes);
    }

    public void setExaminationCirclesColor(Circle bodyPart, Color color) {
        bodyPart.setFill(color);
    }

    public void setExaminationCirclesPopOver(Circle bodyPart, String bodyPartName) {
        PopOver explanationPopOver = new PopOver(new Label(" State of the " + bodyPartName));
        explanationPopOver.setArrowLocation(PopOver.ArrowLocation.RIGHT_TOP);
        bodyPart.setOnMouseEntered(event -> explanationPopOver.show(bodyPart));
        bodyPart.setOnMouseExited(event -> explanationPopOver.hide());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Create a rounded rectangle at runtime for the selectedExaminationPanel
        Rectangle clip = new Rectangle(selectedExaminationPanel.getPrefWidth(), selectedExaminationPanel.getPrefHeight());
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        selectedExaminationPanel.setClip(clip);

        //Create a rounded rectangle at runtime for the selectedMedicalRecordPanel
        Rectangle clip2 = new Rectangle(selectedMedicalRecordPanel.getPrefWidth(), selectedMedicalRecordPanel.getPrefHeight());
        clip2.setArcWidth(20);
        clip2.setArcHeight(20);
        selectedMedicalRecordPanel.setClip(clip2);

        examinationBox.setSpacing(4);
        medicalRecordBox.setSpacing(4);

        //PopOver for the Examination circles: explain what the part is
        setExaminationCirclesPopOver(beakCircle, "beak");
        setExaminationCirclesPopOver(noseCircle, "nose");
        setExaminationCirclesPopOver(eyesCircle, "eyes");
        setExaminationCirclesPopOver(headCircle, "head");
        setExaminationCirclesPopOver(tailCircle, "tail");
        setExaminationCirclesPopOver(neckCircle, "neck");
        setExaminationCirclesPopOver(finsCircle, "fins");
    }
}