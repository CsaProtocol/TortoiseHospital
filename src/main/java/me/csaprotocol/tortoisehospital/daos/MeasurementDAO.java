package me.csaprotocol.tortoisehospital.daos;

import me.csaprotocol.tortoisehospital.entities.Measurement;

import java.util.ArrayList;

public interface MeasurementDAO {
    ArrayList<Measurement> getMeasurementsByTurtleId(String TurtleID);
    void addMeasurement(Measurement measurement, String TurtleID);
}
