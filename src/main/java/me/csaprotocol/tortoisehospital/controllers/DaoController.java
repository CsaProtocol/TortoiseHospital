package me.csaprotocol.tortoisehospital.controllers;

import eu.hansolo.fx.charts.data.XYChartItem;
import eu.hansolo.toolbox.observables.ObservableList;
import me.csaprotocol.tortoisehospital.daos.factory.DAOFactory;
import me.csaprotocol.tortoisehospital.entities.*;
import me.csaprotocol.tortoisehospital.entities.enums.Sex;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class DaoController {

    private final DataController data = DataController.getInstance();
    //DAO Interfaces
    public ArrayList<Center> getCentersByEmployeeID() {
        return Objects.requireNonNull(DAOFactory.getCenterDAO()).getCenterByEmployeeID(data.getLoggedUser().getID());
    }

    public Center getCenterByID(String centerID) {
        return Objects.requireNonNull(DAOFactory.getCenterDAO()).getCenterByID(centerID);
    }

    public ArrayList<Tank> getTanksByCenterID(String centerID) {
        return Objects.requireNonNull(DAOFactory.getTankDAO()).getTankByCenterID(centerID);
    }

    public Tank getTankByID(int tankID, String centerID) {
        return Objects.requireNonNull(DAOFactory.getTankDAO()).getTankByID(tankID, centerID);
    }

    public ArrayList<Turtle> getTurtlesByTankID(int tankID, String centerID) {
        return Objects.requireNonNull(DAOFactory.getTurtleDAO()).getTurtlesByTankID(tankID, centerID);
    }

    public ArrayList<Turtle> getTurtlesByCenterID(String centerID) {
        return Objects.requireNonNull(DAOFactory.getTurtleDAO()).getTurtlesByCenterID(centerID);
    }

    public ArrayList<Turtle> searchTurtles(String toSearch) {
        return Objects.requireNonNull(DAOFactory.getTurtleDAO()).searchTurtle(toSearch);
    }

    public Object[] getTurtleAndTankByID(String TurtleID) {
        return Objects.requireNonNull(DAOFactory.getTurtleDAO()).getTurtleAndTankByTurtleID(TurtleID);
    }

    public ArrayList<Measurement> getMeasurementsByTurtleId(String TurtleID) {
        return Objects.requireNonNull(DAOFactory.getMeasurementDAO()).getMeasurementsByTurtleId(TurtleID);
    }

    public ArrayList<MedicalRecord> getMedicalRecordsByTurtleID(String TurtleID) {
        return Objects.requireNonNull(DAOFactory.getMedicalRecordDAO()).getMedicalRecordsByTurtleID(TurtleID);
    }

    public ArrayList<Examination> getExaminationsByMedicalRecordID(String medicalRecordID) {
        return Objects.requireNonNull(DAOFactory.getExaminationDAO()).getExaminationsByMedicalRecordID(medicalRecordID);
    }

    public String createTurtle(String name, String species, String sex) {
        return Objects.requireNonNull(DAOFactory.getTurtleDAO()).createTurtle(name, species, sex);
    }

    public ArrayList<Turtle> getAllTurtles() {
        return Objects.requireNonNull(DAOFactory.getTurtleDAO()).getAllTurtles();
    }

    public String createMedicalRecord(String turtleID, String centerID, int tankID, String accessDate, String latitude, String longitude) {
        String medRecordID = Objects.requireNonNull(DAOFactory.getMedicalRecordDAO()).createMedicalRecord(turtleID, centerID, accessDate, Double.parseDouble(latitude), Double.parseDouble(longitude));
        Objects.requireNonNull(DAOFactory.getTurtleDAO()).updateTurtleTank(turtleID, centerID, tankID);
        return medRecordID;
    }

    public void createExamination(String medicalRecordID, String head_status, String eyes_status, String beak_status, String neck_status, String nose_status, String fins_status, String tail_status, String vetNotes) {
        Objects.requireNonNull(DAOFactory.getExaminationDAO()).createExamination(medicalRecordID, head_status, eyes_status, beak_status, neck_status, nose_status, fins_status, tail_status, vetNotes);
    }

    public void createMeasurement(String turtleID, Measurement measurement) {
        Objects.requireNonNull(DAOFactory.getMeasurementDAO()).createMeasurement(measurement, turtleID);
    }

    public void updateTurtle(String turtleID, String name, String species, Sex sex) {
        Objects.requireNonNull(DAOFactory.getTurtleDAO()).updateTurtle(turtleID, name, species, String.valueOf(sex));
    }

    public void updateExamination(String internalID, String head_status, String eyes_status, String beak_status, String neck_status, String nose_status, String fins_status, String tail_status, String vetNotes, Examination selectedExamination) {
        Objects.requireNonNull(DAOFactory.getExaminationDAO()).updateExamination(internalID, head_status, eyes_status, beak_status, neck_status, nose_status, fins_status, tail_status, vetNotes, selectedExamination);
    }

    public void releaseTurtle(String internalID) {
        Objects.requireNonNull(DAOFactory.getMedicalRecordDAO()).releaseTurtle(internalID);
    }

    public void deleteTurtle(String internalID) {
        Objects.requireNonNull(DAOFactory.getTurtleDAO()).deleteTurtle(internalID);
    }

    public void deleteMeasurement(String turtleID, LocalDate date) {
        Objects.requireNonNull(DAOFactory.getMeasurementDAO()).deleteMeasurement(turtleID, date);
    }

    public void deleteMedicalRecord(String internalID) {
        Objects.requireNonNull(DAOFactory.getMedicalRecordDAO()).deleteMedicalRecord(internalID);
    }

    public void deleteExamination(String internalID, LocalDateTime date) {
        Objects.requireNonNull(DAOFactory.getExaminationDAO()).deleteExamination(internalID, date);
    }

    public Integer[] createCenterStatistics(LocalDate from, LocalDate to, String centerID) {
        return Objects.requireNonNull(DAOFactory.getCenterDAO()).handleCenterStatistics(from, to, centerID);
    }

    public ObservableList<XYChartItem> createTurtleStats(String turtleID, LocalDate startDate, LocalDate endDate) {
        return Objects.requireNonNull(DAOFactory.getExaminationDAO()).createTurtleStats(turtleID, startDate, endDate);
    }

    public Turtle getTurtleByID(String turtleID) {
        return Objects.requireNonNull(DAOFactory.getTurtleDAO()).getTurtleByID(turtleID);
    }
}
