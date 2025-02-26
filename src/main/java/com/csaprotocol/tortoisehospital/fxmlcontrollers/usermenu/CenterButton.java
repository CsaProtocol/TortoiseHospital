package me.csaprotocol.tortoisehospital.fxmlcontrollers.usermenu;

import com.google.common.eventbus.Subscribe;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import me.csaprotocol.tortoisehospital.controllers.ControllerOrchestrator;
import me.csaprotocol.tortoisehospital.controllers.EventController;
import me.csaprotocol.tortoisehospital.events.CenterClickEvent;
import me.csaprotocol.tortoisehospital.events.eventbuses.EventBus;

import java.net.URL;
import java.util.ResourceBundle;

public class CenterButton implements Initializable {

    @FXML private Button buttonId;
    private boolean isSelected = false;

    @FXML void onButtonClick(MouseEvent event) {
        ControllerOrchestrator co = new ControllerOrchestrator();
        co.showTanksGUI(buttonId.getText());
        co.showTurtlesGUIbyCenter(buttonId.getText());
        co.setSelectedCenter(buttonId.getText());
        EventController ec = new EventController();
        ec.fireCenterEvent();
        buttonId.setStyle("-fx-background-color: #165DCE; -fx-background-radius: 1em");
        isSelected = true;
    }

    @FXML
    void onMouseEnter(MouseEvent event) {
        if(!isSelected)
            buttonId.setStyle("-fx-background-color: #6393E7; -fx-background-radius: 1em");
    }

    @FXML
    void onMouseExit(MouseEvent event) {
        if (!isSelected)
            buttonId.setStyle("-fx-background-color: #282828");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonId.setStyle("-fx-background-color: #282828");
        EventBus.getInstance().register(this);
    }

    @Subscribe
    public void handleCenterClickEvent(CenterClickEvent event) {
        buttonId.setStyle("-fx-background-color: #282828");
        isSelected = false;
    }
}