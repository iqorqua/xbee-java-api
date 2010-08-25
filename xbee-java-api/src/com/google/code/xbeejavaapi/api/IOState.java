/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.google.code.xbeejavaapi.api;

import com.google.code.xbeejavaapi.api.Constants.Analog_IO_Pin;
import com.google.code.xbeejavaapi.api.Constants.Digital_IO_Pin;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * @author David Miguel Antunes <davidmiguel [ at ] antunes.net>
 */
public class IOState {

    private static final Logger logger = Logger.getLogger(IOState.class);
    private HashMap<Digital_IO_Pin, Boolean> digitalIOState;
    private HashMap<Analog_IO_Pin, Double> analogIOState;

    public IOState(HashMap<Digital_IO_Pin, Boolean> digitalIOState, HashMap<Analog_IO_Pin, Double> analogIOState) {
        this.digitalIOState = digitalIOState;
        this.analogIOState = analogIOState;
    }

    public HashMap<Analog_IO_Pin, Double> getAnalogIOState() {
        return analogIOState;
    }

    public HashMap<Digital_IO_Pin, Boolean> getDigitalIOState() {
        return digitalIOState;
    }
}
