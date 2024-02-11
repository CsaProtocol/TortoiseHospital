package me.csaprotocol.tortoisehospital.fxmlcontrollers.usermenu;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import me.csaprotocol.tortoisehospital.controllers.ControllerOrchestrator;

public class tankButton {

    @FXML private Button buttonId;

    @FXML void onButtonClick(MouseEvent event) {
        ControllerOrchestrator co = new ControllerOrchestrator();
        co.showTurtlesGUIbyTank(Integer.parseInt(buttonId.getText()));
        co.setSelectedTank(Integer.parseInt(buttonId.getText()));
    }


}
