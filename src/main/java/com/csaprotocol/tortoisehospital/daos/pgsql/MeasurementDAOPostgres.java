package me.csaprotocol.tortoisehospital.daos.pgsql;

import io.github.palexdev.mfxeffects.ripple.base.Ripple;
import me.csaprotocol.tortoisehospital.daos.MeasurementDAO;
import me.csaprotocol.tortoisehospital.daos.pgsql.jdbc.PostgresDAO;
import me.csaprotocol.tortoisehospital.entities.Measurement;
import me.csaprotocol.tortoisehospital.exceptions.DAOException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class MeasurementDAOPostgres extends PostgresDAO implements MeasurementDAO {

    public MeasurementDAOPostgres() {
        super();
    }

    @Override
    public ArrayList<Measurement> getMeasurementsByTurtleId(String TurtleID) {
        String query = "SELECT width, length, weight, m_date FROM measurement WHERE turtle_id = ? ORDER BY m_date DESC";
        ArrayList<Measurement> measurements = new ArrayList<>();

        try {
            Connection conn = commonDataSource.getConnection();
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, TurtleID);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                Measurement measurement = new Measurement();
                measurement.setWidth(rs.getFloat("width"));
                measurement.setLength(rs.getFloat("length"));
                measurement.setWeight(rs.getFloat("weight"));
                measurement.setDate(rs.getDate("m_date").toLocalDate());
                //Add to ArrayList
                measurements.add(measurement);
            }

            return measurements;

        } catch (SQLException e) {
            throw new DAOException("Error while getting centers by employee ID", e);
        }
    }

    @Override
    public void createMeasurement(Measurement measurement, String turtleID) {
        String query = "INSERT INTO Measurement(turtle_ID, width, length, weight, m_date) VALUES(?, ?, ?, ?, ?)";

        try {
            Connection conn = commonDataSource.getConnection();
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, turtleID);
            st.setFloat(2, measurement.getWidth());
            st.setFloat(3, measurement.getLength());
            st.setFloat(4, measurement.getWeight());
            st.setDate(5, java.sql.Date.valueOf(measurement.getDate()));
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error while getting centers by employee ID", e);
        }
    }

    public void deleteMeasurement(String turtleID, LocalDate date) {
        String query = "DELETE FROM Measurement WHERE turtle_ID = ? AND m_date = ?";

        try {
            Connection conn = commonDataSource.getConnection();
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, turtleID);
            st.setDate(2, Date.valueOf(date));
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error while getting centers by employee ID", e);
        }
    }
}
