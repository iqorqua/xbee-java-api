/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.google.code.xbeejavaapi;

import com.google.code.xbeejavaapi.exception.XBeeOperationFailedException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 *
 * @author David Miguel Antunes <davidmiguel [ at ] antunes.net>
 */
public class XBee {

    private static final Logger logger = Logger.getLogger(XBee.class);
    InputStream in;
    OutputStream out;

    public XBee(InputStream in, OutputStream out) {
        this.in = in;
        this.out = out;
    }

    public String getNodeIdentifier() throws XBeeOperationFailedException {
        try {
            String ni = "";

            write("+++");
            readLine();

            write("ATNI\r");
            ni = readLine();

            write("ATCN\r");
            readLine();

            return ni;
        } catch (IOException ex) {
            logger.error(ex);
            throw new XBeeOperationFailedException();
        }
    }

    public void setAPIMode() throws XBeeOperationFailedException {
        try {
            write("+++");
            readLine();

            write("ATAP1\r");

            write("ATCN\r");
            readLine();
        } catch (IOException ex) {
            logger.error(ex);
            throw new XBeeOperationFailedException();
        }
    }

    public void sendATCommand(ATCommand command) throws XBeeOperationFailedException {
        int[] data = new int[4 + command.getParameters().length];
        int i = 0;
        data[i++] = APIFrames.ATCommand.getValue();
        data[i++] = 0;
        data[i++] = (int) command.getCommand().charAt(0);
        data[i++] = (int) command.getCommand().charAt(1);
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

    public InputStream getIn() {
        return in;
    }

    public OutputStream getOut() {
        return out;
    }

    private void write(String s) throws IOException {
        logger.trace("Writting" + "\"" + s + "\"".replace("\r", "\\r"));
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
        char c = (char) in.read();
        while (c != '\r') {
            line += c;
            c = (char) in.read();
        }
        logger.trace("<" + line);
        return line;
    }
}
