package me.csaprotocol.tortoisehospital.daos.pgsql;

import me.csaprotocol.tortoisehospital.daos.ExaminationDAO;
import me.csaprotocol.tortoisehospital.daos.pgsql.jdbc.PostgresDAO;
import me.csaprotocol.tortoisehospital.entities.Examination;
import me.csaprotocol.tortoisehospital.entities.enums.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

}
