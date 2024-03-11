package me.csaprotocol.tortoisehospital.daos;

import me.csaprotocol.tortoisehospital.entities.Turtle;

import java.util.ArrayList;

public interface TurtleDAO {
    ArrayList<Turtle> searchTurtle(String toSearch);
    Object[] getTurtleAndTankByTurtleID(String turtleID);
    ArrayList<Turtle> getTurtlesByCenterID(String centerID);
    ArrayList<Turtle> getTurtlesByTankID(int tankID, String centerID);
    ArrayList<Turtle> getAllTurtles();
    String createTurtle(String name, String species, String sex);
    void updateTurtleTank(String turtleID, String centerID, int tankID);
    void updateTurtle(String turtleID, String name, String species, String sex);
    void deleteTurtle(String turtleID);
    Turtle getTurtleByID(String turtleID);
}
