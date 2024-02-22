package me.csaprotocol.tortoisehospital.daos;

import me.csaprotocol.tortoisehospital.entities.MedicalRecord;

import java.util.ArrayList;

public interface MedicalRecordDAO {
    ArrayList<MedicalRecord> getMedicalRecordsByTurtleID(String turtleID);
}
