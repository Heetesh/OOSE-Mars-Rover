package edu.curtin.comp2003.rover.state;

import edu.curtin.comp2003.rover.core.MarsRover;
import edu.curtin.comp2003.roverapi.EarthComm;
import edu.curtin.comp2003.roverapi.EngineSystem;
import edu.curtin.comp2003.roverapi.Sensors;
import edu.curtin.comp2003.roverapi.SoilAnalyser;

import java.util.Base64;

/**
 * Abstraction of mars rover's state features.
 *
 * @see MarsRover Mars rover
 * @author Heetesh Doorbiz
 */
public abstract class RoverState {

    /** Mars rover reference */
    protected MarsRover rover;

    /** Earth communications API reference*/
    protected EarthComm earthComm;
    /** Engine system API reference */
    protected EngineSystem engineSystem;
    /** Sensors API reference */
    protected Sensors sensors;
    /** Soil analyser API reference */
    protected SoilAnalyser soilAnalyser;


    /**
     * RoverState general constructor
     * @param marsRover mars rover instance
     */
    public RoverState(MarsRover marsRover) {
        this.rover = marsRover;
        this.earthComm = this.rover.getEarthComm();
        this.engineSystem = this.rover.getEngineSystem();
        this.sensors = this.rover.getSensors();
        this.soilAnalyser = this.rover.getSoilAnalyser();
    }

    /**
     * Abstraction drive feature in the mars rover
     * @param distance distance to drive
     * @see MarsRover#drive(double)
     */
    public abstract void drive(double distance);

    /**
     * Abstraction of turn feature
     * @param angle angle to turn
     */
    public abstract void turn(double angle);

    /**
     * Takes a photo from the Mars rover optics.
     * @see  MarsRover#takePhoto()
     */
    public void takePhoto() {
        String encodedPhoto = Base64.getEncoder().encodeToString(this.sensors.takePhoto());
        this.earthComm.sendMessage("P " + encodedPhoto);
    }

    /**
     * Reports environment status
     */
    public void environmentStatus() {
        // Return temperature, visibility and ambient light in order
        double temperature;
        double visibility;
        double ambientLight;

        temperature = this.sensors.readTemperature();
        visibility = this.sensors.readVisibility();
        ambientLight = this.sensors.readLightLevel();

        String envStatus = temperature + " " + visibility + " " + ambientLight;

        this.earthComm.sendMessage("E " + envStatus);

    }

    /**
     * Abstraction feature of performing soil analysis
     */
    public abstract void soilAnalysis();

}
