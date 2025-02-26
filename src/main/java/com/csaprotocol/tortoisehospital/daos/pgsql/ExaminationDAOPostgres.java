package me.csaprotocol.tortoisehospital.daos.pgsql;

import eu.hansolo.fx.charts.data.XYChartItem;
import eu.hansolo.toolbox.observables.ObservableList;
import me.csaprotocol.tortoisehospital.daos.ExaminationDAO;
import me.csaprotocol.tortoisehospital.daos.pgsql.jdbc.PostgresDAO;
import me.csaprotocol.tortoisehospital.entities.Examination;
import me.csaprotocol.tortoisehospital.entities.enums.Status;
import me.csaprotocol.tortoisehospital.exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
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
                ex.setDate(rs.getTimestamp("ex_date").toLocalDateTime());
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
        } catch (SQLException e) {
            throw new DAOException("Error while getting centers by employee ID", e);
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
            throw new DAOException("Error while getting centers by employee ID", e);
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

        } catch (SQLException e) {
            throw new DAOException("Error while getting centers by employee ID", e);
        }
    }

    @Override
    public void deleteExamination(String internalID, LocalDateTime date) {
        String query = "DELETE FROM examination WHERE internal_id = ? AND ex_date = ?";

        try {
            Connection conn = commonDataSource.getConnection();
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, internalID);
            st.setTimestamp(2, java.sql.Timestamp.valueOf(date));
            st.executeUpdate();

        } catch (Exception e) {
            throw new DAOException("Error while getting centers by employee ID", e);
        }
    }

    @Override
    public ObservableList<XYChartItem> createTurtleStats(String turtleID, LocalDate startDate, LocalDate endDate) {
        String query = "SELECT AvgHealth, ex_date FROM examination INNER JOIN medical_record ON examination.internal_id = medical_record.internal_id WHERE ex_date BETWEEN ? AND ? AND turtle_id = ? ORDER BY ex_date ASC";
        ObservableList<XYChartItem> stats = new ObservableList<>();
        try {
            Connection conn = commonDataSource.getConnection();
            PreparedStatement st = conn.prepareStatement(query);
            st.setTimestamp(1, java.sql.Timestamp.valueOf(startDate.atStartOfDay()));
            st.setTimestamp(2, java.sql.Timestamp.valueOf(endDate.atStartOfDay()));
            st.setString(3, turtleID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                LocalDateTime toConvert = rs.getTimestamp("ex_date").toLocalDateTime();
                Status avghealth = translateStatus(rs.getString("AvgHealth"));
                long epoch = toConvert.toInstant(ZoneOffset.UTC).getEpochSecond();
                stats.add(new XYChartItem(epoch, avghealth.getValue()));
            }
        } catch (SQLException e) {
            throw new DAOException("Error while getting centers by employee ID", e);
        }
        return stats;
    }


    public Status translateStatus(String status) {
        return switch (status) {
            case "C" -> Status.Compromised;
            case "D" -> Status.DeepWounds;
            case "L" -> Status.SuperficialWounds;
            case "N" -> Status.Normal;
            case "P" -> Status.Perfect;
            default -> throw new IllegalArgumentException("Invalid status code: " + status);
        };
    }

    public String translateStatusInverse(String status) {
        return switch (status) {
            case "Compromised" -> "C";
            case "DeepWounds" -> "D";
            case "SuperficialWounds" -> "L";
            case "Normal" -> "N";
            case "Perfect" -> "P";
            default -> throw new IllegalArgumentException("Invalid status code: " + status);
        };
    }
}
