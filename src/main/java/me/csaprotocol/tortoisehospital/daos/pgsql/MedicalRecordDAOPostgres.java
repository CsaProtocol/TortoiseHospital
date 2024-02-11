package me.csaprotocol.tortoisehospital.daos.pgsql;

import me.csaprotocol.tortoisehospital.daos.MedicalRecordDAO;
import me.csaprotocol.tortoisehospital.daos.pgsql.jdbc.PostgresDAO;

public class MedicalRecordDAOPostgres extends PostgresDAO implements MedicalRecordDAO {

        public MedicalRecordDAOPostgres() {
            super();
        }
}
