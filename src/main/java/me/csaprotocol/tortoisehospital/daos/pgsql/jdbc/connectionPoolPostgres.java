package me.csaprotocol.tortoisehospital.daos.pgsql.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class connectionPoolPostgres {
    private HikariDataSource ds;
    private @Getter static final connectionPoolPostgres instance = new connectionPoolPostgres();
    private connectionPoolPostgres() {
        initializeConnectionPoolPostgres();
    }

    private void initializeConnectionPoolPostgres() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/TortoiseHospital");
        config.setUsername("postgres");
        config.setPassword("edugau01");
        config.setMaximumPoolSize(100);
        ds = new HikariDataSource(config);
    }

    public void closeConnectionPoolPostgres() {
        ds.close();
    }

    public HikariDataSource getDataSource() {
        return ds;
    }

}
