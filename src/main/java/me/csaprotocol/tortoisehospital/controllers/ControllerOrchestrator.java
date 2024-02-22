package me.csaprotocol.tortoisehospital.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import me.csaprotocol.tortoisehospital.Main;
import me.csaprotocol.tortoisehospital.entities.*;
import me.csaprotocol.tortoisehospital.entities.enums.Sex;
import me.csaprotocol.tortoisehospital.fxmlcontrollers.modularMenu.fourthColumnTurtleMenu;
import me.csaprotocol.tortoisehospital.fxmlcontrollers.modularMenu.thirdColumnTurtleMenu;
import me.csaprotocol.tortoisehospital.fxmlcontrollers.userMenu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class ControllerOrchestrator {

    private final DataController data = DataController.getInstance();

    //GUI Interfaces
    public void showTurtleManagementGUI(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("resources/fxml/menuUtils/turtleManagementGUI.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            data.setCurrentScene(fxmlLoader);
            stage.setTitle("TortoiseHospital");
            stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("resources/images/appLogo.png"))));
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void showTurtlesGUIbySearch(String TurtleID) {
        userMenu currentSceneController = data.getCurrentScene().getController();
        currentSceneController.clearTurtles();
        DaoController dco = new DaoController();
        ArrayList<Turtle> turtles = dco.searchTurtles(TurtleID);

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

    private void showSubSceneTurtlePanel() {
        FXMLLoader thirdColumnFXMLLoader = new FXMLLoader(Main.class.getResource("resources/fxml/modularMenu/turtleMenu/thirdColumnTurtleMenu.fxml"));
        FXMLLoader fourthColumnFXMLLoader = new FXMLLoader(Main.class.getResource("resources/fxml/modularMenu/turtleMenu/fourthColumnTurtleMenu.fxml"));
        data.setCurrentSubSceneThirdColumn(thirdColumnFXMLLoader);
        data.setCurrentSubSceneFourthColumn(fourthColumnFXMLLoader);
        data.setCurrentSubSceneName("turtlePanel");
        showSubScene(thirdColumnFXMLLoader, fourthColumnFXMLLoader);
    }
    private void showSubScene(FXMLLoader thirdColumnFxmlLoader, FXMLLoader fourthColumnFxmlLoader) {
        userMenu currentSceneFXMLController = data.getCurrentScene().getController();
        currentSceneFXMLController.showSpinner(false);
        currentSceneFXMLController.showThirdColumn(thirdColumnFxmlLoader);
        currentSceneFXMLController.showFourthColumn(fourthColumnFxmlLoader);
    }

    public void handleTurtleClick(String TurtleID) {
        switch(data.getCurrentSubSceneName()) {
            case "turtlePanel":
                setSelectedTurtle(TurtleID);
                showMeasurementGUI();
                showMedicalRecordGUI();
                break;
            case "statsPanel":
                break;
            case null:
                showSubSceneTurtlePanel();
                handleTurtleClick(TurtleID);
                break;
            default:
                break;
        }
    }

    public void showMeasurementGUI() {
        thirdColumnTurtleMenu currentSubSceneController = data.getCurrentSubSceneThirdColumn().getController();
        DaoController dco = new DaoController();
        ArrayList<Measurement> measurementArrayList = dco.getMeasurementsByTurtleId(data.getSelectedTurtle().getID());
        currentSubSceneController.clearMeasurementButtons();

        for (Measurement measurement : measurementArrayList) {
            currentSubSceneController.addMeasurementButton(measurement);
        }
    }

    public void showMedicalRecordGUI() {
        fourthColumnTurtleMenu currentSubSceneController = data.getCurrentSubSceneFourthColumn().getController();
        DaoController dco = new DaoController();
        ArrayList<MedicalRecord> mrList = dco.getMedicalRecordsByTurtleID(data.getSelectedTurtle().getID());
        currentSubSceneController.clearMedicalRecordButtons();

        for (MedicalRecord medrecord : mrList) {
            currentSubSceneController.addMedicalRecordButton(medrecord);
        }
    }

    public void showExaminationsGUI(MedicalRecord mr) {
        fourthColumnTurtleMenu currentSubSceneController = data.getCurrentSubSceneFourthColumn().getController();
        DaoController dco = new DaoController();
        ArrayList<Examination> examinationArrayList = dco.getExaminationsByMedicalRecordID(mr.getInternalID());
        currentSubSceneController.clearExaminationButtons();

        for (Examination examination : examinationArrayList) {
            currentSubSceneController.addExaminationButton(examination);
        }
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

    public void setSelectedTurtle(String turtleID) {
        DaoController dco = new DaoController();
        Object[] TurtleAndTank = dco.getTurtleByID(turtleID);
        data.setSelectedTurtle((Turtle) TurtleAndTank[0]);
        thirdColumnTurtleMenu subSceneController = data.getCurrentSubSceneThirdColumn().getController();
        subSceneController.setTurtleIDLabel(data.getSelectedTurtle().getID());
        subSceneController.setSpeciesLabel(data.getSelectedTurtle().getSpecies());
        subSceneController.setTurtleNameLabel(data.getSelectedTurtle().getName());
        if(data.getSelectedTurtle().getSex().equals(Sex.Female))
            subSceneController.setSexImgToFemale();
        else
            subSceneController.setSexImgToMale();
        subSceneController.setCenterIDLabel((String) TurtleAndTank[2]);
        subSceneController.setTankIDLabel(String.valueOf((int) TurtleAndTank[1]));
    }

    public void setSelectedMeasurement(Measurement measurementToFocus) {
        thirdColumnTurtleMenu subSceneController = data.getCurrentSubSceneThirdColumn().getController();
        subSceneController.setLengthLabel(String.valueOf(measurementToFocus.getLength()));
        subSceneController.setMeasurementDateLabel(measurementToFocus.getDate().toString());
        subSceneController.setWeightLabel(String.valueOf(measurementToFocus.getWeight()));
        subSceneController.setWidthLabel(String.valueOf(measurementToFocus.getWidth()));
    }

    public void setSelectedMedicalRecord(MedicalRecord mr) {
        fourthColumnTurtleMenu subSceneController = data.getCurrentSubSceneFourthColumn().getController();
        subSceneController.setLocationLabel(mr.getLatitude() + ", " + mr.getLongitude());
        subSceneController.setDischargeDateLabel(mr.getRelease_date());
        subSceneController.setAdmissionDateLabel(mr.getAccess_date().toString());
        subSceneController.setMedicalRecordInternalIDLabel(mr.getInternalID());

        showExaminationsGUI(mr);
    }

    public void setSelectedExamination(Examination ex) {
        fourthColumnTurtleMenu subSceneController = data.getCurrentSubSceneFourthColumn().getController();
        subSceneController.setExaminationDateLabel(ex.getDate().toString());
        subSceneController.setVetNotesLabel(ex.getVet_notes());
        subSceneController.setExaminationCirclesColor(subSceneController.getHeadCircle(), ex.getHead_status().getColor());
        subSceneController.setExaminationCirclesColor(subSceneController.getEyesCircle(), ex.getEyes_status().getColor());
        subSceneController.setExaminationCirclesColor(subSceneController.getTailCircle(), ex.getTail_status().getColor());
        subSceneController.setExaminationCirclesColor(subSceneController.getFinsCircle(), ex.getFins_status().getColor());
        subSceneController.setExaminationCirclesColor(subSceneController.getNeckCircle(), ex.getNeck_status().getColor());
        subSceneController.setExaminationCirclesColor(subSceneController.getBeakCircle(), ex.getBeak_status().getColor());
        subSceneController.setExaminationCirclesColor(subSceneController.getNoseCircle(), ex.getNose_status().getColor());
    }

    //Interface with DataController - Data Manipulation
    public ArrayList<String> getCenterIds() {
        ArrayList<String> centerIds = new ArrayList<>();
        for (Center center : data.getCenterArray()) {
            centerIds.add(center.getID());
        }
        return centerIds;
    }

    public ArrayList<String> getTankIds() {
        ArrayList<String> tankIds = new ArrayList<>();
        for (Tank tank : data.getTankArray()) {
            tankIds.add(String.valueOf(tank.getTankID()));
        }
        return tankIds;
    }


}