package me.csaprotocol.tortoisehospital.daos.pgsql.jdbc;

import com.zaxxer.hikari.HikariDataSource;

public abstract class PostgresDAO {
    protected HikariDataSource commonDataSource;

    public PostgresDAO() {
        this.commonDataSource = connectionPoolPostgres.getInstance().getDataSource();
    }
}
