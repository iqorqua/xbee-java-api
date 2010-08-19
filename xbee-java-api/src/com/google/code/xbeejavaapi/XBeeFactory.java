/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.google.code.xbeejavaapi;

import com.google.code.xbeejavaapi.exception.XBeeOperationFailedException;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.log4j.Logger;

/**
 *
 * @author David Miguel Antunes <davidmiguel [ at ] antunes.net>
 */
public class XBeeFactory {

    private static final Logger logger = Logger.getLogger(XBeeFactory.class);
    private String serialPortName;

    public XBeeFactory(String serialPortName) {
        this.serialPortName = serialPortName;
    }

    public LocalXBee newXBee() throws XBeeOperationFailedException {
        {
            logger.debug("Creating XBee at port " + serialPortName);
            try {
                CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(serialPortName);
                if (portIdentifier.isCurrentlyOwned()) {
                    logger.error("Serial port " + serialPortName + " is currently in use");
                }
                CommPort commPort = portIdentifier.open(this.getClass().getName(), 2000);
                if (commPort instanceof SerialPort) {
                    final SerialPort serialPort = (SerialPort) commPort;
                    serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                    InputStream in = serialPort.getInputStream();
                    OutputStream out = serialPort.getOutputStream();
                    return new LocalXBee(in, out) {

                        @Override
                        public void disconnect() {
                            super.disconnect();
                            serialPort.close();
                        }
                    };
                } else {
                    logger.error(serialPortName + " is not a serial port");
                }
            } catch (NoSuchPortException ex) {
                logger.error(ex);
            } catch (IOException ex) {
                logger.error(ex);
            } catch (UnsupportedCommOperationException ex) {
                logger.error(ex);
            } catch (PortInUseException ex) {
                logger.error(ex);
            }
            return null;
        }
    }
}
