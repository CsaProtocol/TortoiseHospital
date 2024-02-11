package me.csaprotocol.tortoisehospital.daos.pgsql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import me.csaprotocol.tortoisehospital.daos.EmployeeDAO;
import me.csaprotocol.tortoisehospital.daos.pgsql.jdbc.PostgresDAO;

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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return true;
    }
}
