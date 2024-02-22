package me.csaprotocol.tortoisehospital.daos.pgsql;

import me.csaprotocol.tortoisehospital.daos.MedicalRecordDAO;
import me.csaprotocol.tortoisehospital.daos.pgsql.jdbc.PostgresDAO;
import me.csaprotocol.tortoisehospital.entities.MedicalRecord;

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
            throw new RuntimeException();
        }
    }
}
