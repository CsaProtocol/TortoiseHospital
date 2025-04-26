package me.csaprotocol.tortoisehospital.daos;

public interface EmployeeDAO {
    boolean validateLogin(String employeeID, String password);
}
