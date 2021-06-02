package edu.curtin.comp2003.roverapi;

import java.util.Base64;

public class Sensors
{
    /**
     * Performs a temperature reading and returns the result in °C.
     * @return temperature value
     */
    public double readTemperature() {
        // TODO: STUB
        return 10.0;
    }

    /**
     * Performs a visibility reading and returns the result in km.
     * @return visibility value
     */
    public double readVisibility() {
        double random = Math.random();
        if(Math.random() > 5.0) {
            return 3.9;
        } else {
            return 5.1;
        }
    }

    /**
     * Performs a light-level reading, and returns the result in lux (units).
     * @return light level value
     */
    public double readLightLevel() {
        // TODO: STUB
        return 1000;
    }

    /**
     * Takes a photo and returns the binary data making up the image.
     * @return byte array of photo
     */
    public byte[] takePhoto() {
        // TODO: STUB
        return "new photo taken".getBytes();
    }
}
