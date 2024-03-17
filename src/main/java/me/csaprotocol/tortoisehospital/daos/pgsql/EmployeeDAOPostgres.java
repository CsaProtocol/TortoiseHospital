package me.csaprotocol.tortoisehospital.daos.pgsql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import me.csaprotocol.tortoisehospital.daos.EmployeeDAO;
import me.csaprotocol.tortoisehospital.daos.pgsql.jdbc.PostgresDAO;
import me.csaprotocol.tortoisehospital.exceptions.DAOException;

public class EmployeeDAOPostgres extends PostgresDAO implements EmployeeDAO {

    public EmployeeDAOPostgres() {
        super();
    }

    public boolean validateLogin(String employeeID, String password) {
        String query = "SELECT * FROM login WHERE employee_id=? AND hash_password=crypt(?, hash_password);";
        try {
            Connection conn = commonDataSource.getConnection();
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, employeeID);
            st.setString(2, password);

            ResultSet rs = st.executeQuery();

            if (!rs.next()) {
                return false;
            }
        } catch (SQLException e) {
            throw new DAOException("Error while getting centers by employee ID", e);
        }

        return true;
    }
}
