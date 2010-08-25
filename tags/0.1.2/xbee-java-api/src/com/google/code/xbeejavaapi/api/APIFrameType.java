/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.google.code.xbeejavaapi.api;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author David Miguel Antunes <davidmiguel [ at ] antunes.net>
 */
public enum APIFrameType {

    ModemStatus(0x8A),
    ATCommand(0x08),
    ATCommandQueueParameterValue(0x09),
    ATCommandResponse(0x88),
    RemoteCommandRequest(0x17),
    RemoteCommandResponse(0x97),
    TransmitRequest(0x10),
    ExplicitAddressingCommand(0x11),
    TransmitStatus(0x8B),
    ReceivePacket(0x90),
    ExplicitRxIndicator(0x91),
    IODataSampleRxIndicator(0x92);
    private int value;
    private static final Map<Integer, APIFrameType> values = new HashMap<Integer, APIFrameType>();

    private APIFrameType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    static {
        for (int i = 0; i < values().length; i++) {
            APIFrameType apiFrame = values()[i];
            values.put(apiFrame.value, apiFrame);
        }
    }

    public static APIFrameType get(int value) {
        return values.get(value);
    }
}
