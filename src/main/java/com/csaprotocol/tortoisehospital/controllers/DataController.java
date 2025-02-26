package me.csaprotocol.tortoisehospital.controllers;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import me.csaprotocol.tortoisehospital.entities.*;

import java.util.ArrayList;

public class DataController {

    private static DataController instance;

    private @Getter @Setter Stage provisionalFocus;
    private @Getter @Setter FXMLLoader currentScene;
    private @Getter @Setter String currentSubSceneName;
    private @Getter @Setter FXMLLoader currentSubSceneThirdColumn;
    private @Getter @Setter FXMLLoader currentSubSceneFourthColumn;
    private @Getter @Setter Employee loggedUser;

    private @Getter @Setter ArrayList<Center> centerArray;
    private @Getter @Setter ArrayList<Tank> tankArray;
    private @Getter @Setter ArrayList<Turtle> turtlesArray;

    private @Getter @Setter Tank selectedTank;
    private @Getter @Setter Center selectedCenter;
    private @Getter @Setter Turtle selectedTurtle;
    private @Getter @Setter MedicalRecord selectedMedicalRecord;
    private @Getter @Setter Examination selectedExamination;
    private @Getter @Setter Measurement selectedMeasurement;

    private DataController() {}

    public static DataController getInstance() {
        if (instance == null) {
            instance = new DataController();
            instance.loggedUser = new Employee();
        }
        return instance;
    }
}
