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
public class XBeeSerialNumber extends Data64Bits {

    private static final Logger logger = Logger.getLogger(XBeeSerialNumber.class);

    public XBeeSerialNumber(long highBytes, long lowBytes) {
        super(highBytes, lowBytes);
    }
}
