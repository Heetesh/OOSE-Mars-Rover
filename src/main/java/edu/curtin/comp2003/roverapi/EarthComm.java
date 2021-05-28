package edu.curtin.comp2003.roverapi;

public class EarthComm {
    /**
     * Return the next command received from Earth, or null if no further command
     * has been received. If multiple commands arrive in between calls to
     * pollCommand(), they will be buffered (stored temporarily), and subsequent
     * calls to pollCommand() will return one command at a time, in the order of
     * arrival.
     *
     * There is no risk of commands being lost. But pollCommand() *will not wait*
     * for a command to be received if none has been yet.
     */
    public String pollCommand() {
        // TODO: STUB
        return null;
    }

    /** Sends a return message to Earth. */
    public void sendMessage(String msg) {
        // TODO: STUB
    }
}
