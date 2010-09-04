/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.google.code.xbeejavaapi.examples;

import com.google.code.xbeejavaapi.api.XBee;
import com.google.code.xbeejavaapi.api.XBeeFactory;
import com.google.code.xbeejavaapi.api.exception.XBeeOperationFailedException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author David Miguel Antunes <davidmiguel [ at ] antunes.net>
 */
public class SimpleExample1 {

    private static final Logger logger = Logger.getLogger(SimpleExample1.class);

    public static void main(String[] args) throws XBeeOperationFailedException {
        BasicConfigurator.configure();
        Logger.getRootLogger().setLevel(Level.ERROR);

        XBee xbee = new XBeeFactory("/dev/ttyUSB0").newXBee();

        System.out.println("Node identifier is \"" + xbee.getNodeIdentifier() + "\"");

        xbee.disconnect();
    }
}
