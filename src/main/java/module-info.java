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


    opens me.csaprotocol.tortoisehospital to javafx.fxml;
    opens me.csaprotocol.tortoisehospital.fxmlcontrollers.usermenu to javafx.fxml;
    opens me.csaprotocol.tortoisehospital.fxmlcontrollers to javafx.fxml;
    exports me.csaprotocol.tortoisehospital;
    exports me.csaprotocol.tortoisehospital.fxmlcontrollers.usermenu to com.google.common;
    exports me.csaprotocol.tortoisehospital.fxmlcontrollers.usermenu.turtlepanel to com.google.common;
    opens me.csaprotocol.tortoisehospital.fxmlcontrollers.usermenu.turtlepanel to javafx.fxml;
}