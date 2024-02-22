package me.csaprotocol.tortoisehospital.daos;

import javafx.util.Pair;
import me.csaprotocol.tortoisehospital.entities.Turtle;

import java.util.ArrayList;

public interface TurtleDAO {
    ArrayList<Turtle> searchTurtle(String toSearch);
    Object[] getTurtleAndTankByTurtleID(String turtleID);
    ArrayList<Turtle> getTurtlesByCenterID(String centerID);
    ArrayList<Turtle> getTurtlesByTankID(int tankID, String centerID);
}
