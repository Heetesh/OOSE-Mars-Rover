package edu.curtin.comp2003.rover.exceptions;

public class RoverException extends RuntimeException {

    public RoverException(String message) {
        super(message);
    }

    public RoverException(String message, Throwable cause) {
        super(message, cause);
    }

}
