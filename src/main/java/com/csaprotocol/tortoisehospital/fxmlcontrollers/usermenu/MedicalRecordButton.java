package me.csaprotocol.tortoisehospital.fxmlcontrollers.usermenu;

import com.google.common.eventbus.Subscribe;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.Setter;
import me.csaprotocol.tortoisehospital.controllers.ControllerOrchestrator;
import me.csaprotocol.tortoisehospital.controllers.EventController;
import me.csaprotocol.tortoisehospital.entities.MedicalRecord;
import me.csaprotocol.tortoisehospital.events.MedicalRecordClickEvent;
import me.csaprotocol.tortoisehospital.events.eventbuses.EventBus;

import java.net.URL;
import java.util.ResourceBundle;

public class MedicalRecordButton implements Initializable {

    @FXML private Label admissionDateLabel;
    @FXML private Label dischargeDateLabel;
    @FXML private Pane medicalRecordPane;
    private boolean isSelected = false;
    private @Getter @Setter MedicalRecord medicalRecord;

    public void setAdmissionDateLabel(String date) {
        admissionDateLabel.setText(date);
    }
    public void setDischargeDateLabel(String date) {
        dischargeDateLabel.setText(date);
    }

    @FXML void onClick(MouseEvent event) {
        EventController co = new EventController();
        co.fireMedicalRecordEvent();
        medicalRecordPane.setStyle("-fx-background-color: #165DCE; -fx-background-radius: 1em; -fx-border-radius: 1em");
        isSelected = true;

        ControllerOrchestrator coo = new ControllerOrchestrator();
        coo.setSelectedMedicalRecord(medicalRecord);
    }

    @FXML void onMouseEnter(MouseEvent event) {
        if(!isSelected) {
            medicalRecordPane.setStyle("-fx-background-color: #6393E7; -fx-background-radius: 1em; -fx-border-radius: 1em");
        }
    }

    @FXML void onMouseExit(MouseEvent event) {
        if(!isSelected) {
            medicalRecordPane.setStyle("-fx-background-color: #282828; -fx-background-radius: 1em; -fx-border-radius: 1em");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        EventBus.getInstance().register(this);
    }
    @Subscribe
    public void handleMedicalRecordEvent(MedicalRecordClickEvent event) {
        medicalRecordPane.setStyle("-fx-background-color: #282828; -fx-background-radius: 1em; -fx-border-radius: 1em");
        isSelected = false;
    }
}
