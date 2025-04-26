package me.csaprotocol.tortoisehospital.daos;

import me.csaprotocol.tortoisehospital.entities.MedicalRecord;

import java.util.ArrayList;

public interface MedicalRecordDAO {
    ArrayList<MedicalRecord> getMedicalRecordsByTurtleID(String turtleID);
    String createMedicalRecord(String turtleID, String centerID, String accessDate, Double latitude, Double longitude);
    void releaseTurtle(String internalID);
    void deleteMedicalRecord(String internalID);
}
