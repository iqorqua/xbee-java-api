/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.google.code.xbeejavaapi.api;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 *
 * @author David Miguel Antunes <davidmiguel [ at ] antunes.net>
 */
public class ATCommandResponse {

    public enum CommandStatus {

        OK(0),
        ERROR(1),
        InvalidCommand(2),
        InvalidParameter(3);
        private int value;
        private static Map<Integer, CommandStatus> values = new HashMap<Integer, CommandStatus>();

        static {
            for (int i = 0; i < values().length; i++) {
                CommandStatus commandStatus = values()[i];
                values.put(commandStatus.value, commandStatus);
            }
        }

        private CommandStatus(int value) {
            this.value = value;
        }

        public static CommandStatus get(int value) {
            return values.get(value);
        }
    }
    private static final Logger logger = Logger.getLogger(ATCommandResponse.class);
    private CommandStatus commandStatus;
    private ATCommand command;
    private int[] data;

    public ATCommandResponse(CommandStatus commandStatus, ATCommand command, int[] data) {
        this.commandStatus = commandStatus;
        this.command = command;
        this.data = data;
    }
}
