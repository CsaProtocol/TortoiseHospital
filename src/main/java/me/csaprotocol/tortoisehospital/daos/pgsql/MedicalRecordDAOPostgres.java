package me.csaprotocol.tortoisehospital.daos.pgsql;

import me.csaprotocol.tortoisehospital.daos.MedicalRecordDAO;
import me.csaprotocol.tortoisehospital.daos.pgsql.jdbc.PostgresDAO;
import me.csaprotocol.tortoisehospital.entities.MedicalRecord;
import me.csaprotocol.tortoisehospital.exceptions.DAOException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class MedicalRecordDAOPostgres extends PostgresDAO implements MedicalRecordDAO {

    public MedicalRecordDAOPostgres() {
            super();
        }

    @Override
    public ArrayList<MedicalRecord> getMedicalRecordsByTurtleID(String turtleID) {
        String query = "SELECT internal_id, access_date, release_date, location_data[0] AS location_longitude, location_data[1] AS location_latitude FROM medical_record WHERE turtle_id = ? ORDER BY access_date DESC";
        ArrayList<MedicalRecord> medicalRecords = new ArrayList<>();

        try {
            Connection conn = commonDataSource.getConnection();
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, turtleID);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                MedicalRecord medRecord = new MedicalRecord();
                medRecord.setInternalID(rs.getString("internal_id"));
                medRecord.setAccess_date(rs.getDate("access_date").toLocalDate());
                medRecord.setLongitude(rs.getDouble("location_longitude"));
                medRecord.setLatitude(rs.getDouble("location_latitude"));
                Date dateHandler = rs.getDate("release_date");
                if(dateHandler != null) {
                    medRecord.setRelease_date(dateHandler.toLocalDate());
                }
                medicalRecords.add(medRecord);
            }

            return medicalRecords;
        } catch (SQLException e) {
            throw new DAOException("Error while getting centers by employee ID", e);
        }
    }

    @Override
    public String createMedicalRecord(String turtleID, String centerID, String accessDate, Double latitude, Double longitude) {
        String query = "INSERT INTO medical_record(access_date, location_data, Center_ID, Turtle_ID) VALUES(?, POINT(?, ?), ?, ?) RETURNING internal_id";
        try {
            Connection conn = commonDataSource.getConnection();
            PreparedStatement st = conn.prepareStatement(query);
            st.setDate(1, Date.valueOf(accessDate));
            st.setDouble(2, longitude);
            st.setDouble(3, latitude);
            st.setString(4, centerID);
            st.setString(5, turtleID);
            ResultSet rs = st.executeQuery();
            rs.next();
            return rs.getString("internal_id");
        } catch (SQLException e) {
            throw new DAOException("Error while getting centers by employee ID", e);
        }
    }

    @Override
    public void releaseTurtle(String internalID) {
        String query = "UPDATE medical_record SET release_date = ? WHERE internal_id = ?";
        try {
            Connection conn = commonDataSource.getConnection();
            PreparedStatement st = conn.prepareStatement(query);
            st.setDate(1, Date.valueOf(LocalDate.now()));
            st.setString(2, internalID);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error while getting centers by employee ID", e);
        }
    }

    @Override
    public void deleteMedicalRecord(String internalID) {
        String query = "DELETE FROM medical_record WHERE internal_id = ?";
        try {
            Connection conn = commonDataSource.getConnection();
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, internalID);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error while getting centers by employee ID", e);
        }
    }
}
