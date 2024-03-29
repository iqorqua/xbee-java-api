/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.google.code.xbeejavaapi.api;

import org.apache.log4j.Logger;

/**
 *
 * @author David Miguel Antunes <davidmiguel [ at ] antunes.net>
 */
public class TransmitStatusFactory {

    private static final Logger logger = Logger.getLogger(TransmitStatusFactory.class);

    /**
     * Parses this frame from frame data sent by the module.
     * Frame data corresponds to bytes 4-n, see XBee®/XBee-PRO® DigiMeshTM 2.4 RF Modules Manual.
     * @param data Frame bytes 4-n (excludes checksum) received from the module.
     */
    public TransmitStatus parse(int[] data) {
        return new TransmitStatus(data);
    }
}
