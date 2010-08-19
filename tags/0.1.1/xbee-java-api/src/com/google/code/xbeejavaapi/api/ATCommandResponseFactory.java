/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.google.code.xbeejavaapi.api;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author David Miguel Antunes <davidmiguel [ at ] antunes.net>
 */
public class ATCommandResponseFactory {

    private static final Logger logger = Logger.getLogger(ATCommandResponseFactory.class);

    /**
     * Parses this frame from frame data sent by the module.
     * Frame data corresponds to bytes 4-n, see XBee®/XBee-PRO® DigiMeshTM 2.4 RF Modules Manual.
     * @param data Frame bytes 4-n (excludes checksum) received from the module.
     */
    public ATCommandResponse parse(int[] data) {
        try {
            String commandName = new String(new char[]{(char) data[2], (char) data[3]});
            Constructor constructor =
                    Class.forName("com.google.code.xbeejavaapi.api.ATCommandResponse$" + commandName).
                    getConstructor(new Class[]{(new int[0]).getClass()});
            ATCommandResponse atCommandResponse = (ATCommandResponse) constructor.newInstance(data);
            return atCommandResponse;

        } catch (Exception ex) {
            logger.error(ex);
            return null;
        }
    }
}
