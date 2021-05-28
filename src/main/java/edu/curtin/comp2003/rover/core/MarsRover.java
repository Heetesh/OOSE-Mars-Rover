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

public class MarsRover implements PollCommandListener {

    private RoverState state = new Stopped(this); // Starting state => Stopped
    private EarthComm earthComm;
    private EngineSystem engineSystem;
    private Sensors sensors;
    private SoilAnalyser soilAnalyser;

    // Event listeners
    private List<EventListener> listeners = new ArrayList<>();
    private Map<String, CommandStrategy> strategy = new HashMap<>();


    /**
     * Mars rover constructor
     * @param engine engine API
     * @param sensors sensor API
     * @param soilAnalyser soil analyser API
     */
    public MarsRover(EarthComm earthComm,EngineSystem engine, Sensors sensors, SoilAnalyser soilAnalyser)
    {
        setComponents(earthComm, engine,sensors,soilAnalyser);

        strategy.put("D", new DriveCommand(this));
        strategy.put("T", new TurnCommand(this));
        strategy.put("P", new PhotoCommand(this));
        strategy.put("E", new EnvironmentStatusCommand(this));
        strategy.put("S", new SoilAnalysisCommand(this));
    }

    /**
     * Sets components
     * @param engine engine API
     * @param sensors sensor API
     * @param soilAnalyser soil analyser API
     */
    private void setComponents(EarthComm earthComm,EngineSystem engine, Sensors sensors, SoilAnalyser soilAnalyser) {
        // If statement checking if all clause to evaluate to true otherwise throw exception
        if (!(engine != null && sensors != null && soilAnalyser != null)) {
            throw new RoverException("One of more core components failed setting up!");
        } else { // All components valid
            this.earthComm = earthComm;
            this.engineSystem = engine;
            this.sensors = sensors;
            this.soilAnalyser = soilAnalyser;
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

    // State call implementation

    public void drive(double distance) {
        this.state.drive(distance);
    }

    public void turn(double angle) {
        this.state.turn(angle);
    }

    public void takePhoto() {
        this.state.takePhoto();
    }

    public void environmentStatus() {
        this.state.environmentStatus();
    }

    public void soilAnalysis() {
        this.state.soilAnalysis();
    }

    /**
     * Sets the new state
     * @param state the new state
     */
    public void setState(RoverState state) {

    }

    public void addListener(EventListener listener) {
        if (listener != null) {
            this.listeners.add(listener);
        }
    }

    public void removeListener(EventListener listener) {
        this.listeners.remove(listener);
    }

    public void notifyListeners() {
        for (EventListener listener: this.listeners) {
            listener.action();
        }
    }

    @Override
    public void action() {
        String command = earthComm.pollCommand();
        String[] line;

        if(command != null) {
            line = command.split(" ");
            performCommandAction(line);
        }
    }

    private void performCommandAction(String... line) {
        if(strategy.containsKey(line[0])) {
            strategy.get(line[0]).action(line);
        } else {
            earthComm.sendMessage("! Entered wrong command: " + Arrays.toString(line));
        }
    }


}
