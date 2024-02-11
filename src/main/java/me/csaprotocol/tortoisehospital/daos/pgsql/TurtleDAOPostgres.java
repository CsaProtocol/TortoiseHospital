package me.csaprotocol.tortoisehospital.daos.pgsql;

import me.csaprotocol.tortoisehospital.daos.TurtleDAO;
import me.csaprotocol.tortoisehospital.daos.pgsql.jdbc.PostgresDAO;
import me.csaprotocol.tortoisehospital.entities.Turtle;

import java.sql.*;
import java.util.ArrayList;

public class TurtleDAOPostgres extends PostgresDAO implements TurtleDAO {
    public TurtleDAOPostgres() {
        super();
    }
    public Turtle getTurtleByID(String turtleID) {
        return null;
    }
    public ArrayList<Turtle> getTurtlesByCenterID(String centerID) {
        ArrayList<Turtle> turtles = new ArrayList<>();
        String query = "SELECT turtle_id, name FROM turtle WHERE center_id = ?";

        return getTurtles(centerID, turtles, query);
    }


    public ArrayList<Turtle> getTurtlesByTankID(int tankID, String centerID) {
        ArrayList<Turtle> turtles = new ArrayList<>();
        String query = "SELECT turtle_id, name FROM turtle WHERE center_ID = ? AND tank_id = ?";

        try {
            Connection conn = commonDataSource.getConnection();
            assert conn != null;
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, centerID);
            st.setInt(2, tankID);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                turtles.add(
                    new Turtle(
                        rs.getString("turtle_id"),
                        rs.getString("name")
                    )
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return turtles;
    }

    private ArrayList<Turtle> getTurtles(String ID, ArrayList<Turtle> turtles, String query) {
        try {
            Connection conn = commonDataSource.getConnection();
            assert conn != null;
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, ID);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                turtles.add(
                    new Turtle(
                        rs.getString("turtle_id"),
                        rs.getString("name")
                    )
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return turtles;
    }
}
