package edu.curtin.comp2003.rover.state;

import edu.curtin.comp2003.rover.core.MarsRover;

/**
 * Class representing the stopped state of the mars rover.
 * @see MarsRover Mars Rover
 * @author Heetesh Doorbiz
 */
public class Stopped extends RoverState {

    public Stopped(MarsRover marsRover) {
        super(marsRover);
    }

    @Override
    public void drive(double distance) {
        if(distance > 0.0) {
            this.rover.setState(new Driving(this.rover));
            this.rover.drive(distance); // drive() called in Driving state
        } else {
//            throw new StateException("Distance for drive is less than 0");
            earthComm.sendMessage("! Distance of " + distance + "provided is incorrect.");
        }
    }

    @Override
    public void turn(double angle) {
        if(angle >= -180 && angle <= 180) {
            this.engineSystem.turn(angle);
        } else {
            earthComm.sendMessage("! Angle of " + angle + " is not in range");
        }
    }

    @Override
    public void soilAnalysis() {
        this.rover.setState(new Analysis(this.rover));
        this.rover.soilAnalysis();
    }

}
