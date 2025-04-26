package me.csaprotocol.tortoisehospital.controllers;

import me.csaprotocol.tortoisehospital.daos.EmployeeDAO;
import me.csaprotocol.tortoisehospital.daos.factory.DAOFactory;

public class LoginController {

    public boolean handleLogin(String emp_id, String password) {
        EmployeeDAO empDAO = DAOFactory.getEmployeeDAO();
        assert empDAO != null;
        if (empDAO.validateLogin(emp_id, password)) {
            DataController data = DataController.getInstance();
            data.getLoggedUser().setID(emp_id);
            return true;
        } else {
            return false;
        }
    }
}
