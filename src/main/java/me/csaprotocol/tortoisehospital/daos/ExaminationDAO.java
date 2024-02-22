package me.csaprotocol.tortoisehospital.daos;

import me.csaprotocol.tortoisehospital.entities.Examination;

import java.util.ArrayList;

public interface ExaminationDAO {
    ArrayList<Examination> getExaminationsByMedicalRecordID(String medicalRecordID);
}
