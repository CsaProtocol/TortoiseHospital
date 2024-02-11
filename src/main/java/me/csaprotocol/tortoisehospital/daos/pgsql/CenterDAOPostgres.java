package me.csaprotocol.tortoisehospital.daos.pgsql;

import me.csaprotocol.tortoisehospital.daos.CenterDAO;
import me.csaprotocol.tortoisehospital.daos.pgsql.jdbc.PostgresDAO;
import me.csaprotocol.tortoisehospital.entities.Center;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CenterDAOPostgres extends PostgresDAO implements CenterDAO {

    public CenterDAOPostgres() {
        super();
    }

    @Override
    public ArrayList<Center> getCenterByEmployeeID(String emp_id) {
        //Lista dei centri in cui lavora l'impiegato
        ArrayList<Center> centers = new ArrayList<>();

        //Query per ottenere i centri in cui lavora l'impiegato
        String query = "SELECT center_id, name FROM employment INNER JOIN center" +
                " ON employment.center_id = center.id_center WHERE employee_id = ?";

        //PreparedStatement e ResultSet si autochiudono a fine scope, non è necessario usare
        //rs.close() e st.close()
        try {
            Connection conn = commonDataSource.getConnection();
            assert conn != null;
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, emp_id);
            ResultSet rs = st.executeQuery();

            //Riempimento dell'arraylist con i risultati della query
            while (rs.next()) {
                centers.add(
                        new Center(
                                rs.getString("center_id"),
                                rs.getString("name")
                        )
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return centers;
    }

    public Center getCenterByID(String ID) {
        String query = "SELECT id_center, name, contact_email, contact_number, address, province, city, CAP, dismissed FROM center WHERE id_center = ?";
        try {
            Connection conn = commonDataSource.getConnection();
            assert conn != null;
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, ID);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                Center center = new Center(
                        rs.getString("id_center"),
                        rs.getString("name")
                );

                center.setEmail(rs.getString("contact_email"));
                center.setPhone(rs.getString("contact_number"));
                center.setAddress(rs.getString("address"));
                center.setProvince(rs.getString("province"));
                center.setCity(rs.getString("city"));
                center.setCAP(rs.getString("CAP"));
                center.setDismissed(rs.getBoolean("dismissed"));

                return center;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void create(Center toCreate) {
    }

    @Override
    public void update(Center toUpdate) {

    }

    @Override
    public void delete(Center toDelete) {

    }

}
