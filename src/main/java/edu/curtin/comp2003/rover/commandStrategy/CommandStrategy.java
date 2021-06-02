package edu.curtin.comp2003.rover.commandStrategy;

/**
 * Interface for strategy pattern.
 * @see edu.curtin.comp2003.rover.core.MarsRover Mars rover
 * @author Heetesh Doorbiz
 */
public interface CommandStrategy {
    /**
     * Action to be performed
     * @param s array of string
     */
    void action(String[] s);
}
