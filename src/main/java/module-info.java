module me.csaprotocol.tortoisehospital {
    requires javafx.controls;
    requires javafx.fxml;


    opens me.csaprotocol.tortoisehospital to javafx.fxml;
    exports me.csaprotocol.tortoisehospital;
}