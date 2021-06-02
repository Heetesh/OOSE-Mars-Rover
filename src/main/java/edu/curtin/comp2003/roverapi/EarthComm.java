package edu.curtin.comp2003.roverapi;

import java.util.LinkedList;
import java.util.Queue;

public class EarthComm {

    private Queue<String> commands = new LinkedList<>();

    public EarthComm() {
        commands.add("D 10.0");
        commands.add("D 40.0");
        commands.add("T -188.0");
        commands.add("T 188.0");
        commands.add("T 75.0");
        commands.add("S");
        for (int ii = 0; ii <= 10; ii++) {
            commands.add("S");
            commands.add("E");
        }

    }
    /**
     * Return the next command received from Earth, or null if no further command
     * has been received. If multiple commands arrive in between calls to
     * pollCommand(), they will be buffered (stored temporarily), and subsequent
     * calls to pollCommand() will return one command at a time, in the order of
     * arrival.
     *
     * There is no risk of commands being lost. But pollCommand() *will not wait*
     * for a command to be received if none has been yet.
     *
     * @return command from earth
     */
    public String pollCommand() {
        if (commands.size() > 0 ) {
            return commands.remove();
        } else {
            return null;
        }
    }

    /**
     * Sends a return message to Earth.
     * @param msg message to be sent
     */
    public void sendMessage(String msg) {
        System.out.println(msg);
    }
}
