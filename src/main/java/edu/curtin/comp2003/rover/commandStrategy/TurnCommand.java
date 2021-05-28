package edu.curtin.comp2003.rover.commandStrategy;

import edu.curtin.comp2003.rover.core.MarsRover;

public class TurnCommand implements CommandStrategy {

    private MarsRover rover;

    public TurnCommand(MarsRover rover) {
        this.rover = rover;
    }


    @Override
    public void action(String[] s) {
        this.rover.turn(Double.parseDouble(s[1]));
    }

}
