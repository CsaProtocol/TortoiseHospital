package me.csaprotocol.tortoisehospital.daos.pgsql;

import me.csaprotocol.tortoisehospital.daos.TurtleDAO;
import me.csaprotocol.tortoisehospital.daos.pgsql.jdbc.PostgresDAO;
import me.csaprotocol.tortoisehospital.entities.Turtle;
import me.csaprotocol.tortoisehospital.entities.enums.Sex;
import me.csaprotocol.tortoisehospital.exceptions.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

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
            throw new DAOException("Error while getting centers by employee ID", e);
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
                throw new DAOException("Error while getting centers by employee ID", e);
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
            throw new DAOException("Error while getting centers by employee ID", e);
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
            throw new DAOException("Error while getting centers by employee ID", e);
        }

        return turtles;
    }

    public String createTurtle(String name, String species, String sex) {
        String query = "INSERT INTO turtle(turtle_sex, name, species) VALUES(?, ?, ?) RETURNING turtle_id";
        try {
            Connection conn = commonDataSource.getConnection();
            assert conn != null;
            PreparedStatement st = conn.prepareStatement(query);
            if(sex.equals("Male"))
                st.setString(1, "M");
            else
                st.setString(1, "F");

            st.setString(2, name);
            st.setString(3, species);
            ResultSet rs = st.executeQuery();
            rs.next();
            return rs.getString("turtle_id");

        } catch (SQLException e) {
            throw new DAOException("Error while getting centers by employee ID", e);
        }
    }

    @Override
    public void updateTurtleTank(String turtleID, String centerID, int tankID) {
        String query = "UPDATE turtle SET tank_id = ?, center_id = ? WHERE turtle_id = ?";
        try {
            Connection conn = commonDataSource.getConnection();
            assert conn != null;
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, tankID);
            st.setString(2, centerID);
            st.setString(3, turtleID);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error while getting centers by employee ID", e);
        }
    }

    @Override
    public void updateTurtle(String turtleID, String name, String species, String sex) {
        String query = "UPDATE turtle SET name = ?, species = ?, turtle_sex = ? WHERE turtle_id = ?";
        try {
            Connection conn = commonDataSource.getConnection();
            assert conn != null;
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, name);
            st.setString(2, species);
            if(sex.equals("Male"))
                st.setString(3, "M");
            else
                st.setString(3, "F");
            st.setString(4, turtleID);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error while getting centers by employee ID", e);
        }
    }

    @Override
    public void deleteTurtle(String turtleID) {
        String query = "DELETE FROM turtle WHERE turtle_id = ?";
        try {
            Connection conn = commonDataSource.getConnection();
            assert conn != null;
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, turtleID);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error while getting centers by employee ID", e);
        }
    }
    @Override
    public ArrayList<Turtle> getAllTurtles() {
        ArrayList<Turtle> turtles = new ArrayList<>();
        String query = "SELECT turtle_id, name FROM turtle";
        try {
            Connection conn = commonDataSource.getConnection();
            assert conn != null;
            PreparedStatement st = conn.prepareStatement(query);
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
            throw new DAOException("Error while getting centers by employee ID", e);
        }
        return turtles;
    }

    @Override
    public Turtle getTurtleByID(String turtleID) {
        String query = "SELECT * FROM turtle WHERE turtle_id = ?";
        try {
            Connection conn = commonDataSource.getConnection();
            assert conn != null;
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, turtleID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Turtle turtle = new Turtle(
                    rs.getString("turtle_id"),
                    rs.getString("name")
                );
                turtle.setSpecies(rs.getString("species"));
                if(Objects.equals(rs.getString("turtle_sex"), "M")) {
                    turtle.setSex(Sex.Male);
                } else {
                    turtle.setSex(Sex.Female);
                }
                return turtle;
            }
        } catch (SQLException e) {
            throw new DAOException("Error while getting centers by employee ID", e);
        }
        return null;
    }
}
