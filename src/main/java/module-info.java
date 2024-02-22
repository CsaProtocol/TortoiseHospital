module me.csaprotocol.tortoisehospital {
    requires javafx.controls;
    requires javafx.fxml;
    //SQL functionalities for Java
    requires java.sql;
    //PostgreSQL JDBC Driver
    requires org.postgresql.jdbc;
    //HikariCP connection pool
    requires com.zaxxer.hikari;
    //Google Guava
    requires com.google.common;
    //Projekt Lombok
    requires static lombok;
    //MaterialFX
    requires MaterialFX;
    requires org.controlsfx.controls;
    //ControlsFX


    opens me.csaprotocol.tortoisehospital to javafx.fxml;
    opens me.csaprotocol.tortoisehospital.fxmlcontrollers to javafx.fxml;
    opens me.csaprotocol.tortoisehospital.fxmlcontrollers.steppers to javafx.fxml;
    opens me.csaprotocol.tortoisehospital.fxmlcontrollers.usermenu to javafx.fxml;
    opens me.csaprotocol.tortoisehospital.fxmlcontrollers.modularMenu to javafx.fxml;

    exports me.csaprotocol.tortoisehospital;
    exports me.csaprotocol.tortoisehospital.fxmlcontrollers.usermenu to com.google.common;

}