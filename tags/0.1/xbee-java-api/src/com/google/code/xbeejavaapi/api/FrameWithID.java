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
public class FrameWithID extends Frame {

    private static final Logger logger = Logger.getLogger(FrameWithID.class);
    private int id;

    public FrameWithID(int[] data) {
        super(data);
        this.id = data[1];
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
