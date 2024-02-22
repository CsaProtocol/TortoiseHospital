package me.csaprotocol.tortoisehospital;

import io.github.palexdev.materialfx.theming.JavaFXThemes;
import io.github.palexdev.materialfx.theming.MaterialFXStylesheets;
import io.github.palexdev.materialfx.theming.UserAgentBuilder;
import javafx.application.Application;
import javafx.stage.Stage;
import me.csaprotocol.tortoisehospital.controllers.ControllerOrchestrator;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        ControllerOrchestrator co = new ControllerOrchestrator();

        UserAgentBuilder.builder()
                .themes(JavaFXThemes.MODENA)
                .themes(MaterialFXStylesheets.forAssemble(true))
                .setDeploy(true)
                .setResolveAssets(true)
                .build()
                .setGlobal();

        co.showLoginGUI(stage);
    }

    public static void main(String[] args) {;
        launch(args);
    }
}