/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.google.code.xbeejavaapi;

import com.google.code.xbeejavaapi.api.ATCommandRequest;
import com.google.code.xbeejavaapi.api.APIFrame;
import com.google.code.xbeejavaapi.api.TransmitStatusFactory;
import com.google.code.xbeejavaapi.exception.ChecksumFailedException;
import com.google.code.xbeejavaapi.exception.XBeeOperationFailedException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 *
 * @author David Miguel Antunes <davidmiguel [ at ] antunes.net>
 */
public class XBee {

    private static final Logger logger = Logger.getLogger(XBee.class);
    InputStream in;
    OutputStream out;
    Map<Integer, int[]> messages = new HashMap<Integer, int[]>();

    public XBee(InputStream in, OutputStream out) throws XBeeOperationFailedException {
        this.in = in;
        this.out = out;
        setAPIMode();
        FrameListener listener = new FrameListener();
        listener.start();
    }

    private void setAPIMode() throws XBeeOperationFailedException {
        try {
            write("+++");
            readLine();

            write("ATAP1\r");
            readLine();

            write("ATCN\r");
            readLine();
        } catch (IOException ex) {
            logger.error(ex);
            throw new XBeeOperationFailedException();
        }
    }

    public void sendATCommand(ATCommandRequest command) throws XBeeOperationFailedException {
        int[] data = new int[4 + command.getParameters().length];
        int i = 0;
        data[i++] = APIFrame.ATCommand.getValue();
        data[i++] = 1;
        data[i++] = (int) command.getCommand().getCommand().charAt(0);
        data[i++] = (int) command.getCommand().getCommand().charAt(1);
        for (int j = 0; j < command.getParameters().length; j++) {
            data[i++] = command.getParameters()[j];
        }
        sendFrame(data);
    }

    private void sendFrame(int[] data) throws XBeeOperationFailedException {
        try {
            write(0x7E);
            write(data.length >> 8);
            write(data.length);
            write(data);

            int sum = 0;
            for (int i = 0; i < data.length; i++) {
                sum += data[i];
            }

            write(0xFF - sum);

        } catch (IOException ex) {
            logger.error(ex);
            throw new XBeeOperationFailedException();
        }

    }

    /**
     * Disconnects this instance from hardware (normally a serial port).
     * No other method may be called after this one.
     */
    public void disconnect() {
        try {
            in.close();
            out.close();
        } catch (IOException ex) {
            logger.error(ex);
        }
    }

    private void write(String s) throws IOException {
        logger.trace("Writting: " + "\"" + s + "\"".replace("\r", "\\r"));
        for (int i = 0; i < s.toCharArray().length; i++) {
            char c = s.toCharArray()[i];
            write(c);
        }
    }

    private void write(int[] data) throws IOException {
        for (int j = 0; j < data.length; j++) {
            write(data[j]);
        }
    }

    private void write(int i) throws IOException {
        logger.trace(">" + "0x" + Integer.toHexString(i).toUpperCase() + " '" + (char) i + "'");
        out.write(i);
        out.flush();
    }

    private String readLine() throws IOException {
        String line = "";
        char c = (char) read();
        while (c != '\r') {
            line += c;
            c = (char) read();
        }
        logger.trace("Read line: \"" + line + "\"");
        return line;
    }

    private int read() throws IOException {
        int b = in.read();
        logger.trace("<" + "0x" + Integer.toHexString(b).toUpperCase() + " '" + (char) b + "'");
        return b;
    }

    class FrameListener extends Thread {

        @Override
        public void run() {
            try {
                logger.debug("Started frameListener");
                while (true) {
                    int b = read();
                    int[] data = receiveFrame();

                    String debug = "Received API Frame (" + APIFrame.get(data[0]).toString() + "): ";
                    debug += data[0];
                    for (int i = 1; i < data.length; i++) {
                        debug += (char) data[i];
                    }
                    logger.trace(debug);

                    Object frame = null;
                    switch (APIFrame.get(data[0])) {
                        case TransmitStatus:
                            frame = new TransmitStatusFactory().parse(data);
                            break;
                    }
                    System.out.println("API Frame: " + frame);
                }
            } catch (XBeeOperationFailedException ex) {
                logger.error(ex);
            } catch (ChecksumFailedException ex) {
                logger.error(ex);
            } catch (IOException ex) {
                logger.error(ex);
            }
        }

        /**
         * Returns frame data (bytes 4-n, see XBee®/XBee-PRO® DigiMeshTM 2.4 RF Modules Manual)
         */
        private int[] receiveFrame() throws XBeeOperationFailedException, ChecksumFailedException, IOException {
            int length;
            int length1 = read();
            int length2 = read();
            length = (length1 << 8) | length2;

            int[] data = new int[length];

            for (int i = 0; i < length; i++) {
                data[i] = read();
            }

            int checksum = 0;
            for (int i = 0; i < data.length; i++) {
                checksum += data[i];
            }

            int receivedChecksum = read();

            checksum += receivedChecksum;

            if (checksum % 256 != 0xFF) {
                throw new ChecksumFailedException();
            }

            return data;
        }
    }
}
