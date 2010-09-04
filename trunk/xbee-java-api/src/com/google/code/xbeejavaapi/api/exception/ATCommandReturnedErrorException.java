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
public class ATCommandReturnedErrorException extends XBeeOperationFailedException {

    private static final Logger logger = Logger.getLogger(ATCommandReturnedErrorException.class);
    private int frameID;

    public ATCommandReturnedErrorException(int frameID) {
        this.frameID = frameID;
    }

    public ATCommandReturnedErrorException(String message, int frameID) {
        super(message);
        this.frameID = frameID;
    }

    public ATCommandReturnedErrorException(String message, Throwable cause, int frameID) {
        super(message, cause);
        this.frameID = frameID;
    }

    public ATCommandReturnedErrorException(Throwable cause, int frameID) {
        super(cause);
        this.frameID = frameID;
    }

    public int getFrameID() {
        return frameID;
    }
}
