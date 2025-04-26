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
import me.csaprotocol.tortoisehospital.entities.Measurement;
import me.csaprotocol.tortoisehospital.events.MeasurementClickEvent;
import me.csaprotocol.tortoisehospital.events.eventbuses.EventBus;

import java.net.URL;
import java.util.ResourceBundle;

public class MeasurementButton implements Initializable {

    @FXML private Pane measurementPane;
    @FXML private Label dateLabel;
    private boolean isSelected = false;
    private @Getter @Setter Measurement measurementAssociated;

    public void setDateLabel(String date) {
        dateLabel.setText(date);
    }
    @FXML void onClick(MouseEvent event) {
        EventController ec = new EventController();
        ec.fireMeasurementEvent();
        measurementPane.setStyle("-fx-background-color: #165DCE; -fx-background-radius: 1em; -fx-border-radius: 1em");
        isSelected = true;

        ControllerOrchestrator coo = new ControllerOrchestrator();
        coo.setSelectedMeasurement(measurementAssociated);
    }
    @FXML public void onMouseEnter(MouseEvent event) {
        if(!isSelected) {
            measurementPane.setStyle("-fx-background-color: #6393E7; -fx-background-radius: 1em; -fx-border-radius: 1em");
        }
    }
    @FXML public void onMouseExit(MouseEvent event) {
        if (!isSelected) {
            measurementPane.setStyle("-fx-background-color: #282828; -fx-background-radius: 1em; -fx-border-radius: 1em");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        EventBus.getInstance().register(this);
    }

    @Subscribe
    public void handleMeasurementEvent(MeasurementClickEvent event) {
        measurementPane.setStyle("-fx-background-color: #282828; -fx-background-radius: 1em; -fx-border-radius: 1em");
        isSelected = false;
    }
}
