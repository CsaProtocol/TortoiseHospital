package me.csaprotocol.tortoisehospital.exceptions;

import org.controlsfx.control.Notifications;

public class ExceptionHandler {
    public void handleException(Exception e) {
        Notifications.create()
                .title("Error")
                .text("An error occurred, please contact an administrator.")
                .showError();

        e.printStackTrace();
        //TODO log the error with log4j
    }

    public void handleException(String message) {
        Notifications.create()
                .title("Error")
                .text(message)
                .showWarning();
    }
}
