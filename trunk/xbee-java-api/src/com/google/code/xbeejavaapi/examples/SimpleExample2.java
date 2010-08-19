/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.google.code.xbeejavaapi.examples;

import com.google.code.xbeejavaapi.api.ATCommandPayloadFactory;
import com.google.code.xbeejavaapi.LocalXBee;
import com.google.code.xbeejavaapi.XBeeFactory;
import com.google.code.xbeejavaapi.api.ATCommandResponse;
import com.google.code.xbeejavaapi.exception.XBeeOperationFailedException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author David Miguel Antunes <davidmiguel [ at ] antunes.net>
 */
public class SimpleExample2 {

    private static final Logger logger = Logger.getLogger(SimpleExample2.class);

    public static void main(String[] args) throws XBeeOperationFailedException {
        BasicConfigurator.configure();
        Logger.getRootLogger().setLevel(Level.ALL);

        LocalXBee xbee = new XBeeFactory("/dev/ttyUSB0").newXBee();

        System.out.println("Setting ID=0xFFFF");
        xbee.sendATCommand(new ATCommandPayloadFactory().setID(0xFFFF));
        System.out.println("Quering ID");
        xbee.sendATCommand(new ATCommandPayloadFactory().queryID());
        System.out.println("Setting ID=1");
        xbee.sendATCommand(new ATCommandPayloadFactory().setID(1));
        System.out.println("Quering ID");
        xbee.sendATCommand(new ATCommandPayloadFactory().queryID());
        xbee.disconnect();
    }
}
