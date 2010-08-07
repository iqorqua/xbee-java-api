/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.google.code.xbeejavaapi;

import org.apache.log4j.Logger;

/**
 *
 * @author David Miguel Antunes <davidmiguel [ at ] antunes.net>
 */
public enum APIFrames {

    ModemStatus(0x8A),
    ATCommand(0x08),
    ATCommandQueueParameterValue(0x09),
    ATCommandResponse(0x88),
    RemoteCommandRequest(0x17),
    RemoteCommandResponse(0x97),
    TransmitRequest(0x10),
    ExplicitAddressingCommand(0x11),
    TransmitStatus(0x88),
    ReceivePacket(0x90),
    ExplicitRxIndicator(0x91);
    private int value;

    private APIFrames(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
