package me.csaprotocol.tortoisehospital.daos;

import me.csaprotocol.tortoisehospital.entities.Tank;

import java.util.ArrayList;

public interface TankDAO {
    ArrayList<Tank> getTankByCenterID(String centerID);
    Tank getTankByID(int tankID, String centerID);
}
