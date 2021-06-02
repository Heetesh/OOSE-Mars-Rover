package edu.curtin.comp2003.rover.core;

import edu.curtin.comp2003.rover.commandStrategy.*;
import edu.curtin.comp2003.rover.events.EventListener;
import edu.curtin.comp2003.rover.events.PollCommandListener;
import edu.curtin.comp2003.rover.exceptions.RoverException;
import edu.curtin.comp2003.roverapi.EarthComm;
import edu.curtin.comp2003.roverapi.EngineSystem;
import edu.curtin.comp2003.roverapi.Sensors;
import edu.curtin.comp2003.roverapi.SoilAnalyser;
import edu.curtin.comp2003.rover.state.Stopped;
import edu.curtin.comp2003.rover.state.RoverState;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Mars rover core class representing the rover's software.
 * @author Heetesh Doorbiz
 */
public class MarsRover {

    private RoverState state;
    private EarthComm earthComm;
    private EngineSystem engineSystem;
    private Sensors sensors;
    private SoilAnalyser soilAnalyser;

    /** List of listeners */
    private List<EventListener> listeners = new CopyOnWriteArrayList<>();
    // Using CopyOnWriteArrayList to bypass concurrent modification exception
    // Around similar time complexity as array list since array to be copied is relatively small.


    /** Map of strategy objects */
    private final Map<String, CommandStrategy> strategy = new HashMap<>();

    /**
     * Mars rover constructor
     * @param earthComm earth communication API
     * @param engine engine API
     * @param sensors sensor API
     * @param soilAnalyser soil analyser API
     */
    public MarsRover(EarthComm earthComm,EngineSystem engine, Sensors sensors, SoilAnalyser soilAnalyser)
    {
        this.state = new Stopped(this); // starting state;
        setComponents(earthComm, engine,sensors,soilAnalyser);

        strategy.put("D", new DriveCommand(this));
        strategy.put("T", new TurnCommand(this));
        strategy.put("P", new PhotoCommand(this));
        strategy.put("E", new EnvironmentStatusCommand(this));
        strategy.put("S", new SoilAnalysisCommand(this));
    }

    /**
     * Sets components
     * @param earthComm earth communication API
     * @param engine engine API
     * @param sensors sensor API
     * @param soilAnalyser soil analyser API
     */
    private void setComponents(EarthComm earthComm,EngineSystem engine, Sensors sensors, SoilAnalyser soilAnalyser) {
        // If statement checking if all clause to evaluate to true otherwise throw exception
        if (!(earthComm != null && engine != null && sensors != null && soilAnalyser != null)) {
            throw new RoverException("One of more core components failed setting up!");
        } else { // All components valid
            this.earthComm = earthComm;
            this.engineSystem = engine;
            this.sensors = sensors;
            this.soilAnalyser = soilAnalyser;

            // Poll command listener
            this.addListener((PollCommandListener) () -> {
                String command = earthComm.pollCommand();
                String[] line;

                if(command != null) {
                    line = command.split(" ");
                    performCommandAction(line);
                }
            });

            this.addListener(() -> {
                double visibility = sensors.readVisibility();
                if(visibility < 4.0 || visibility > 5.0) {
                    state.environmentStatus(); // Calling state's method to message EnvironmentStatus
                }
            });
        }
    }


    /**
     * Gets earth communication link
     * @return EarthComm object
     */
    public EarthComm getEarthComm() {
        return this.earthComm;
    }

    /**
     * Gets engine system
     * @return EngineSystem object
     */
    public EngineSystem getEngineSystem() {
        return this.engineSystem;
    }

    /**
     * Gets sensors
     * @return Sensor object
     */
    public Sensors getSensors() {
        return this.sensors;
    }

    /**
     * Gets soil analyser
     * @return SoilAnalyser object
     */
    public SoilAnalyser getSoilAnalyser() {
        return this.soilAnalyser;
    }

    // State calls implementation

    /**
     * Starts driving
     * @param distance distance to drive
     */
    public void drive(double distance) {
        this.state.drive(distance);
    }

    /**
     * Turn itself
     * @param angle angle to turn
     */
    public void turn(double angle) {
        this.state.turn(angle);
    }

    /**
     * Takes a photo
     */
    public void takePhoto() {
        this.state.takePhoto();
    }

    /**
     * Performs environment status
     */
    public void environmentStatus() {
        this.state.environmentStatus();
    }

    /**
     * Performs soil chemistry analysis
     */
    public void soilAnalysis() {
        this.state.soilAnalysis();
    }

    /**
     * Sets the new state
     * @param state the new state
     */
    public void setState(RoverState state) {
        this.state = state;
    }

    /**
     * Adds a listener
     * @param listener listener instance
     */
    public void addListener(EventListener listener) {
        /* Listener not null and listeners collection doesn't contain param listener*/

        if (listener != null && !(this.listeners.contains(listener))) {
            this.listeners.add(listener);
        }
    }

    /**
     * Removes the listener
     * @param listener listener to remove
     */
    public void removeListener(EventListener listener) {
        for (EventListener eventListener : this.listeners) {
            if (eventListener.equals(listener)) {
                this.listeners.remove(listener);
            }
        }
    }

    /**
     * Notifies listeners
     */
    public void notifyListeners() {
        for (EventListener listener : this.listeners) {
            listener.action();
        }
    }

    /**
     * Handles command from earth communications.
     * @param line commands
     */
    private void performCommandAction(String... line) {
        if (line != null) {
            if (strategy.containsKey(line[0])) {
                strategy.get(line[0]).action(line);
            } else {
                earthComm.sendMessage("! Entered wrong command: " + Arrays.toString(line).trim());
            }
        }
    }

}
