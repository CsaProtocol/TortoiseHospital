package me.csaprotocol.tortoisehospital;

import javafx.application.Application;
import javafx.stage.Stage;
import me.csaprotocol.tortoisehospital.controllers.ControllerOrchestrator;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        ControllerOrchestrator co = new ControllerOrchestrator();
        co.showLoginGUI(stage);
    }

    public static void main(String[] args) {;
        launch(args);
    }
}