/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.google.code.xbeejavaapi.examples;

import com.google.code.xbeejavaapi.api.ATCommandPayloadFactory;
import com.google.code.xbeejavaapi.LocalXBee;
import com.google.code.xbeejavaapi.XBeeFactory;
import com.google.code.xbeejavaapi.api.ATCommandResponse;
import com.google.code.xbeejavaapi.api.XBeeAddress;
import com.google.code.xbeejavaapi.exception.XBeeOperationFailedException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author David Miguel Antunes <davidmiguel [ at ] antunes.net>
 */
public class SimpleExample3 {

    private static final Logger logger = Logger.getLogger(SimpleExample3.class);

    public static void main(String[] args) throws XBeeOperationFailedException {
        BasicConfigurator.configure();
        Logger.getRootLogger().setLevel(Level.ALL);

        LocalXBee xbee = new XBeeFactory("/dev/ttyUSB0").newXBee();

        xbee.sendATCommand(new ATCommandPayloadFactory().setNT(0x20));

        xbee.sendATCommand(new ATCommandPayloadFactory().ND());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
        }

        xbee.disconnect();
    }
}
