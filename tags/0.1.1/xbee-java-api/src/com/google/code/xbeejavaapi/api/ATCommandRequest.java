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
public class ATCommandRequest {

    private ATCommand command;
    private int[] parameters;

    public ATCommandRequest(ATCommand command, int[] parameters) {
        this.command = command;
        this.parameters = parameters;
    }

    public ATCommand getCommand() {
        return command;
    }

    public int[] getParameters() {
        return parameters;
    }
}
