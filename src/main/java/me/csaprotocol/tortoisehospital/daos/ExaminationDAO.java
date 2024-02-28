package me.csaprotocol.tortoisehospital.daos;

import me.csaprotocol.tortoisehospital.entities.Examination;

import java.time.LocalDate;
import java.util.ArrayList;

public interface ExaminationDAO {
    ArrayList<Examination> getExaminationsByMedicalRecordID(String medicalRecordID);
    void createExamination(String internalID, String head_status, String eyes_status, String beak_status, String neck_status, String nose_status, String fins_status, String tail_status, String vetNotes);
    void updateExamination(String internalID, String head_status, String eyes_status, String beak_status, String neck_status, String nose_status, String fins_status, String tail_status, String vetNotes, Examination selectedExamination);
    void deleteExamination(String internalID, LocalDate date, String vetNotes);
}
