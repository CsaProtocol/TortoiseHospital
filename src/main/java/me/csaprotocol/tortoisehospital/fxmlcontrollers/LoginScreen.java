package me.csaprotocol.tortoisehospital.fxmlcontrollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import me.csaprotocol.tortoisehospital.controllers.ControllerOrchestrator;
import me.csaprotocol.tortoisehospital.controllers.LoginController;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginScreen implements Initializable {

    @FXML private TextField employeeIDField;
    @FXML private PasswordField employeePasswordField;
    @FXML private Button loginButton;
    @FXML private Label unsuccessfulLoginLabel;

    @FXML
    void loginActionHandler(ActionEvent event) {

        LoginController loginControllerReference = new LoginController();

        if(loginControllerReference.handleLogin(employeeIDField.getText(), employeePasswordField.getText())) {
            ControllerOrchestrator co = new ControllerOrchestrator();
            co.showUserMenuGUI();
            co.closeStageByEvent(event);
        } else {
            unsuccessfulLoginLabel.setVisible(true);
            employeeIDField.clear();
            employeePasswordField.clear();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        unsuccessfulLoginLabel.setVisible(false);
    }
}
