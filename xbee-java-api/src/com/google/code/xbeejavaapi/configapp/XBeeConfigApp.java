/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.google.code.xbeejavaapi.configapp;

import com.google.code.xbeejavaapi.api.exception.XBeeOperationFailedException;
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
        Logger.getRootLogger().setLevel(Level.TRACE);
        try {
            if (args.length > 0) {
                Logger.getRootLogger().setLevel(Level.toLevel(args[0]));
            }
        } catch (Exception e) {
            logger.info(e);
        }
        JTabbedPane localNodesPane = new JTabbedPane();
        MainFrame jFrame = new MainFrame(localNodesPane);

        jFrame.getMainPanel().add(localNodesPane);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
