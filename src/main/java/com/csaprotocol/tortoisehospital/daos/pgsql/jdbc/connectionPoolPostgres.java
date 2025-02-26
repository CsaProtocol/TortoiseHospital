package me.csaprotocol.tortoisehospital.daos.pgsql.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;

public class connectionPoolPostgres {
    private HikariDataSource ds;
    private @Getter static final connectionPoolPostgres instance = new connectionPoolPostgres();
    private connectionPoolPostgres() {
        initializeConnectionPoolPostgres();
    }

    private void initializeConnectionPoolPostgres() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/tortoisehospital");
        config.setUsername("postgres");
        config.setPassword("YYYYYYYY");
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
