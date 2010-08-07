/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.google.code.xbeejavaapi;

import org.apache.log4j.Logger;

/**
 *
 * @author David Miguel Antunes <davidmiguel [ at ] antunes.net>
 */
public class ATCommandFactory {

    private static final Logger logger = Logger.getLogger(ATCommandFactory.class);

    public enum D2LineState {

        DISABLED(0),
        ANALOG_INPUT_SINGLE_ENDED(2),
        DIGITAL_INPUT_MONITORED(3),
        DIGITAL_OUTPUT_LOW(4),
        DIGITAL_OUTPUT_HIGH(5);
        private int value;

        private D2LineState(int val) {
            this.value = val;
        }

        public int getValue() {
            return value;
        }
    }

    public ATCommand d2(D2LineState lineState) {
        return new ATCommand("D2", new int[]{lineState.getValue()});
    }
}
