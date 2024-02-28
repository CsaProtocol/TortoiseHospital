package me.csaprotocol.tortoisehospital.daos.pgsql;

import me.csaprotocol.tortoisehospital.daos.ExaminationDAO;
import me.csaprotocol.tortoisehospital.daos.pgsql.jdbc.PostgresDAO;
import me.csaprotocol.tortoisehospital.entities.Examination;
import me.csaprotocol.tortoisehospital.entities.enums.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class ExaminationDAOPostgres extends PostgresDAO implements ExaminationDAO {
    public ExaminationDAOPostgres() {
        super();
    }

    @Override
    public ArrayList<Examination> getExaminationsByMedicalRecordID(String medicalRecordID) {
        String query = "SELECT ex_date, vet_notes, head_status, eyes_status, tail_status, fins_status, neck_status, beak_status, nose_status FROM examination WHERE internal_id = ?";
        ArrayList<Examination> examinations = new ArrayList<>();

        try {
            Connection conn = commonDataSource.getConnection();
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, medicalRecordID);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Examination ex = new Examination();
                ex.setDate(rs.getDate("ex_date").toLocalDate());
                ex.setVet_notes(rs.getString("vet_notes"));
                ex.setHead_status(translateStatus(rs.getString("head_status")));
                ex.setEyes_status(translateStatus(rs.getString("eyes_status")));
                ex.setTail_status(translateStatus(rs.getString("tail_status")));
                ex.setFins_status(translateStatus(rs.getString("fins_status")));
                ex.setNeck_status(translateStatus(rs.getString("neck_status")));
                ex.setBeak_status(translateStatus(rs.getString("beak_status")));
                ex.setNose_status(translateStatus(rs.getString("nose_status")));

                examinations.add(ex);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return examinations;
    }

    @Override
    public void createExamination(String internalID, String head_status, String eyes_status, String beak_status, String neck_status, String nose_status, String fins_status, String tail_status, String vetNotes) {
        String query = "INSERT INTO examination(internal_id, head_status, eyes_status, beak_status, neck_status, nose_status, fins_status, tail_status, vet_notes) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = commonDataSource.getConnection();
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, internalID);
            st.setString(2, translateStatusInverse(head_status));
            st.setString(3, translateStatusInverse(eyes_status));
            st.setString(4, translateStatusInverse(beak_status));
            st.setString(5, translateStatusInverse(neck_status));
            st.setString(6, translateStatusInverse(nose_status));
            st.setString(7, translateStatusInverse(fins_status));
            st.setString(8, translateStatusInverse(tail_status));
            st.setString(9, vetNotes);
            st.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void updateExamination(String internalID, String head_status, String eyes_status, String beak_status, String neck_status, String nose_status, String fins_status, String tail_status, String vetNotes, Examination selectedExamination) {
        String query = "UPDATE examination SET head_status = ?, eyes_status = ?, beak_status = ?, neck_status = ?, nose_status = ?, fins_status = ?, tail_status = ?, vet_notes = ? WHERE internal_id = ? AND ex_date = CURRENT_DATE AND vet_notes = ?";

        try {
            Connection conn = commonDataSource.getConnection();
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, translateStatusInverse(head_status));
            st.setString(2, translateStatusInverse(eyes_status));
            st.setString(3, translateStatusInverse(beak_status));
            st.setString(4, translateStatusInverse(neck_status));
            st.setString(5, translateStatusInverse(nose_status));
            st.setString(6, translateStatusInverse(fins_status));
            st.setString(7, translateStatusInverse(tail_status));
            st.setString(8, vetNotes);
            st.setString(9, internalID);
            st.setString(10, selectedExamination.getVet_notes());
            st.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteExamination(String internalID, LocalDate date, String vetNotes) {
        String query = "DELETE FROM examination WHERE internal_id = ? AND ex_date = ? AND vet_notes = ?";

        try {
            Connection conn = commonDataSource.getConnection();
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, internalID);
            st.setDate(2, java.sql.Date.valueOf(date));
            st.setString(3, vetNotes);
            st.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }


    public Status translateStatus(String status) {
        switch(status) {
            case "C":
                return Status.Compromised;
            case "D":
                return Status.DeepWounds;
            case "L":
                return Status.SuperficialWounds;
            case "N":
                return Status.Normal;
            case "P":
                return Status.Perfect;
            default:
                throw new IllegalArgumentException("Invalid status code: " + status);
        }
    }

    public String translateStatusInverse(String status) {
        switch(status) {
            case "Compromised":
                return "C";
            case "DeepWounds":
                return "D";
            case "SuperficialWounds":
                return "L";
            case "Normal":
                return "N";
            case "Perfect":
                return "P";
            default:
                throw new IllegalArgumentException("Invalid status code: " + status);
        }
    }
}
