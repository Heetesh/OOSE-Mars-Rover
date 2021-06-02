package edu.curtin.comp2003.roverapi;

public class EngineSystem {
    /**
     * Begins driving forward. The effect is *not* to drive a fixed distance, but
     * to simply start driving. The rover will not stop until the stopDriving()
     * method is subsequently called.
     *
     * If startDriving() is called while the rover is already driving, it will
     * throw an exception.
     */

    private int distance = 0;
    public void startDriving() {
        // TODO: STUB
    }

    /**
     * Stops driving.
     *
     * If stopDriving() is called while the rover is already stopped, it will
     * throw an exception.
     */
    public void stopDriving() {
        // TODO: STUB
    }

    /**
     * Immediately turns the rover by the specified angle anticlockwise (negative
     * for clockwise).
     *
     * @param angle turn angle
     */
    public void turn(double angle) {
        // TODO: STUB
    }

    /**
     * Returns the total distance that the rover has ever driven, since it first
     * landed on Mars. This figure is never reset. It remains constant while the
     * rover is stopped, and increases while the rover is driving.
     * @return distance driven
     */
    public double getDistanceDriven() {
        distance = distance + 50;
        return distance;
    }
}
