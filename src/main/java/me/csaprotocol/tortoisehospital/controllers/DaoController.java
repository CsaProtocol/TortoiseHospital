package me.csaprotocol.tortoisehospital.controllers;

import me.csaprotocol.tortoisehospital.daos.factory.DAOFactory;
import me.csaprotocol.tortoisehospital.entities.Center;
import me.csaprotocol.tortoisehospital.entities.Tank;
import me.csaprotocol.tortoisehospital.entities.Turtle;

import java.util.ArrayList;
import java.util.Objects;

public class DaoController {

    private final DataController data = DataController.getInstance();
    //DAO Interfaces
    public ArrayList<Center> getCentersByEmployeeID() {
        return Objects.requireNonNull(DAOFactory.getCenterDAO()).getCenterByEmployeeID(data.getLoggedUser().getID());
    }

    public Center getCenterByID(String centerID) {
        return Objects.requireNonNull(DAOFactory.getCenterDAO()).getCenterByID(centerID);
    }

    public ArrayList<Tank> getTanksByCenterID(String centerID) {
        return Objects.requireNonNull(DAOFactory.getTankDAO()).getTankByCenterID(centerID);
    }

    public Tank getTankByID(int tankID, String centerID) {
        return Objects.requireNonNull(DAOFactory.getTankDAO()).getTankByID(tankID, centerID);
    }

    public ArrayList<Turtle> getTurtlesByTankID(int tankID, String centerID) {
        return Objects.requireNonNull(DAOFactory.getTurtleDAO()).getTurtlesByTankID(tankID, centerID);
    }

    public ArrayList<Turtle> getTurtlesByCenterID(String centerID) {
        return Objects.requireNonNull(DAOFactory.getTurtleDAO()).getTurtlesByCenterID(centerID);
    }
}
