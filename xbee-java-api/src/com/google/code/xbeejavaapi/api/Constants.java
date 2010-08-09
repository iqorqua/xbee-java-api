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
public class Constants {

    public static enum APIMode {

        API_MODE_OFF(0),
        API_MODE_ON(1),
        API_MODE_ON_WITH_ESCAPE_SEQUENCES(2);
        private int value;

        private APIMode(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static enum APIOutputFormat {

        STANDARD_DATA_FRAMES(0),
        EXPLICIT_ADDRESSING_DATA_FRAMES(1);
        private int value;

        private APIOutputFormat(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static enum BaudRate {

        BAUD_1200(0),
        BAUD_2400(1),
        BAUD_4800(2),
        BAUD_9600(3),
        BAUD_19200(4),
        BAUD_38400(5),
        BAUD_57600(6),
        BAUD_115200(7),
        BAUD_230400(8);
        private int value;

        private BaudRate(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static enum Parity {

        NO_PARITY(0),
        EVEN_PARITY(1),
        ODD_PARITY(2),
        FORCED_HIGH_PARITY(3),
        FORCED_LOW_PARITY(4);
        private int value;

        private Parity(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static enum DIO7Configuration {

        INPUT_UNMONITORED(0),
        CTS_FLOW_CONTROL(1),
        DIGITAL_INPUT_MONITORED(3),
        DIGITAL_OUTPUT_LOW(4),
        DIGITAL_OUTPUT_HIGH(5),
        RS485_TX_ENABLE_LOW_TX(6),
        RS485_TX_ENABLE_HIGH_TX(7);
        private int value;

        private DIO7Configuration(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static enum DIO6Configuration {

        INPUT_UNMONITORED(0),
        RTS_FLOW_CONTROL(1),
        DIGITAL_INPUT_MONITORED(3),
        DIGITAL_OUTPUT_LOW(4),
        DIGITAL_OUTPUT_HIGH(5),;
        private int value;

        private DIO6Configuration(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
