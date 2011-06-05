/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.google.code.xbeejavaapi.api;

import java.io.Serializable;
import org.apache.log4j.Logger;

/**
 *
 * @author David Miguel Antunes <davidmiguel [ at ] antunes.net>
 */
public class Data64Bits implements Serializable {

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
        return "low=0x" + Long.toHexString(lowBytes).toUpperCase() + " high=0x" + Long.toHexString(highBytes).toUpperCase();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Data64Bits other = (Data64Bits) obj;
        if (this.highBytes != other.highBytes) {
            return false;
        }
        if (this.lowBytes != other.lowBytes) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (int) (this.highBytes ^ (this.highBytes >>> 32));
        hash = 97 * hash + (int) (this.lowBytes ^ (this.lowBytes >>> 32));
        return hash;
    }
}
