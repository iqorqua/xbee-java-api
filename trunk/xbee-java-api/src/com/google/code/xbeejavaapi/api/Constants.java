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

    public static interface ValueBasedEnum<T> {

        public int getValue();
    }

    public static enum APIMode implements ValueBasedEnum {

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

    public static enum APIOutputFormat implements ValueBasedEnum {

        STANDARD_DATA_FRAMES(0),
        EXPLICIT_ADDRESSING_DATA_FRAMES(1);
        private int value;

        private APIOutputFormat(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public APIOutputFormat get(int value) {
            for (int i = 0; i < values().length; i++) {
                APIOutputFormat instance = values()[i];
                if (instance.value == value) {
                    return instance;
                }
            }
            throw new RuntimeException("Asked for an unexisting value in an enum");
        }
    }

    public static enum BaudRate implements ValueBasedEnum {

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

        public BaudRate get(int value) {
            for (int i = 0; i < values().length; i++) {
                BaudRate instance = values()[i];
                if (instance.value == value) {
                    return instance;
                }
            }
            throw new RuntimeException("Asked for an unexisting value in an enum");
        }
    }

    public static enum Parity implements ValueBasedEnum {

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

    public static enum DIO7Configuration implements ValueBasedEnum {

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

    public static enum DIO6Configuration implements ValueBasedEnum {

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

    public static enum PWM0_DIO10_Configuration implements ValueBasedEnum {

        DISABLED(0),
        RSSI_PWM(1),
        PWM_OUTPUT(2),
        DIGITAL_INPUT_MONITORED(3),
        DIGITAL_OUTPUT_DEFAULT_LOW(4),
        DIGITAL_OUTPUT_DEFAULT_HIGH(5),;
        private int value;

        private PWM0_DIO10_Configuration(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static enum PWM1_DIO11_Configuration implements ValueBasedEnum {

        DISABLED(0),
        RSSI_PWM(1),
        PWM_OUTPUT(2),
        DIGITAL_INPUT_MONITORED(3),
        DIGITAL_OUTPUT_DEFAULT_LOW(4),
        DIGITAL_OUTPUT_DEFAULT_HIGH(5),;
        private int value;

        private PWM1_DIO11_Configuration(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static enum DIO12_Configuration implements ValueBasedEnum {

        DISABLED(0),
        DIGITAL_INPUT_MONITORED(3),
        DIGITAL_OUTPUT_DEFAULT_LOW(4),
        DIGITAL_OUTPUT_DEFAULT_HIGH(5),;
        private int value;

        private DIO12_Configuration(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static enum AD0_DIO0_Configuration implements ValueBasedEnum {

        DISABLED(0),
        COMMISSIONING_BUTTON_ENABLE(1),
        ANALOG_INPUT_SINGLE_ENDED(2),
        DIGITAL_INPUT_MONITORED(3),
        DIGITAL_OUTPUT_DEFAULT_LOW(4),
        DIGITAL_OUTPUT_DEFAULT_HIGH(5),;
        private int value;

        private AD0_DIO0_Configuration(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static enum AD1_DIO1_Configuration implements ValueBasedEnum {

        DISABLED(0),
        DISABLED_BUTTON(1),
        ANALOG_INPUT_SINGLE_ENDED(2),
        DIGITAL_INPUT_MONITORED(3),
        DIGITAL_OUTPUT_DEFAULT_LOW(4),
        DIGITAL_OUTPUT_DEFAULT_HIGH(5),;
        private int value;

        private AD1_DIO1_Configuration(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static enum AD2_DIO2_Configuration implements ValueBasedEnum {

        DISABLED(0),
        ANALOG_INPUT_SINGLE_ENDED(2),
        DIGITAL_INPUT_MONITORED(3),
        DIGITAL_OUTPUT_DEFAULT_LOW(4),
        DIGITAL_OUTPUT_DEFAULT_HIGH(5),;
        private int value;

        private AD2_DIO2_Configuration(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static enum AD3_DIO3_Configuration implements ValueBasedEnum {

        DISABLED(0),
        ANALOG_INPUT_SINGLE_ENDED(2),
        DIGITAL_INPUT_MONITORED(3),
        DIGITAL_OUTPUT_DEFAULT_LOW(4),
        DIGITAL_OUTPUT_DEFAULT_HIGH(5),;
        private int value;

        private AD3_DIO3_Configuration(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static enum AD4_DIO4_Configuration implements ValueBasedEnum {

        DISABLED(0),
        ANALOG_INPUT_SINGLE_ENDED(2),
        DIGITAL_INPUT_MONITORED(3),
        DIGITAL_OUTPUT_DEFAULT_LOW(4),
        DIGITAL_OUTPUT_DEFAULT_HIGH(5),;
        private int value;

        private AD4_DIO4_Configuration(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static enum AD5_DIO5_Configuration implements ValueBasedEnum {

        DISABLED(0),
        ASSOCIATED_INDICATION_LED(1),
        ANALOG_INPUT_SINGLE_ENDED(2),
        DIGITAL_INPUT_MONITORED(3),
        DIGITAL_OUTPUT_DEFAULT_LOW(4),
        DIGITAL_OUTPUT_DEFAULT_HIGH(5),;
        private int value;

        private AD5_DIO5_Configuration(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static enum DIO8_Configuration implements ValueBasedEnum {

        DISABLED(0),
        DIGITAL_INPUT_MONITORED(3),
        DIGITAL_OUTPUT_DEFAULT_LOW(4),
        DIGITAL_OUTPUT_DEFAULT_HIGH(5),;
        private int value;

        private DIO8_Configuration(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static enum DIO9_Configuration implements ValueBasedEnum {

        DISABLED(0),
        ON_SLEEP_LINE(1),
        DIGITAL_INPUT_MONITORED(3),
        DIGITAL_OUTPUT_DEFAULT_LOW(4),
        DIGITAL_OUTPUT_DEFAULT_HIGH(5),;
        private int value;

        private DIO9_Configuration(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static enum Pullup_Resistor implements ValueBasedEnum {

        DIO4_AD4(0),
        AD3_DIO3(1),
        AD2_DIO2(2),
        AD1_DIO1(3),
        AD0_DIO0(4),
        RTS_DIO6(5),
        DTR_SLEEP_RQ_DIO8(6),
        DIN_CONFIG(7),
        ASSOCIATE_DIO5(8),
        ON_SLEEP_DIO9(9),
        DIO12(10),
        PWM0_RSSI_DIO10(11),
        PWM1_DIO11(12),
        DIO7_CTS(13),
        DOUT(14),;
        private int value;

        private Pullup_Resistor(int bit) {
            this.value = bit;
        }

        public int getValue() {
            return value;
        }
    }

    public static enum Digital_IO_Pin implements ValueBasedEnum {

        DIO0(0),
        DIO1(1),
        DIO2(2),
        DIO3(3),
        DIO4(4),
        DIO5(5),
        DIO6(6),
        DIO7(7),
        DIO8(8),
        DIO9(9),
        DIO10(10),
        DIO11(11),
        DIO12(12),;
        private int value;

        private Digital_IO_Pin(int bit) {
            this.value = bit;
        }

        public int getValue() {
            return value;
        }
    }

    public static enum Analog_IO_Pin implements ValueBasedEnum {

        AD0(0),
        AD1(1),
        AD2(2),
        AD3(3),
        AD4(4),
        AD5(5),;
        private int value;

        private Analog_IO_Pin(int bit) {
            this.value = bit;
        }

        public int getValue() {
            return value;
        }
    }

    public static enum Network_Discovery_Option implements ValueBasedEnum {

        APPEND_DD_VALUE(0x01),
        LOCAL_DEVICE_SENDS_ND(0x02),;
        private int value;

        private Network_Discovery_Option(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static enum Encryption_Enable implements ValueBasedEnum {

        ENCRYPTION_DISABLED(0),
        ENCRYPTION_ENABLED(1),;
        private int value;

        private Encryption_Enable(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static enum Routing_Type implements ValueBasedEnum {

        ROUTER(0),
        END_DEVICE(2),;
        private int value;

        private Routing_Type(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static enum Power_Level implements ValueBasedEnum {

        XBEE_minus_7dBm_XBEEPRO_10dBm(0),
        XBEE_minus_1_7dBm_XBEEPRO_12dBm(1),
        XBEE_minus_0_77dBm_XBEEPRO_14dBm(2),
        XBEE_0_62dBm_XBEEPRO_16dBm(3),
        XBEE_1_42dBm_XBEEPRO_18dBm(4),;
        private int value;

        private Power_Level(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static enum Sleep_Mode implements ValueBasedEnum {

        NORMAL(0),
        SLEEP_SUPPORT_NODE(7),
        CYCLIC_SLEEP(8),;
        private int value;

        private Sleep_Mode(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static enum Sleep_Option implements ValueBasedEnum {

        PREFERRED_SLEEP_COORDINATOR(0),
        NON_SLEEP_COORDINATOR(1),
        ENABLE_API_SLEEP_STATUS_MESSAGES(2),
        DISABLE_AUTOMATIC_EARLY_WAKEUP_FOR_MISSED_SYNCS(3),
        SM7_AND_SM8_NODES_EQUAL_FOR_ELECTION(4),
        DISABLE_COORDINATOR_RAPID_SYNC_DEPLOYMENT_MODE(5),;
        private int value;

        private Sleep_Option(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static enum Sleep_Status_Value implements ValueBasedEnum {

        NETWORK_IN_WAKE_STATE(0),
        NODE_IS_NETWORK_SLEEP_COORDINATOR(1),
        RECEIVED_A_VALID_SYNC_MESSAGE_SINCE_POWER_ON(2),
        RECEIVED_A_VALID_SYNC_MESSAGE_IN_CURRENT_WAKE_CYCLE(3),
        USER_CHANGED_SLEEP_SETTINGS(4),
        USER_REQUESTED_NODE_TO_BE_SLEEP_COORDINATOR(5),
        NODE_IS_IN_DEPLOYMENT_MODE(6),;
        private int value;

        private Sleep_Status_Value(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static enum Association_Indication implements ValueBasedEnum {

        SUCCESSFULL_JOINED_OR_FORMED_NETWORK(0x00),
        SCAN_FOUND_NO_PANS(0x21),
        SCAN_FOUND_NO_VALID_PANS(0x22),
        VALID_COORDINATOR_OR_NETWORK_FOUND_BUT_JOINING_IS_NOT_ALLOWED(0x23),
        NODE_JOINING_ATTEMPT_FAILED(0x27),
        COORDINATOR_START_ATTEMPT_FAILED(0x2A),
        CHECKING_FOR_AN_EXISTING_COORDINATOR(0x2B),
        ATTEMPTED_TO_JOIN_A_DEVICE_THAT_DID_NOT_RESPOND(0xAB),
        SECURE_JOIN_ERROR_NETWORK_SECURITY_KEY_RECEIVED_UNSECURED(0xAC),
        SECURE_JOIN_ERROR_NETWORK_SECURITY_KEY_NOT_RECEIVED(0xAD),
        SECURE_JOIN_ERROR_JOINING_DEVICE_DOES_NOT_HAVE_THE_RIGHT_PRECONFIGURED_LINK_KEY(0xAF),
        SCANNING_FOR_A_ZIGBEE_NETWORK(0xFF),;
        private int value;

        private Association_Indication(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static enum Device_Type implements ValueBasedEnum {

        COORDINATOR(0),
        ROUTER(1),
        END_DEVICE(2),;
        private int value;

        private Device_Type(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static enum Command_Status {

        OK(0),
        ERROR(1),
        INVALID_COMMAND(2),
        INVALID_PARAMETER(3),;
        private int value;

        private Command_Status(int value) {
            this.value = value;
        }
    }
}
