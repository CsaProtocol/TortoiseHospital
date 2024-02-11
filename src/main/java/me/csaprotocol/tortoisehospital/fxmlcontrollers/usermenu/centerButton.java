package me.csaprotocol.tortoisehospital.fxmlcontrollers.usermenu;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import me.csaprotocol.tortoisehospital.controllers.ControllerOrchestrator;

public class centerButton {

    @FXML private Button buttonId;

    @FXML void onButtonClick(MouseEvent event) {
        ControllerOrchestrator co = new ControllerOrchestrator();
        co.showTanksGUI(buttonId.getText());
        co.showTurtlesGUIbyCenter(buttonId.getText());
        co.setSelectedCenter(buttonId.getText());
    }

}