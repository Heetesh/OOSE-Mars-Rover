package edu.curtin.comp2003.rover.state;

import edu.curtin.comp2003.rover.core.MarsRover;
import edu.curtin.comp2003.roverapi.EarthComm;
import edu.curtin.comp2003.roverapi.EngineSystem;
import edu.curtin.comp2003.roverapi.Sensors;
import edu.curtin.comp2003.roverapi.SoilAnalyser;

import java.util.Base64;

/**
 * @author Heetesh Doorbiz
 *
 * Class representing the mars rover state on a high level abstraction
 */
public abstract class RoverState {

    protected MarsRover rover;

    // API Components
    protected EarthComm earthComm;
    protected EngineSystem engineSystem;
    protected Sensors sensors;
    protected SoilAnalyser soilAnalyser;


    public RoverState(MarsRover marsRover) {
        this.rover = marsRover;
        this.earthComm = this.rover.getEarthComm();
        this.engineSystem = this.rover.getEngineSystem();
        this.sensors = this.rover.getSensors();
        this.soilAnalyser = this.rover.getSoilAnalyser();
    }

    public abstract void drive(double distance);

    public abstract void turn(double angle);

    /**
     * Takes a photo from the Mars rover optics.
     */
    public final void takePhoto() {
        String encodedPhoto = Base64.getEncoder().encodeToString(this.sensors.takePhoto());
        this.earthComm.sendMessage("P " + encodedPhoto);
    }

    /**
     * Reports environment status
     */
    public final void environmentStatus() {
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

    public abstract void soilAnalysis();

}
