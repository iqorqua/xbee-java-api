/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.google.code.xbeejavaapi.api;

import com.google.code.xbeejavaapi.api.exception.ATCommandReturnedErrorException;
import com.google.code.xbeejavaapi.api.exception.XBeeOperationFailedException;
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
    private static final int ERROR = 1;
    private static final int INVALID_COMMAND = 2;
    private static final int INVALID_PARAMETER = 3;

    /**
     * Parses this frame from frame data sent by the module.
     * Frame data corresponds to bytes 4-n, see XBee®/XBee-PRO® DigiMeshTM 2.4 RF Modules Manual.
     * @param data Frame bytes 4-n (excludes checksum) received from the module.
     */
    public ATCommandResponse parse(int[] data) throws XBeeOperationFailedException {
        Exception e;
        try {
            String commandName = new String(new char[]{(char) data[2], (char) data[3]});

            switch (data[4]) {
                case ERROR:
                    throw new ATCommandReturnedErrorException("ATCommand " + commandName + " returned ERROR as command status", data[1]);
                case INVALID_COMMAND:
                    throw new ATCommandReturnedErrorException("ATCommand " + commandName + " returned INVALID_COMMAND as command status", data[1]);
                case INVALID_PARAMETER:
                    throw new ATCommandReturnedErrorException("ATCommand " + commandName + " returned INVALID_PARAMETER as command status", data[1]);
            }

            if (commandName.equals("1S")) {
                commandName = "_1S";
            }

            Constructor constructor =
                    Class.forName("com.google.code.xbeejavaapi.api.ATCommandResponse$" + commandName).
                    getConstructor(new Class[]{(new int[0]).getClass()});
            ATCommandResponse atCommandResponse = (ATCommandResponse) constructor.newInstance(data);
            return atCommandResponse;

        } catch (InstantiationException ex) {
            logger.error(ex);
            e = ex;
        } catch (IllegalAccessException ex) {
            logger.error(ex);
            e = ex;
        } catch (IllegalArgumentException ex) {
            logger.error(ex);
            e = ex;
        } catch (InvocationTargetException ex) {
            logger.error(ex);
            e = ex;
        } catch (NoSuchMethodException ex) {
            logger.error(ex);
            e = ex;
        } catch (SecurityException ex) {
            logger.error(ex);
            e = ex;
        } catch (ClassNotFoundException ex) {
            logger.error(ex);
            e = ex;
        }
        throw new XBeeOperationFailedException("Reflection exception", e);
    }
}
