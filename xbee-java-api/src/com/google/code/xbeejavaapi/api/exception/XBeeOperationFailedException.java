/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.google.code.xbeejavaapi.api.exception;

import org.apache.log4j.Logger;

/**
 *
 * @author David Miguel Antunes <davidmiguel [ at ] antunes.net>
 */
public class XBeeOperationFailedException extends Exception {

    private static final Logger logger = Logger.getLogger(XBeeOperationFailedException.class);

    public XBeeOperationFailedException(Throwable cause) {
        super(cause);
    }

    public XBeeOperationFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public XBeeOperationFailedException(String message) {
        super(message);
    }

    public XBeeOperationFailedException() {
        super();
    }
}
