package me.csaprotocol.tortoisehospital.daos.pgsql;

import me.csaprotocol.tortoisehospital.daos.TankDAO;
import me.csaprotocol.tortoisehospital.daos.pgsql.jdbc.PostgresDAO;
import me.csaprotocol.tortoisehospital.entities.Tank;
import me.csaprotocol.tortoisehospital.exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TankDAOPostgres extends PostgresDAO implements TankDAO {
    public TankDAOPostgres() {
        super();
    }
    public ArrayList<Tank> getTankByCenterID(String centerID) {
        ArrayList<Tank> tanks = new ArrayList<>();

        String query = "SELECT tank_id, center_id, capacity FROM tank WHERE center_id = ?";
        try {
            Connection conn = commonDataSource.getConnection();
            assert conn != null;
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, centerID);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                tanks.add(
                        new Tank(
                                rs.getInt("tank_id"),
                                rs.getString("center_id"),
                                rs.getInt("capacity")
                        )
                );
            } return tanks;
        } catch (Exception e) {
            throw new DAOException("Error while getting centers by employee ID", e);
        }
    }

    public Tank getTankByID(int tankID, String centerID) {
        String query = "SELECT tank_id, center_id, capacity FROM tank WHERE tank_id = ? AND center_id = ?";
        try {
            Connection conn = commonDataSource.getConnection();
            assert conn != null;
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, tankID);
            st.setString(2, centerID);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return new Tank(
                        rs.getInt("tank_id"),
                        rs.getString("center_id"),
                        rs.getInt("capacity")
                );
            } return null;
        } catch (Exception e) {
            throw new DAOException("Error while getting centers by employee ID", e);
        }
    }
}
