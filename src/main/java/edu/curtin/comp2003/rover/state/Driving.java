package edu.curtin.comp2003.rover.state;

import edu.curtin.comp2003.rover.core.MarsRover;
import edu.curtin.comp2003.rover.events.DriveStatusListener;
import edu.curtin.comp2003.rover.events.EventListener;

/**
 * Class representing the driving state of the mars rover.<br>
 * @see MarsRover Mars Rover
 * @author Heetesh Doorbiz
 */
public class Driving extends RoverState {

    /** Listener instance */
    private EventListener listener = null;
    /** Keeping track whether driving or not */
    private boolean driving = false;

    public Driving(MarsRover marsRover) {
        super(marsRover);
    }

    @Override
    public void drive(double distance) {
        if (distance <= 0.0) { // Invalid input handling
            this.earthComm.sendMessage("! Distance of " + distance + " is invalid.");
        } else if (distance > 0.0) {
            // Checking whether driving or not and updating the boolean
            if (!driving) {
                engineSystem.startDriving();
                driving = true;
            } else {
                this.rover.removeListener(listener); // Listener present in rover's drive event list so we remove it
            }

            // Listener will get updated for every time a new drive distance is updated
            listener = new DriveStatusListener() {
                /*Sum of total driven and new distance to drive*/
                private final double finalDistSummed = engineSystem.getDistanceDriven() + distance;
                private final double originalDistance = distance;
                @Override
                public void action() {
                    if (engineSystem.getDistanceDriven() >= finalDistSummed) { // Reached driving distance goal
                        engineSystem.stopDriving();
                        earthComm.sendMessage("D driven distance of " + originalDistance +
                                " and stopped driving.");
                        rover.setState(new Stopped(rover));
                        rover.removeListener(this); // Removing listener
                        driving = false; // update driving
                        listener = null;
                    }
                }
            };

            this.rover.addListener(listener);
        }
    }

    @Override
    public void turn(double angle) {
        // Valid turning from -180 to 180
        if (angle >= -180 && angle <= 180) {
            this.engineSystem.turn(angle);
        } else {
            earthComm.sendMessage("! Angle of " + angle + " is not in range");
        }
    }

    @Override
    public void soilAnalysis() {
//        throw new StateException("The rover cannot perform soil analysis when driving");
        earthComm.sendMessage("! Cannot perform soil analysis whilst driving.");
    }

}
