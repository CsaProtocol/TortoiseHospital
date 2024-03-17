package me.csaprotocol.tortoisehospital.controllers;

import eu.hansolo.fx.charts.data.XYChartItem;
import eu.hansolo.toolbox.observables.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import me.csaprotocol.tortoisehospital.Main;
import me.csaprotocol.tortoisehospital.entities.*;
import me.csaprotocol.tortoisehospital.entities.enums.Sex;
import me.csaprotocol.tortoisehospital.exceptions.CoreException;
import me.csaprotocol.tortoisehospital.exceptions.ExceptionHandler;
import me.csaprotocol.tortoisehospital.fxmlcontrollers.dialogUtil;
import me.csaprotocol.tortoisehospital.fxmlcontrollers.modularmenu.FourthColumnTurtleMenu;
import me.csaprotocol.tortoisehospital.fxmlcontrollers.modularmenu.ThirdColumnTurtleMenu;
import me.csaprotocol.tortoisehospital.fxmlcontrollers.userMenu;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.action.Action;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class ControllerOrchestrator {

    private final DataController data = DataController.getInstance();

    //Interfaces for new things to insert in DB
    public void showNewTurtleGUI(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("resources/fxml/menuUtils/newTurtle.fxml"));
        try {
            showOtherStage(fxmlLoader, stage);
        } catch (CoreException e) {
            ExceptionHandler eh = new ExceptionHandler();
            eh.handleException(e);
        }
    }

    public void showNewMedicalRecordGUI(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("resources/fxml/menuUtils/newMedicalRecord.fxml"));
        try {
            showOtherStage(fxmlLoader, stage);
        } catch (CoreException e) {
            ExceptionHandler eh = new ExceptionHandler();
            eh.handleException(e);
        }
    }

    public void showNewExaminationGUI(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("resources/fxml/menuUtils/newExamination.fxml"));
        try {
            showOtherStage(fxmlLoader, stage);
        } catch (CoreException e) {
            ExceptionHandler eh = new ExceptionHandler();
            eh.handleException(e);
        }
    }

    public void showNewMeasurementGUI(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("resources/fxml/menuUtils/newMeasurement.fxml"));
        try {
            showOtherStage(fxmlLoader, stage);
        } catch (CoreException e) {
            ExceptionHandler eh = new ExceptionHandler();
            eh.handleException(e);
        }
    }

    //Update Interfaces

    public void showUpdateTurtleGUI(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("resources/fxml/menuUtils/updateTurtle.fxml"));
        try {
            showOtherStage(fxmlLoader, stage);
        } catch (CoreException e) {
            ExceptionHandler eh = new ExceptionHandler();
            eh.handleException(e);
        }
    }

    public void showUpdateExaminationGUI(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("resources/fxml/menuUtils/updateExamination.fxml"));
        try {
            showOtherStage(fxmlLoader, stage);
        } catch (CoreException e) {
            ExceptionHandler eh = new ExceptionHandler();
            eh.handleException(e);
        }
    }

    public void showDialogReleaseTurtle(MouseEvent event, Pane content) {
        if(data.getSelectedMedicalRecord() == null)
            return;
        dialogUtil dialog = new dialogUtil();
        dialog.showDialogReleaseTurtle(event, content);
    }

    public void showDialogDeleteTurtle(MouseEvent event, Pane content) {
        if(data.getSelectedTurtle() == null)
            return;
        dialogUtil dialog = new dialogUtil();
        dialog.showDialogDeleteTurtle(event, content);
    }

    public void showDialogDeleteMeasurement(MouseEvent event, Pane content, String date) {
        dialogUtil dialog = new dialogUtil();
        dialog.showDialogDeleteMeasurement(event, content, LocalDate.parse(date));
    }

    public void showDialogDeleteMedicalRecord(MouseEvent event, Pane content) {
        if(data.getSelectedMedicalRecord() == null)
            return;
        dialogUtil dialog = new dialogUtil();
        dialog.showDialogDeleteMedicalRecord(event, content);
    }

    public void showDialogDeleteExamination(MouseEvent event, Pane content) {
        if(data.getSelectedExamination() == null)
            try {
                throw new CoreException("No examination selected, please select one");
            } catch (CoreException e) {
                ExceptionHandler eh = new ExceptionHandler();
                eh.handleException(e.getMessage());
                return;
            }
        dialogUtil dialog = new dialogUtil();
        dialog.showDialogDeleteExamination(event, content);
    }

    public void showOtherStage(FXMLLoader fxmlLoader, Stage stage) throws CoreException {
        try {
            Scene scene = new Scene(fxmlLoader.load());
            data.setProvisionalFocus(stage);
            stage.setTitle("TortoiseHospital");
            stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("resources/images/appLogo.png"))));
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new CoreException("Error loading new stage", e);
        }
    }

    //GUI Interfaces

    public void showLoginGUI(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("resources/fxml/login.fxml"));
        try {
            showNewGUI(stage, fxmlLoader);
        } catch (CoreException e) {
            ExceptionHandler eh = new ExceptionHandler();
            eh.handleException(e);
        }
    }

    public void showUserMenuGUI() {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("resources/fxml/userMenu.fxml"));
        Stage stage = new Stage();
        try {
            showNewGUI(stage, fxmlLoader);
        } catch (CoreException e) {
            ExceptionHandler eh = new ExceptionHandler();
            eh.handleException(e);
        }

        userMenu currentSceneController = data.getCurrentScene().getController();
        DaoController dco = new DaoController();
        ArrayList<Center> centers = dco.getCentersByEmployeeID();
        showTurtleGUI();
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

    public void showTurtleGUI() {
        userMenu currentSceneController = data.getCurrentScene().getController();
        currentSceneController.clearTurtles();
        DaoController dco = new DaoController();
        ArrayList<Turtle> turtles = dco.getAllTurtles();

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

    private void showNewGUI(Stage stage, FXMLLoader fxmlLoader) throws CoreException {
        try {
            Scene scene = new Scene(fxmlLoader.load());
            data.setCurrentScene(fxmlLoader);
            stage.setTitle("TortoiseHospital");
            stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("resources/images/appLogo.png"))));
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new CoreException("Error loading new GUI", e);
        } finally {
            data.setCurrentScene(fxmlLoader);
        }
    }

    public void closeStageByEvent(ActionEvent event) {
        Stage stageToClose = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageToClose.close();
    }

    public void closeStage(Stage stage) {
        stage.close();
        data.setProvisionalFocus(null);
    }

    public void showSubSceneStatsPanel() {
        FXMLLoader thirdColumnFXMLLoader = new FXMLLoader(Main.class.getResource("resources/fxml/modularMenu/statsMenu/thirdColumnStatsMenu.fxml"));
        FXMLLoader fourthColumnFXMLLoader = new FXMLLoader(Main.class.getResource("resources/fxml/modularMenu/statsMenu/fourthColumnStatsMenu.fxml"));
        data.setCurrentSubSceneThirdColumn(thirdColumnFXMLLoader);
        data.setCurrentSubSceneFourthColumn(fourthColumnFXMLLoader);
        data.setCurrentSubSceneName("statsPanel");
        showSubScene(thirdColumnFXMLLoader, fourthColumnFXMLLoader);
    }
    public void showSubSceneTurtlePanel() {
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

    public void showMeasurementGUI() {
        ThirdColumnTurtleMenu currentSubSceneController = data.getCurrentSubSceneThirdColumn().getController();
        DaoController dco = new DaoController();
        ArrayList<Measurement> measurementArrayList = dco.getMeasurementsByTurtleId(data.getSelectedTurtle().getID());
        currentSubSceneController.clearMeasurementButtons();

        for (Measurement measurement : measurementArrayList) {
            currentSubSceneController.addMeasurementButton(measurement);
        }
    }

    public void showMedicalRecordGUI() {
        FourthColumnTurtleMenu currentSubSceneController = data.getCurrentSubSceneFourthColumn().getController();
        DaoController dco = new DaoController();
        ArrayList<MedicalRecord> mrList = dco.getMedicalRecordsByTurtleID(data.getSelectedTurtle().getID());
        currentSubSceneController.clearMedicalRecordButtons();

        for (MedicalRecord medrecord : mrList) {
            currentSubSceneController.addMedicalRecordButton(medrecord);
        }
    }

    public void showExaminationsGUI(MedicalRecord mr) {
        FourthColumnTurtleMenu currentSubSceneController = data.getCurrentSubSceneFourthColumn().getController();
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
        Object[] TurtleAndTank = dco.getTurtleAndTankByID(turtleID);
        data.setSelectedTurtle((Turtle) TurtleAndTank[0]);
        ThirdColumnTurtleMenu subSceneController = data.getCurrentSubSceneThirdColumn().getController();
        subSceneController.setTurtleIDLabel(data.getSelectedTurtle().getID());
        subSceneController.setSpeciesLabel(data.getSelectedTurtle().getSpecies());
        subSceneController.setTurtleNameLabel(data.getSelectedTurtle().getName());
        if(data.getSelectedTurtle().getSex().equals(Sex.Female))
            subSceneController.setSexImgToFemale();
        else
            subSceneController.setSexImgToMale();

        String centerID = (String) TurtleAndTank[2];
        subSceneController.setCenterIDLabel(Objects.requireNonNullElse(centerID, "N/A"));

        if(TurtleAndTank[1] != null) {
            String tankID = String.valueOf((int) TurtleAndTank[1]);
            subSceneController.setTankIDLabel(tankID);
        } else {
            subSceneController.setTankIDLabel("N/A");
        }
    }

    public void setSelectedTurtleStats(String turtleID) {
        DaoController dco = new DaoController();
        data.setSelectedTurtle(dco.getTurtleByID(turtleID));
    }

    public void setSelectedMeasurement(Measurement measurementToFocus) {
        ThirdColumnTurtleMenu subSceneController = data.getCurrentSubSceneThirdColumn().getController();
        subSceneController.setLengthLabel(String.valueOf(measurementToFocus.getLength()));
        subSceneController.setMeasurementDateLabel(measurementToFocus.getDate().toString());
        subSceneController.setWeightLabel(String.valueOf(measurementToFocus.getWeight()));
        subSceneController.setWidthLabel(String.valueOf(measurementToFocus.getWidth()));
    }

    public void setSelectedMedicalRecord(MedicalRecord mr) {
        data.setSelectedMedicalRecord(mr);
        FourthColumnTurtleMenu subSceneController = data.getCurrentSubSceneFourthColumn().getController();
        subSceneController.setLocationLabel(mr.getLatitude() + ", " + mr.getLongitude());
        subSceneController.setDischargeDateLabel(mr.getRelease_date());
        subSceneController.setAdmissionDateLabel(mr.getAccess_date().toString());
        subSceneController.setMedicalRecordInternalIDLabel(mr.getInternalID());

        showExaminationsGUI(mr);
    }

    public void setSelectedExamination(Examination ex) {
        data.setSelectedExamination(ex);
        FourthColumnTurtleMenu subSceneController = data.getCurrentSubSceneFourthColumn().getController();
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

    //Click handling
    public void handleTurtleClick(String TurtleID) {
        switch(data.getCurrentSubSceneName()) {
            case "turtlePanel":
                setSelectedTurtle(TurtleID);
                showMeasurementGUI();
                showMedicalRecordGUI();
                break;
            case "statsPanel":
                setSelectedTurtleStats(TurtleID);
                break;
            case null:
                showSubSceneTurtlePanel();
                handleTurtleClick(TurtleID);
                break;
            default:
                break;
        }
    }

    public void handleNewTurtle(String turtleID) {

        closeStage(data.getProvisionalFocus());

        Action copyTurtleID = new Action("Copy Turtle ID", event -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(turtleID);
            clipboard.setContent(content);
        });

        Notifications.create()
            .title("Turtle created")
            .text("Turtle " + turtleID + " has been created")
            .action(copyTurtleID)
            .showInformation();

        EventController ec = new EventController();
        ec.fireCenterEvent();
        ec.fireTankEvent();
        showTurtleGUI();
    }

    public void handleNewMedicalRecord(String centerID, int tankID, String accessDate, String latitude, String longitude) {
        DaoController dco = new DaoController();
        String medRecordID = dco.createMedicalRecord(data.getSelectedTurtle().getID(), centerID, tankID, accessDate, latitude, longitude);

        closeStage(data.getProvisionalFocus());

        Action copyInternalID = new Action("Copy Internal ID", event -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(medRecordID);
            clipboard.setContent(content);
        });

        Notifications.create()
            .title("Medical record created")
            .text("Turtle admitted with internal ID " + medRecordID)
            .action(copyInternalID)
            .showInformation();

        showMedicalRecordGUI();
        setSelectedTurtle(data.getSelectedTurtle().getID());
    }

    public void handleNewExamination(String head, String eyes, String beak, String neck, String nose, String fins, String tail, String vetNotes) {
        DaoController dco = new DaoController();
        dco.createExamination(data.getSelectedMedicalRecord().getInternalID(), head, eyes, beak, neck, nose, fins, tail, vetNotes);

        closeStage(data.getProvisionalFocus());

        Notifications.create()
            .title("Examination created")
            .text("Examination created successfully")
            .showInformation();

        showExaminationsGUI(data.getSelectedMedicalRecord());
    }

    public void handleNewMeasurement(String date, String width, String length, String weight) {
        DaoController dco = new DaoController();
        Measurement toPass = new Measurement();
        toPass.setDate(LocalDate.parse(date));
        toPass.setWidth((float) Double.parseDouble(width));
        toPass.setLength((float) Double.parseDouble(length));
        toPass.setWeight((float) Double.parseDouble(weight));

        dco.createMeasurement(data.getSelectedTurtle().getID(), toPass);

        closeStage(data.getProvisionalFocus());

        Notifications.create()
            .title("Measurement created")
            .text("Measurement created successfully")
            .showInformation();

        showMeasurementGUI();
    }

    public void handleTurtleUpdate(String turtleName, String species, Sex sex) {
        DaoController dco = new DaoController();
        dco.updateTurtle(data.getSelectedTurtle().getID(), turtleName, species, sex);
        setSelectedTurtle(data.getSelectedTurtle().getID());

        closeStage(data.getProvisionalFocus());

        Notifications.create()
            .title("Turtle " + data.getSelectedTurtle().getID() + " updated")
            .text("Turtle updated successfully")
            .showInformation();

        showTurtleGUI();
    }

    public void handleExaminationUpdate(String head, String eyes, String beak, String neck, String nose, String fins, String tail, String vetNotes) {
        DaoController dco = new DaoController();
        dco.updateExamination(data.getSelectedMedicalRecord().getInternalID(), head, eyes, beak, neck, nose, fins, tail, vetNotes, data.getSelectedExamination());

        closeStage(data.getProvisionalFocus());

        Notifications.create()
            .title("Examination updated")
            .text("Examination updated successfully")
            .showInformation();

        showExaminationsGUI(data.getSelectedMedicalRecord());
    }

    public void handleTurtleRelease() {
        DaoController dco = new DaoController();
        dco.releaseTurtle(data.getSelectedMedicalRecord().getInternalID());

        Notifications.create()
            .title("Turtle " + data.getSelectedTurtle().getID() + " released")
            .text("Turtle released successfully from center")
            .showInformation();

        showTurtleGUI();
        showMedicalRecordGUI();
    }

    public void handleTurtleDeletion() {
        DaoController dco = new DaoController();
        dco.deleteTurtle(data.getSelectedTurtle().getID());

        Notifications.create()
            .title("Turtle " + data.getSelectedTurtle().getID() + " deleted")
            .text("Turtle deleted successfully")
            .showInformation();

        data.setSelectedTurtle(null);
        showTurtleGUI();
    }

    public void handleMeasurementDeletion(LocalDate date) {
        DaoController dco = new DaoController();
        dco.deleteMeasurement(data.getSelectedTurtle().getID(), date);

        Notifications.create()
            .title("Measurement deleted")
            .text("Measurement deleted successfully")
            .showInformation();

        showMeasurementGUI();
    }

    public void handleMedicalRecordDeletion() {
        DaoController dco = new DaoController();
        dco.deleteMedicalRecord(data.getSelectedMedicalRecord().getInternalID());

        Notifications.create()
            .title("Medical record deleted")
            .text("Medical record deleted successfully")
            .showInformation();

        data.setSelectedMedicalRecord(null);
        showMedicalRecordGUI();
    }

    public void handleExaminationDeletion() {
        DaoController dco = new DaoController();
        dco.deleteExamination(data.getSelectedMedicalRecord().getInternalID(), data.getSelectedExamination().getDate());

        Notifications.create()
            .title("Examination deleted")
            .text("Examination deleted successfully")
            .showInformation();

        data.setSelectedExamination(null);
        showExaminationsGUI(data.getSelectedMedicalRecord());
    }

    //Statistics
    public Integer[] handleCenterStatistics(LocalDate from, LocalDate to) throws CoreException {
        if(Objects.isNull(data.getSelectedCenter())) {
            throw new CoreException("No center selected, please select one from the column on the left");
        }
        DaoController dc = new DaoController();
        return dc.createCenterStatistics(from, to, data.getSelectedCenter().getID());
    }

    public ObservableList<XYChartItem> handleTurtleStats(LocalDate startDate, LocalDate endDate) throws CoreException {
        if(Objects.isNull(data.getSelectedTurtle())) {
            throw new CoreException("No turtle selected, please select one from the column on the left");
        }
        DaoController dc = new DaoController();
        return dc.createTurtleStats(data.getSelectedTurtle().getID(), startDate, endDate);
    }
}