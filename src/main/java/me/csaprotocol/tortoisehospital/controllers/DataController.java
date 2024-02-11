package me.csaprotocol.tortoisehospital.controllers;

import javafx.fxml.FXMLLoader;
import lombok.Getter;
import lombok.Setter;
import me.csaprotocol.tortoisehospital.entities.Center;
import me.csaprotocol.tortoisehospital.entities.Employee;
import me.csaprotocol.tortoisehospital.entities.Tank;
import me.csaprotocol.tortoisehospital.entities.Turtle;

import java.util.ArrayList;

public class DataController {

    private static DataController instance;
    private @Getter @Setter FXMLLoader currentScene;
    private @Getter @Setter Employee loggedUser;

    private @Getter @Setter ArrayList<Center> centerArray;
    private @Getter @Setter ArrayList<Tank> tankArray;
    private @Getter @Setter ArrayList<Turtle> turtlesArray;

    private @Getter @Setter Tank selectedTank;
    private @Getter @Setter Center selectedCenter;
    private @Getter @Setter Turtle selectedTurtle;

    private DataController() {}

    public static DataController getInstance() {
        if (instance == null) {
            instance = new DataController();
            instance.loggedUser = new Employee();
        }
        return instance;
    }
}
