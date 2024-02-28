package me.csaprotocol.tortoisehospital.daos;

import me.csaprotocol.tortoisehospital.entities.Measurement;

import java.time.LocalDate;
import java.util.ArrayList;

public interface MeasurementDAO {
    ArrayList<Measurement> getMeasurementsByTurtleId(String TurtleID);
    void createMeasurement(Measurement measurement, String TurtleID);
    void deleteMeasurement(String turtleID, LocalDate date);
}
