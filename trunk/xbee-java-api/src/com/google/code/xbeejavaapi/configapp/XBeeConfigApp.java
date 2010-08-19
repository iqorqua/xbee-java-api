/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.google.code.xbeejavaapi.configapp;

import com.google.code.xbeejavaapi.LocalXBee;
import com.google.code.xbeejavaapi.XBeeFactory;
import com.google.code.xbeejavaapi.exception.XBeeOperationFailedException;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author David Miguel Antunes <davidmiguel [ at ] antunes.net>
 */
public class XBeeConfigApp {

    private static final Logger logger = Logger.getLogger(XBeeConfigApp.class);

    public static void main(String[] args) throws XBeeOperationFailedException {
        BasicConfigurator.configure();
        Logger.getRootLogger().setLevel(Level.ALL);

        LocalXBee xbee = new XBeeFactory("/dev/ttyUSB0").newXBee();

        JFrame jFrame = new JFrame();
        JTabbedPane jTabbedPane = new JTabbedPane();

        ParametersConfig valuesDisplay = new ParametersConfig(xbee, jFrame);
        jTabbedPane.addTab("Parameters", valuesDisplay);
        NodeDiscovery nodeDiscovery = new NodeDiscovery(xbee, jFrame, jTabbedPane);
        jTabbedPane.addTab("Node Discovery", nodeDiscovery);

        jFrame.getContentPane().add(jTabbedPane);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
