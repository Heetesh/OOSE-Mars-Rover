package edu.curtin.comp2003.rover.commandStrategy;

import edu.curtin.comp2003.rover.core.MarsRover;

public class EnvironmentStatusCommand implements CommandStrategy{

    private MarsRover rover;

    public EnvironmentStatusCommand(MarsRover rover) {
        this.rover = rover;
    }

    @Override
    public void action(String[] type) {
        this.rover.environmentStatus();
    }

}
