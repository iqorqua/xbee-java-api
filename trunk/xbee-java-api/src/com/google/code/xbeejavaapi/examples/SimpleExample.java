/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.google.code.xbeejavaapi.examples;

import com.google.code.xbeejavaapi.ATCommandFactory;
import com.google.code.xbeejavaapi.XBee;
import com.google.code.xbeejavaapi.XBeeFactory;
import com.google.code.xbeejavaapi.exception.XBeeOperationFailedException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author David Miguel Antunes <davidmiguel [ at ] antunes.net>
 */
public class SimpleExample {

    private static final Logger logger = Logger.getLogger(SimpleExample.class);

    public static void main(String[] args) throws XBeeOperationFailedException {
        BasicConfigurator.configure();
        Logger.getRootLogger().setLevel(Level.ALL);

        XBee xbee = new XBeeFactory("/dev/ttyUSB0").newXBee();

        xbee.setAPIMode();
        xbee.sendATCommand(new ATCommandFactory().d2(ATCommandFactory.D2LineState.DIGITAL_OUTPUT_LOW));
    }
}
