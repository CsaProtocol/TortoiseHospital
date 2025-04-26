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
import me.csaprotocol.tortoisehospital.entities.Examination;
import me.csaprotocol.tortoisehospital.events.ExaminationClickEvent;
import me.csaprotocol.tortoisehospital.events.eventbuses.EventBus;

import java.net.URL;
import java.util.ResourceBundle;

public class ExaminationButton implements Initializable {

    @FXML private Label examinationDate;
    @FXML private Pane examinationPane;
    private boolean isSelected = false;
    private @Getter @Setter Examination associatedExamination;

    public void setExaminationDate(String date) {
        examinationDate.setText(date);
    }
    @FXML void onClick(MouseEvent event) {
        EventController ec = new EventController();
        ec.fireExaminationEvent();
        examinationPane.setStyle("-fx-background-color: #165DCE; -fx-background-radius: 1em; -fx-border-radius: 1em");
        isSelected = true;

        ControllerOrchestrator coo = new ControllerOrchestrator();
        coo.setSelectedExamination(associatedExamination);
    }

    @FXML void onMouseEnter(MouseEvent event) {
        if(!isSelected) {
            examinationPane.setStyle("-fx-background-color: #6393E7; -fx-background-radius: 1em; -fx-border-radius: 1em");
        }
    }

    @FXML void onMouseExit(MouseEvent event) {
        if(!isSelected) {
            examinationPane.setStyle("-fx-background-color: #282828; -fx-background-radius: 1em; -fx-border-radius: 1em");
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        EventBus.getInstance().register(this);
    }

    @Subscribe
    public void handleMeasurementEvent(ExaminationClickEvent event) {
        examinationPane.setStyle("-fx-background-color: #282828; -fx-background-radius: 1em; -fx-border-radius: 1em");
        isSelected = false;
    }
}