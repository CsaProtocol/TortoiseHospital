package me.csaprotocol.tortoisehospital.exceptions;

public class CoreException extends Exception {
    public CoreException() {
        super();
    }
    public CoreException(String message) {
        super(message);
    }

    public CoreException(Throwable cause) {
        super(cause);
    }

    public CoreException(String message, Throwable cause) {
        super(message, cause);
    }
}
