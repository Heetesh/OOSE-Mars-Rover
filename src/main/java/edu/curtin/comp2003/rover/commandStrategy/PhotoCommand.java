package edu.curtin.comp2003.rover.commandStrategy;

import edu.curtin.comp2003.rover.core.MarsRover;

public class PhotoCommand implements CommandStrategy {

    private MarsRover rover;

    public PhotoCommand(MarsRover rover) {
        this.rover = rover;
    }


    @Override
    public void action(String[] s) {
        this.rover.takePhoto();
    }

}
