package edu.curtin.comp2003.rover.state;

import edu.curtin.comp2003.rover.core.MarsRover;
import edu.curtin.comp2003.rover.events.AnalysisListener;

import java.util.Base64;

/**
 * Class representing the analysis state of the mars rover.
 * @see MarsRover Mars Rover
 * @author Heetesh Doorbiz
 */
public class Analysis extends RoverState {
    private boolean analysing = false;

    public Analysis(MarsRover marsRover) {
        super(marsRover);
    }

    @Override
    public void drive(double distance) {
        this.earthComm.sendMessage("! Cannot drive when performing analysis");
    }

    @Override
    public void turn(double angle) {
        this.earthComm.sendMessage("! Cannot turn when driving");
    }

    @Override
    public void soilAnalysis() {
        if (!analysing) {
            analysing = true; // Starting analysis
            this.soilAnalyser.startAnalysis();
            // Using a listener to check analysis message poll
            this.rover.addListener(new AnalysisListener() {
                @Override
                public void action() {
                    byte[] rawResults = rover.getSoilAnalyser().pollAnalysis();
                    if (rawResults != null) { // IF message received
                        String encodedMessage = Base64.getEncoder().encodeToString(rawResults);
                        earthComm.sendMessage("S " + encodedMessage);
                        rover.setState(new Stopped(rover)); // Analysis is done
                        rover.removeListener(this); // removes self from listener
                        analysing = false;
                    }
                }
            });
        } else {
            earthComm.sendMessage("! Already performing soil chemistry analysis");
        }
    }

}
