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
public class Frame {

    private static final Logger logger = Logger.getLogger(Frame.class);
    private APIFrameType type;

    public Frame(int[] data) {
        this.type = APIFrameType.get(data[0]);
    }

    public APIFrameType getType() {
        return type;
    }

    public void setType(APIFrameType type) {
        this.type = type;
    }
}
