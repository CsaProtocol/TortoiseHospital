package me.csaprotocol.tortoisehospital;

import javafx.application.Application;
import javafx.stage.Stage;
import me.csaprotocol.tortoisehospital.controllers.ControllerOrchestrator;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        ControllerOrchestrator co = new ControllerOrchestrator();
        co.showQualcosina(stage);
       // co.showLoginGUI(stage);
    }

    public static void main(String[] args) {;
        launch(args);
    }
}