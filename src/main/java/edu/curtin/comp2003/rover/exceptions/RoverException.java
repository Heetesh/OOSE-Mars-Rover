package edu.curtin.comp2003.rover.exceptions;

/**
 * Exception class for general use in the Mars Rover
 * @see edu.curtin.comp2003.rover.core.MarsRover Mars Rover
 * @author Heetesh Doorbiz
 */
public class RoverException extends RuntimeException {

    public RoverException(String message) {
        super(message);
    }

    public RoverException(String message, Throwable cause) {
        super(message, cause);
    }

}
