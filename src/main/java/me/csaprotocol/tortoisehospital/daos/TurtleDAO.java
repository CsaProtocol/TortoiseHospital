package me.csaprotocol.tortoisehospital.daos;

import me.csaprotocol.tortoisehospital.entities.Turtle;

import java.util.ArrayList;

public interface TurtleDAO {
    Turtle getTurtleByID(String turtleID);
    ArrayList<Turtle> getTurtlesByCenterID(String centerID);
    ArrayList<Turtle> getTurtlesByTankID(int tankID, String centerID);
}
