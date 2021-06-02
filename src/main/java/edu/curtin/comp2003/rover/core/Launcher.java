package edu.curtin.comp2003.rover.core;

import edu.curtin.comp2003.roverapi.EarthComm;
import edu.curtin.comp2003.roverapi.EngineSystem;
import edu.curtin.comp2003.roverapi.Sensors;
import edu.curtin.comp2003.roverapi.SoilAnalyser;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Launcher class for the mars rover software
 * @see MarsRover Mars Rover
 * @author Heetesh Doorbiz
 */
public class Launcher {
    public static void main(String[] args) {

        Timer timer = new Timer();

        EarthComm earthComm = new EarthComm();
        EngineSystem engineSystem = new EngineSystem();
        Sensors sensors = new Sensors();
        SoilAnalyser soilAnalyser = new SoilAnalyser();

        MarsRover rover = new MarsRover(earthComm, engineSystem, sensors,soilAnalyser);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                rover.notifyListeners();
            }
        };

        timer.scheduleAtFixedRate(
                task, // Task to run
                0, // Start once code runs
                1000 // Repeated after every 1 seconds
        );

    }
}
