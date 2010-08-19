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
public class Data64Bits {

    private static final Logger logger = Logger.getLogger(Data64Bits.class);
    private long highBytes;
    private long lowBytes;

    public Data64Bits(long highBytes, long lowBytes) {
        this.highBytes = highBytes;
        this.lowBytes = lowBytes;
    }

    public long getHighBytes() {
        return highBytes;
    }

    public long getLowBytes() {
        return lowBytes;
    }

    @Override
    public String toString() {
        return "high=0x" + Long.toHexString(highBytes).toUpperCase() + " low=0x" + Long.toHexString(lowBytes).toUpperCase();
    }
}
