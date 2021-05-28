package edu.curtin.comp2003.rover.commandStrategy;

import edu.curtin.comp2003.rover.core.MarsRover;

public class DriveCommand implements CommandStrategy {
    private MarsRover rover;

    public DriveCommand(MarsRover rover) {
        this.rover = rover;
    }
    @Override
    public void action(String[] s) {
        this.rover.drive(Double.parseDouble(s[1]));
    }
}
