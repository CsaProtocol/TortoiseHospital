package me.csaprotocol.tortoisehospital.daos.factory;

import me.csaprotocol.tortoisehospital.daos.*;
import me.csaprotocol.tortoisehospital.daos.pgsql.*;

import java.io.*;
import java.util.Properties;

public class DAOFactory {
    private static final String PROPERTIES_FILE_PATH = "/me/csaprotocol/tortoisehospital/resources/db.properties";
    private static final String DATABASE_TYPE_KEY = "database.type";

    public static CenterDAO getCenterDAO() {
        Properties properties = new Properties();

        try (InputStream is = DAOFactory.class.getResourceAsStream(PROPERTIES_FILE_PATH)) {
            properties.load(is);

            String databaseType = properties.getProperty(DATABASE_TYPE_KEY);

            switch (databaseType.toLowerCase()) {
                case "postgresql":
                    return new CenterDAOPostgres();
                default:
                    throw new IllegalArgumentException("Unsupported database type: " + databaseType);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static EmployeeDAO getEmployeeDAO() {
        Properties properties = new Properties();
        try (InputStream is = DAOFactory.class.getResourceAsStream(PROPERTIES_FILE_PATH)) {
            properties.load(is);

            String databaseType = properties.getProperty(DATABASE_TYPE_KEY);

            switch (databaseType.toLowerCase()) {
                case "postgresql":
                    return new EmployeeDAOPostgres();
                default:
                    throw new IllegalArgumentException("Unsupported database type: " + databaseType);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ExaminationDAO getExaminationDAO() {
        Properties properties = new Properties();

        try (InputStream is = DAOFactory.class.getResourceAsStream(PROPERTIES_FILE_PATH)) {
            properties.load(is);

            String databaseType = properties.getProperty(DATABASE_TYPE_KEY);

            switch (databaseType.toLowerCase()) {
                case "postgresql":
                    return new ExaminationDAOPostgres();
                default:
                    throw new IllegalArgumentException("Unsupported database type: " + databaseType);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static MeasurementDAO getMeasurementDAO() {
        Properties properties = new Properties();

        try (InputStream is = DAOFactory.class.getResourceAsStream(PROPERTIES_FILE_PATH)) {
            properties.load(is);

            String databaseType = properties.getProperty(DATABASE_TYPE_KEY);

            switch (databaseType.toLowerCase()) {
                case "postgresql":
                    return new MeasurementDAOPostgres();
                default:
                    throw new IllegalArgumentException("Unsupported database type: " + databaseType);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static MedicalRecordDAO getMedicalRecordDAO() {
        Properties properties = new Properties();

        try (InputStream is = DAOFactory.class.getResourceAsStream(PROPERTIES_FILE_PATH)) {
            properties.load(is);

            String databaseType = properties.getProperty(DATABASE_TYPE_KEY);

            switch (databaseType.toLowerCase()) {
                case "postgresql":
                    return new MedicalRecordDAOPostgres();
                default:
                    throw new IllegalArgumentException("Unsupported database type: " + databaseType);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static TankDAO getTankDAO() {
        Properties properties = new Properties();

        try (InputStream is = DAOFactory.class.getResourceAsStream(PROPERTIES_FILE_PATH)) {
            properties.load(is);

            String databaseType = properties.getProperty(DATABASE_TYPE_KEY);

            switch (databaseType.toLowerCase()) {
                case "postgresql":
                    return new TankDAOPostgres();
                default:
                    throw new IllegalArgumentException("Unsupported database type: " + databaseType);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static TurtleDAO getTurtleDAO() {
        Properties properties = new Properties();

        try (InputStream is = DAOFactory.class.getResourceAsStream(PROPERTIES_FILE_PATH)) {
            properties.load(is);

            String databaseType = properties.getProperty(DATABASE_TYPE_KEY);

            switch (databaseType.toLowerCase()) {
                case "postgresql":
                    return new TurtleDAOPostgres();
                default:
                    throw new IllegalArgumentException("Unsupported database type: " + databaseType);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}