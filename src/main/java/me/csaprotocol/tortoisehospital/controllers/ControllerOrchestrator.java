package me.csaprotocol.tortoisehospital.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import me.csaprotocol.tortoisehospital.Main;
import me.csaprotocol.tortoisehospital.entities.Center;
import me.csaprotocol.tortoisehospital.entities.Tank;
import me.csaprotocol.tortoisehospital.entities.Turtle;
import me.csaprotocol.tortoisehospital.fxmlcontrollers.userMenu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class ControllerOrchestrator {

    private final DataController data = DataController.getInstance();

    //GUI Interfaces
    public void showQualcosina(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("resources/fxml/usermenuResources/turtlepanel/turtlePanel.fxml"));
        showNewGUI(stage, fxmlLoader);
    }

    public void showLoginGUI(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("resources/fxml/login.fxml"));

        showNewGUI(stage, fxmlLoader);
    }

    public void showUserMenuGUI() {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("resources/fxml/userMenu.fxml"));
        Stage stage = new Stage();
        showNewGUI(stage, fxmlLoader);

        userMenu currentSceneController = data.getCurrentScene().getController();
        DaoController dco = new DaoController();
        ArrayList<Center> centers = dco.getCentersByEmployeeID();

        data.setCenterArray(centers);

        for (Center center : centers) {
            currentSceneController.addCenterButton(center.getID());
        }
    }

    public void showTanksGUI(String centerID) {
        userMenu currentSceneController = data.getCurrentScene().getController();
        currentSceneController.clearTanks();
        DaoController dco = new DaoController();
        ArrayList<Tank> tanks = dco.getTanksByCenterID(centerID);

        data.setTankArray(tanks);

        for (Tank tank : tanks) {
            currentSceneController.addTankButton(tank.getTankID());
        }
    }

    public void showTurtlesGUIbyTank(int tankCode) {
        userMenu currentSceneController = data.getCurrentScene().getController();
        currentSceneController.clearTurtles();
        DaoController dco = new DaoController();
        ArrayList<Turtle> turtles = dco.getTurtlesByTankID(tankCode, data.getSelectedCenter().getID());

        data.setTurtlesArray(turtles);

        for (Turtle turtle : turtles) {
            currentSceneController.addTurtleButton(turtle.getID(), turtle.getName());
        }
    }

    public void showTurtlesGUIbyCenter(String centerID) {
        userMenu currentSceneController = data.getCurrentScene().getController();
        currentSceneController.clearTurtles();
        DaoController dco = new DaoController();
        ArrayList<Turtle> turtles = dco.getTurtlesByCenterID(centerID);

        data.setTurtlesArray(turtles);

        for (Turtle turtle : turtles) {
            currentSceneController.addTurtleButton(turtle.getID(), turtle.getName());
        }
    }

    private void showNewGUI(Stage stage, FXMLLoader fxmlLoader) {
        try {
            Scene scene = new Scene(fxmlLoader.load());
            data.setCurrentScene(fxmlLoader);
            stage.setTitle("TortoiseHospital");
            stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("resources/images/appLogo.png"))));
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            data.setCurrentScene(fxmlLoader);
        }
    }

    public void closeStage(ActionEvent event) {
        Stage stageToClose = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageToClose.close();
    }

    //Interface with DataController
    public void setSelectedCenter(String centerID) {
        DaoController dco = new DaoController();
        data.setSelectedCenter(dco.getCenterByID(centerID));
        userMenu sceneController = data.getCurrentScene().getController();
        sceneController.setSelectedImgToCenter();
        sceneController.setFirstLabel(centerID);
        sceneController.setSecondLabel(data.getSelectedCenter().getName());
        sceneController.setThirdLabel(data.getSelectedCenter().getEmail());
        sceneController.setFourthLabel(data.getSelectedCenter().getPhone(), true);
        String address = data.getSelectedCenter().getAddress() + ", " + data.getSelectedCenter().getCity() + ", (" + data.getSelectedCenter().getProvince() + ")";
        sceneController.setFifthLabel(address, true);
    }

    public void setSelectedTank(int tankID) {
        DaoController dco = new DaoController();
        data.setSelectedTank(dco.getTankByID(tankID, data.getSelectedCenter().getID()));
        userMenu sceneController = data.getCurrentScene().getController();
        sceneController.setSelectedImgToTank();
        sceneController.setFirstLabel(String.valueOf(tankID));
        sceneController.setSecondLabel(data.getSelectedTank().getCenterID());
        String capacity = "Capacity: " + data.getSelectedTank().getCapacity() + " turtles";
        sceneController.setThirdLabel(capacity);
        sceneController.setFourthLabel("", false);
        sceneController.setFifthLabel("", false);
    }
}