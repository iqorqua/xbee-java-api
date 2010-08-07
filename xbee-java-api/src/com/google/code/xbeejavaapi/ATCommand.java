/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.google.code.xbeejavaapi;

import org.apache.log4j.Logger;

/**
 *
 * @author David Miguel Antunes <davidmiguel [ at ] antunes.net>
 */
public class ATCommand {

    private String command;
    private int[] parameters;

    public ATCommand(String command, int[] parameters) {
        this.command = command;
        this.parameters = parameters;
    }

    public String getCommand() {
        return command;
    }

    public int[] getParameters() {
        return parameters;
    }
}
