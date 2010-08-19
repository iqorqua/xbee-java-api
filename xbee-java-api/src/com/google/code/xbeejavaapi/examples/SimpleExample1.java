/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.google.code.xbeejavaapi.examples;

import com.google.code.xbeejavaapi.api.ATCommandPayloadFactory;
import com.google.code.xbeejavaapi.LocalXBee;
import com.google.code.xbeejavaapi.XBeeFactory;
import com.google.code.xbeejavaapi.api.Constants.AD2_DIO2_Configuration;
import com.google.code.xbeejavaapi.exception.XBeeOperationFailedException;
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

        LocalXBee xbee = new XBeeFactory("/dev/ttyUSB0").newXBee();

        String oldNodeIdentifier = xbee.getNodeIdentifier();

        System.out.println("Old node identifier is \"" + oldNodeIdentifier + "\"");

        xbee.setNodeIdentifier("NODE1");

        System.out.println("Setting new node identifier");

        System.out.println("Current node identifier is \"" + xbee.getNodeIdentifier() + "\"");

        xbee.setNodeIdentifier(oldNodeIdentifier);

        System.out.println("Reverted to old node identifier");

        xbee.disconnect();
    }
}
