package me.csaprotocol.tortoisehospital.daos;

import eu.hansolo.fx.charts.data.XYChartItem;
import eu.hansolo.toolbox.observables.ObservableList;
import me.csaprotocol.tortoisehospital.entities.Examination;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public interface ExaminationDAO {
    ArrayList<Examination> getExaminationsByMedicalRecordID(String medicalRecordID);
    void createExamination(String internalID, String head_status, String eyes_status, String beak_status, String neck_status, String nose_status, String fins_status, String tail_status, String vetNotes);
    void updateExamination(String internalID, String head_status, String eyes_status, String beak_status, String neck_status, String nose_status, String fins_status, String tail_status, String vetNotes, Examination selectedExamination);
    void deleteExamination(String InternalID, LocalDateTime date);
    ObservableList<XYChartItem> createTurtleStats(String turtleID, LocalDate startDate, LocalDate endDate);
}
