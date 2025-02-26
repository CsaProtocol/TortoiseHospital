package me.csaprotocol.tortoisehospital.exceptions;

import org.controlsfx.control.Notifications;

public class ExceptionHandler {
    public void handleException(Exception e) {
        Notifications.create()
                .title("Error")
                .text("An error occurred, please contact an administrator.")
                .showError();
        //TODO log the error with log4j
    }

    public void handleException(String message) {
        Notifications.create()
                .title("Warning")
                .text(message)
                .showWarning();
    }
}
