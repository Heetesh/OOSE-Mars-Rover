package edu.curtin.comp2003.rover.events;

/**
 * Event listener class to be used for listening to event by any observer implementor.
 * @see edu.curtin.comp2003.rover.core.MarsRover Mars rover
 * @author Heetesh Doorbiz
 */
@FunctionalInterface
public interface EventListener {
    /** Handles event defined */
    void action();
}
