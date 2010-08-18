/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.google.code.xbeejavaapi;

import com.google.code.xbeejavaapi.api.ATCommandRequest;
import com.google.code.xbeejavaapi.api.APIFrameType;
import com.google.code.xbeejavaapi.api.ATCommandPayloadFactory;
import com.google.code.xbeejavaapi.api.ATCommandResponse;
import com.google.code.xbeejavaapi.api.ATCommandResponseFactory;
import com.google.code.xbeejavaapi.api.Constants.*;
import com.google.code.xbeejavaapi.api.Frame;
import com.google.code.xbeejavaapi.api.FrameWithID;
import com.google.code.xbeejavaapi.api.TransmitStatusFactory;
import com.google.code.xbeejavaapi.api.XBeeAddress;
import com.google.code.xbeejavaapi.api.XBeeSerialNumber;
import com.google.code.xbeejavaapi.exception.ChecksumFailedException;
import com.google.code.xbeejavaapi.exception.XBeeOperationFailedException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.Logger;

/**
 *
 * @author David Miguel Antunes <davidmiguel [ at ] antunes.net>
 */
public class XBee {

    private static final Logger logger = Logger.getLogger(XBee.class);
    private InputStream in;
    private OutputStream out;
    private Map<Integer, int[]> messages = new HashMap<Integer, int[]>();
    private int frameIDGenerator = 1;
    private FrameListener listener;

    public XBee(InputStream in, OutputStream out) throws XBeeOperationFailedException {
        this.in = in;
        this.out = out;
        setAPIMode();
        listener = new FrameListener();
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


    /* Special */
    public void write() throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().WR());
        ATCommandResponse.WR resp1 = listener.getResponse(frameID);
    }

    public void restoreDefaults() throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().RE());
        ATCommandResponse.RE resp1 = listener.getResponse(frameID);
    }

    public void softwareReset() throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().FR());
        ATCommandResponse.FR resp1 = listener.getResponse(frameID);
    }

    public void applyChanges() throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().AC());
        ATCommandResponse.AC resp1 = listener.getResponse(frameID);
    }

    /*
     * This does not work because the module does not send a standard API frame,
     * but instead it dumps the information directly as ASCII.
     */
//    public void getVersionLong() throws XBeeOperationFailedException {
//        int frameID;
//        frameID = sendATCommand(new ATCommandPayloadFactory().VL());
//        ATCommandResponse.VL resp1 = listener.getResponse(frameID);
//
    /* Addressing */
    public void setDestinationAddress(XBeeAddress address) throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().setDH(address.getHighBytes()));
        ATCommandResponse.DH resp1 = listener.getResponse(frameID);

        frameID = sendATCommand(new ATCommandPayloadFactory().setDL(address.getLowBytes()));
        ATCommandResponse.DL resp2 = listener.getResponse(frameID);
    }

    public XBeeAddress getDestinationAddress() throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().queryDH());
        ATCommandResponse.DH resp1 = listener.getResponse(frameID);

        frameID = sendATCommand(new ATCommandPayloadFactory().queryDL());
        ATCommandResponse.DL resp2 = listener.getResponse(frameID);

        return new XBeeAddress(resp1.getValue(), resp2.getValue());
    }

    public long getDeviceTypeIdentifier() throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().queryDD());
        ATCommandResponse.DD resp1 = listener.getResponse(frameID);

        return resp1.getValue();
    }

    public XBeeSerialNumber getSerialNumber() throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().querySH());
        ATCommandResponse.SH resp1 = listener.getResponse(frameID);

        frameID = sendATCommand(new ATCommandPayloadFactory().querySL());
        ATCommandResponse.SL resp2 = listener.getResponse(frameID);

        return new XBeeSerialNumber(resp1.getValue(), resp2.getValue());
    }

    public void setHoppingChannel(long hoppingChannel) throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().setHP((int) hoppingChannel));
        ATCommandResponse.HP resp1 = listener.getResponse(frameID);
    }

    public long getHoppingChannel() throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().queryHP());
        ATCommandResponse.HP resp1 = listener.getResponse(frameID);

        return resp1.getValue();
    }

    public void setSourceEndpoint(long sourceEndpoint) throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().setSE((int) sourceEndpoint));
        ATCommandResponse.SE resp1 = listener.getResponse(frameID);
    }

    public long getSourceEndpoint() throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().querySE());
        ATCommandResponse.SE resp1 = listener.getResponse(frameID);

        return resp1.getValue();
    }

    public void setDestinationEndpoint(long destinationEndpoint) throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().setDE((int) destinationEndpoint));
        ATCommandResponse.DE resp1 = listener.getResponse(frameID);
    }

    public long getDestinationEndpoint() throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().queryDE());
        ATCommandResponse.DE resp1 = listener.getResponse(frameID);

        return resp1.getValue();
    }

    public void setClusterIdentifier(long clusterIdentifier) throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().setCI((int) clusterIdentifier));
        ATCommandResponse.CI resp1 = listener.getResponse(frameID);
    }

    public long getClusterIdentifier() throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().queryCI());
        ATCommandResponse.CI resp1 = listener.getResponse(frameID);

        return resp1.getValue();
    }

    public void setMaximumRFPayloadBytes(long maximumRFPayloadBytes) throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().setNP((int) maximumRFPayloadBytes));
        ATCommandResponse.NP resp1 = listener.getResponse(frameID);
    }

    public long getMaximumRFPayloadBytes() throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().queryNP());
        ATCommandResponse.NP resp1 = listener.getResponse(frameID);

        return resp1.getValue();
    }

    /* Serial Interfacing (I/O) */

    /* I/O Commands */
    public void setPWM0DIO10Configuration(PWM0_DIO10_Configuration config) throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().setP0Configuration(config));
        ATCommandResponse.P0 resp1 = listener.getResponse(frameID);
    }

    public PWM0_DIO10_Configuration getPWM0DIO10Configuration() throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().queryP0Configuration());
        ATCommandResponse.P0 resp1 = listener.getResponse(frameID);

        return resp1.getElement();
    }

    public void setPWM1DIO11Configuration(PWM1_DIO11_Configuration config) throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().setP1Configuration(config));
        ATCommandResponse.P1 resp1 = listener.getResponse(frameID);
    }

    public PWM1_DIO11_Configuration getPWM1DIO11Configuration() throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().queryP1Configuration());
        ATCommandResponse.P1 resp1 = listener.getResponse(frameID);

        return resp1.getElement();
    }

    public void setDIO12Configuration(DIO12_Configuration config) throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().setP2Configuration(config));
        ATCommandResponse.P2 resp1 = listener.getResponse(frameID);
    }

    public DIO12_Configuration getDIO12Configuration() throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().queryP2Configuration());
        ATCommandResponse.P2 resp1 = listener.getResponse(frameID);

        return resp1.getElement();
    }

    public void setAD0DIO0Configuration(AD0_DIO0_Configuration config) throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().setD0Configuration(config));
        ATCommandResponse.D0 resp1 = listener.getResponse(frameID);
    }

    public AD0_DIO0_Configuration getAD0DIO0Configuration() throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().queryD0Configuration());
        ATCommandResponse.D0 resp1 = listener.getResponse(frameID);

        return resp1.getElement();
    }

    public void setAD1DIO1Configuration(AD1_DIO1_Configuration config) throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().setD1Configuration(config));
        ATCommandResponse.D1 resp1 = listener.getResponse(frameID);
    }

    public AD1_DIO1_Configuration getAD1DIO1Configuration() throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().queryD1Configuration());
        ATCommandResponse.D1 resp1 = listener.getResponse(frameID);

        return resp1.getElement();
    }

    public void setAD2DIO2Configuration(AD2_DIO2_Configuration config) throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().setD2Configuration(config));
        ATCommandResponse.D2 resp1 = listener.getResponse(frameID);
    }

    public AD2_DIO2_Configuration getAD2DIO2Configuration() throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().queryD2Configuration());
        ATCommandResponse.D2 resp1 = listener.getResponse(frameID);

        return resp1.getElement();
    }

    public void setAD3DIO3Configuration(AD3_DIO3_Configuration config) throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().setD3Configuration(config));
        ATCommandResponse.D3 resp1 = listener.getResponse(frameID);
    }

    public AD3_DIO3_Configuration getAD3DIO3Configuration() throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().queryD3Configuration());
        ATCommandResponse.D3 resp1 = listener.getResponse(frameID);

        return resp1.getElement();
    }

    public void setAD4DIO4Configuration(AD4_DIO4_Configuration config) throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().setD4Configuration(config));
        ATCommandResponse.D4 resp1 = listener.getResponse(frameID);
    }

    public AD4_DIO4_Configuration getAD4DIO4Configuration() throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().queryD4Configuration());
        ATCommandResponse.D4 resp1 = listener.getResponse(frameID);

        return resp1.getElement();
    }

    public void setAD5DIO5Configuration(AD5_DIO5_Configuration config) throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().setD5Configuration(config));
        ATCommandResponse.D5 resp1 = listener.getResponse(frameID);
    }

    public AD5_DIO5_Configuration getAD5DIO5Configuration() throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().queryD5Configuration());
        ATCommandResponse.D5 resp1 = listener.getResponse(frameID);

        return resp1.getElement();
    }

    public void setDIO8Configuration(DIO8_Configuration config) throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().setD8Configuration(config));
        ATCommandResponse.D8 resp1 = listener.getResponse(frameID);
    }

    public DIO8_Configuration getDIO8Configuration() throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().queryD8Configuration());
        ATCommandResponse.D8 resp1 = listener.getResponse(frameID);

        return resp1.getElement();
    }

    public void setDIO9Configuration(DIO9_Configuration config) throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().setD9Configuration(config));
        ATCommandResponse.D9 resp1 = listener.getResponse(frameID);
    }

    public DIO9_Configuration getDIO9Configuration() throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().queryD9Configuration());
        ATCommandResponse.D9 resp1 = listener.getResponse(frameID);

        return resp1.getElement();
    }

    public void setRssiPwmTimer(long rssiPwmTimer) throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().setRP((int) rssiPwmTimer));
        ATCommandResponse.RP resp1 = listener.getResponse(frameID);
    }

    public long getRssiPwmTimer() throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().queryRP());
        ATCommandResponse.RP resp1 = listener.getResponse(frameID);

        return resp1.getValue();
    }

    public void setEnabledPullupResistors(Set<Pullup_Resistor> enabledPullupResistors) throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().setPRConfiguration(enabledPullupResistors));
        ATCommandResponse.PR resp1 = listener.getResponse(frameID);
    }

    public Set<Pullup_Resistor> getEnabledPullupResistors() throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().queryPRConfiguration());
        ATCommandResponse.PR resp1 = listener.getResponse(frameID);

        return resp1.getEnabledBits();
    }

    public void setPWM0OutputLevel(long pwm0OutputLevel) throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().setM0((int) pwm0OutputLevel));
        ATCommandResponse.M0 resp1 = listener.getResponse(frameID);
    }

    public long getPWM0OutputLevel() throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().queryM0());
        ATCommandResponse.M0 resp1 = listener.getResponse(frameID);

        return resp1.getValue();
    }

    public void setPWM1OutputLevel(long pwm0OutputLevel) throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().setM1((int) pwm0OutputLevel));
        ATCommandResponse.M1 resp1 = listener.getResponse(frameID);
    }

    public long getPWM1OutputLevel() throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().queryM1());
        ATCommandResponse.M1 resp1 = listener.getResponse(frameID);

        return resp1.getValue();
    }

    public void setAssocLEDBlinkTime(long assocLEDBlinkTime) throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().setLT((int) assocLEDBlinkTime));
        ATCommandResponse.LT resp1 = listener.getResponse(frameID);
    }

    public long getAssocLEDBlinkTime() throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().queryLT());
        ATCommandResponse.LT resp1 = listener.getResponse(frameID);

        return resp1.getValue();
    }

    public void pushCommissioningButton() throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().CB());
        ATCommandResponse.CB resp1 = listener.getResponse(frameID);
    }

    public void setIOSampleRate(long ioSampleRate) throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().setIR((int) ioSampleRate));
        ATCommandResponse.IR resp1 = listener.getResponse(frameID);
    }

    public long getIOSampleRate() throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().queryIR());
        ATCommandResponse.IR resp1 = listener.getResponse(frameID);

        return resp1.getValue();
    }

    public void setSampleFromSleepRate(long sampleFromSleepRate) throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().setIF((int) sampleFromSleepRate));
        ATCommandResponse.IF resp1 = listener.getResponse(frameID);
    }

    public long getSampleFromSleepRate() throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().queryIF());
        ATCommandResponse.IF resp1 = listener.getResponse(frameID);

        return resp1.getValue();
    }

    public void setMacRetries(long macRetries) throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().setRR((int) macRetries));
        ATCommandResponse.RR resp1 = listener.getResponse(frameID);
    }

    public long getMacRetries() throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().queryRR());
        ATCommandResponse.RR resp1 = listener.getResponse(frameID);

        return resp1.getValue();
    }

    public void setMultipleTransmissions(long multipleTransmissions) throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().setMT((int) multipleTransmissions));
        ATCommandResponse.MT resp1 = listener.getResponse(frameID);
    }

    public long getMultipleTransmissions() throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().queryMT());
        ATCommandResponse.MT resp1 = listener.getResponse(frameID);

        return resp1.getValue();
    }

    public void setEnabledIODigitalChangeDetectionPins(Set<Digital_IO_Pin> enabledDigitalChangeDetectionIOPins) throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().setIC(enabledDigitalChangeDetectionIOPins));
        ATCommandResponse.IC resp1 = listener.getResponse(frameID);
    }

    public Set<Digital_IO_Pin> getEnabledIODigitalChangeDetectionPins() throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().queryIC());
        ATCommandResponse.IC resp1 = listener.getResponse(frameID);

        return resp1.getEnabledBits();
    }

    //TODO: Check the remote case (_1S)
    public void forceSample() throws XBeeOperationFailedException {
        int frameID;
        frameID = sendATCommand(new ATCommandPayloadFactory().IS());
        ATCommandResponse.IS resp1 = listener.getResponse(frameID);
    }

    /* Diagnostics */
    /* AT Command Options */
    /* Sleep Commands */
    /* Encryption and Security */
    /* Networking */
    /* RF Interfacing */
    /* DigiMesh */
    public int sendATCommand(ATCommandRequest command) throws XBeeOperationFailedException {
        int[] data = new int[4 + command.getParameters().length];
        int i = 0;
        int frameID = generateFrameID();
        data[i++] = APIFrameType.ATCommand.getValue();
        data[i++] = frameID;
        data[i++] = (int) command.getCommand().getCommandString().charAt(0);
        data[i++] = (int) command.getCommand().getCommandString().charAt(1);
        for (int j = 0; j < command.getParameters().length; j++) {
            data[i++] = command.getParameters()[j];
        }
        sendFrame(data);

        return frameID;
    }

    private int generateFrameID() {
        frameIDGenerator++;
        if (frameIDGenerator == 256) {
            frameIDGenerator = 1;
        }
        return frameIDGenerator;
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

        Map<Integer, Object> locks = new HashMap<Integer, Object>();
        Map<Integer, FrameWithID> frames = new HashMap<Integer, FrameWithID>();

        {
            for (int i = 0; i < 256; i++) {
                locks.put(i, new Object());
            }
        }

        public <T extends FrameWithID> T getResponse(int id) {
            synchronized (locks.get(id)) {
                try {
                    locks.get(id).wait();
                } catch (InterruptedException ex) {
                    logger.error(ex);
                }
            }

            return (T) frames.get(id);
        }

        @Override
        public void run() {
            try {
                logger.debug("Started frameListener");
                while (true) {
                    int b = read();
                    int[] data = receiveFrame();

                    String debug = "Received API Frame (" + APIFrameType.get(data[0]).toString() + "): ";
                    debug += data[0];
                    for (int i = 1; i < data.length; i++) {
                        debug += (char) data[i];
                    }
                    logger.trace(debug);

                    Frame frame = null;
                    switch (APIFrameType.get(data[0])) {
                        case TransmitStatus:
                            frame = new TransmitStatusFactory().parse(data);
                            break;
                        case ATCommandResponse:
                            frame = new ATCommandResponseFactory().parse(data);
                            if (!((ATCommandResponse) frame).getCommandStatus().equals(ATCommandResponse.CommandStatus.OK)) {
                                logger.error("ATCommand " + ((ATCommandResponse) frame).getCommand().getCommandString() + " returned an error");
                            }
                            break;
                        default:
                            logger.warn("Received unknown frame type: " + data[0]);
                    }
                    if (frame != null && frame instanceof FrameWithID) {
                        FrameWithID frameWithID = (FrameWithID) frame;
                        frames.put(frameWithID.getId(), frameWithID);
                        synchronized (locks.get(frameWithID.getId())) {
                            locks.get(frameWithID.getId()).notifyAll();
                        }
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
