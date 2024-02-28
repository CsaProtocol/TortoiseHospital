package me.csaprotocol.tortoisehospital.daos;

import me.csaprotocol.tortoisehospital.entities.Center;

import java.time.LocalDate;
import java.util.ArrayList;

public interface CenterDAO {
    ArrayList<Center> getCenterByEmployeeID(String emp_id);
    Center getCenterByID(String ID);
    Integer[] handleCenterStatistics(LocalDate from, LocalDate to, String centerID);
}
