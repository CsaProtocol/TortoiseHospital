package me.csaprotocol.tortoisehospital.daos;

import me.csaprotocol.tortoisehospital.entities.Center;

import java.util.ArrayList;

public interface CenterDAO {
    ArrayList<Center> getCenterByEmployeeID(String emp_id);
    Center getCenterByID(String ID);
    void create(Center center);
    void update(Center center);
    void delete(Center center);
}
