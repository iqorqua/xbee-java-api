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
        Logger.getRootLogger().setLevel(Level.ALL);

        LocalXBee xbee = new XBeeFactory("/dev/ttyUSB0").newXBee();

        xbee.sendATCommand(new ATCommandPayloadFactory().setD2(AD2_DIO2_Configuration.DIGITAL_OUTPUT_DEFAULT_LOW));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(SimpleExample1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        xbee.sendATCommand(new ATCommandPayloadFactory().setD2(AD2_DIO2_Configuration.DIGITAL_OUTPUT_DEFAULT_HIGH));

        System.out.println("Quering D2");

        xbee.sendATCommand(new ATCommandPayloadFactory().queryD2());

        xbee.disconnect();
    }
}
