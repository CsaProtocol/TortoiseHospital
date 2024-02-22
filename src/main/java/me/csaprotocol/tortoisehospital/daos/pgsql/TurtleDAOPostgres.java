package me.csaprotocol.tortoisehospital.daos.pgsql;

import me.csaprotocol.tortoisehospital.daos.TurtleDAO;
import me.csaprotocol.tortoisehospital.daos.pgsql.jdbc.PostgresDAO;
import me.csaprotocol.tortoisehospital.entities.Turtle;
import me.csaprotocol.tortoisehospital.entities.enums.Sex;

import java.sql.*;
import java.util.ArrayList;

public class TurtleDAOPostgres extends PostgresDAO implements TurtleDAO {
    public TurtleDAOPostgres() {
        super();
    }

    @Override
    public ArrayList<Turtle> searchTurtle(String toSearch) {
        String query = "SELECT turtle_id, name FROM turtle WHERE lower(turtle_id) LIKE lower(?) OR lower(name) LIKE lower(?)";
        ArrayList<Turtle> turtles = new ArrayList<>();

        try {
            Connection conn = commonDataSource.getConnection();
            assert conn != null;
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, toSearch.concat("%"));
            st.setString(2, toSearch.concat("%"));
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                turtles.add(
                        new Turtle(
                            rs.getString("turtle_id"),
                            rs.getString("name")
                ));
            }
            return turtles;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public Object[] getTurtleAndTankByTurtleID(String turtleID) {
        String query = "SELECT turtle_id, name, turtle_sex, species, tank_id, center_id FROM turtle WHERE turtle_id = ?";

        try {
            Connection conn = commonDataSource.getConnection();
            assert conn != null;
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, turtleID);
            ResultSet rs = st.executeQuery();

            if(rs.next()) {
                Turtle newTurtle = new Turtle(
                        rs.getString("turtle_id"),
                        rs.getString("name")
                );
                String sex = rs.getString("turtle_sex");

                if(sex.equals("F")) {
                    newTurtle.setSex(Sex.Female);
                } else {
                    newTurtle.setSex(Sex.Male);
                }

                newTurtle.setSpecies(rs.getString("species"));

                return new Object[] {
                        newTurtle,
                        rs.getInt("tank_id"),
                        rs.getString("center_id")
                };

                }
            } catch (SQLException e) {
                throw new RuntimeException();
            }
        return new Object[0];
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
