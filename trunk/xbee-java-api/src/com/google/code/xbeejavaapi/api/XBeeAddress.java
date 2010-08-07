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
public class XBeeAddress {

    private static final Logger logger = Logger.getLogger(XBeeAddress.class);
    public static final XBeeAddress MULTICAST = new XBeeAddress(0x0, 0xFFFF);
    private long highBytes;
    private long lowBytes;

    public XBeeAddress(long highBytes, long lowBytes) {
        this.highBytes = highBytes;
        this.lowBytes = lowBytes;
    }

    public long getHighBytes() {
        return highBytes;
    }

    public long getLowBytes() {
        return lowBytes;
    }
}
