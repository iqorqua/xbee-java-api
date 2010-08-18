/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.google.code.xbeejavaapi.api;

import org.apache.log4j.Logger;
import com.google.code.xbeejavaapi.api.Constants.*;
import java.util.Set;

/**
 *
 * @author David Miguel Antunes <davidmiguel [ at ] antunes.net>
 */
public class ATCommandPayloadFactory {

    private static final Logger logger = Logger.getLogger(ATCommandPayloadFactory.class);

    public enum D2LineState {

        DISABLED(0),
        ANALOG_INPUT_SINGLE_ENDED(2),
        DIGITAL_INPUT_MONITORED(3),
        DIGITAL_OUTPUT_LOW(4),
        DIGITAL_OUTPUT_HIGH(5);
        private int value;

        private D2LineState(int val) {
            this.value = val;
        }

        public int getValue() {
            return value;
        }
    }

    public ATCommandRequest setD2(D2LineState lineState) {
        return new ATCommandRequest(ATCommand.D2, new int[]{lineState.getValue()});
    }

    public ATCommandRequest queryD2() {
        return new ATCommandRequest(ATCommand.D2, new int[]{});
    }

    /* Special */
    /**
     * Write. Write parameter values to non-volatile memory so that parameter
     * modifications persist through subsequent resets.
     */
    public ATCommandRequest WR() {
        return new ATCommandRequest(ATCommand.WR, new int[]{});
    }

    /**
     * Restore Defaults. Restore module parameters to factory defaults.
     * @return
     */
    public ATCommandRequest RE() {
        return new ATCommandRequest(ATCommand.RE, new int[]{});
    }

    /**
     * Software Reset. Reset module. Responds immediately with an “OK” then
     * performs a FR reset 100ms later.
     */
    public ATCommandRequest FR() {
        return new ATCommandRequest(ATCommand.RE, new int[]{});
    }

    /**
     * Apply Changes. Immediately applies new settings without exiting
     * command mode.
     */
    public ATCommandRequest AC() {
        return new ATCommandRequest(ATCommand.AC, new int[]{});
    }

    /**
     * Version Long. Shows detailed version information including application
     * build date and VL time.
     */
    public ATCommandRequest VL() {
        return new ATCommandRequest(ATCommand.VL, new int[]{});
    }

    /* Addressing */
    public ATCommandRequest setDH(long addressHigh) {
        return new ATCommandRequest(
                ATCommand.DH,
                to4Bytes(addressHigh));
    }

    public ATCommandRequest queryDH() {
        return new ATCommandRequest(ATCommand.DH, new int[]{});
    }

    public ATCommandRequest setDL(long addressLow) {
        return new ATCommandRequest(
                ATCommand.DL,
                to4Bytes(addressLow));
    }

    public ATCommandRequest queryDL() {
        return new ATCommandRequest(ATCommand.DL, new int[]{});
    }

    public ATCommandRequest queryDD() {
        return new ATCommandRequest(ATCommand.DD, new int[]{});
    }

    public ATCommandRequest querySH() {
        return new ATCommandRequest(ATCommand.SH, new int[]{});
    }

    public ATCommandRequest querySL() {
        return new ATCommandRequest(ATCommand.SL, new int[]{});
    }

    public ATCommandRequest setHP(int hoppingChannel) {
        return new ATCommandRequest(ATCommand.HP, to1Byte(hoppingChannel));
    }

    public ATCommandRequest queryHP() {
        return new ATCommandRequest(ATCommand.HP, new int[]{});
    }

    public ATCommandRequest setSE(int sourceEndpoint) {
        return new ATCommandRequest(ATCommand.SE, to1Byte(sourceEndpoint));
    }

    public ATCommandRequest querySE() {
        return new ATCommandRequest(ATCommand.SE, new int[]{});
    }

    public ATCommandRequest setDE(int destinationEndpoint) {
        return new ATCommandRequest(ATCommand.DE, to1Byte(destinationEndpoint));
    }

    public ATCommandRequest queryDE() {
        return new ATCommandRequest(ATCommand.DE, new int[]{});
    }

    public ATCommandRequest setCI(long clusterIdentifier) {
        return new ATCommandRequest(
                ATCommand.CI,
                to2Bytes(clusterIdentifier));
    }

    public ATCommandRequest queryCI() {
        return new ATCommandRequest(ATCommand.CI, new int[]{});
    }

    public ATCommandRequest setNP(long maximumNFPayloadBytes) {
        return new ATCommandRequest(
                ATCommand.NP,
                to2Bytes(maximumNFPayloadBytes));
    }

    public ATCommandRequest queryNP() {
        return new ATCommandRequest(ATCommand.NP, new int[]{});
    }

    /* Serial Interfacing (I/O) */
    public ATCommandRequest setAP(APIMode mode) {
        return new ATCommandRequest(ATCommand.AP, new int[]{mode.getValue()});
    }

    public ATCommandRequest queryAP() {
        return new ATCommandRequest(ATCommand.AP, new int[]{});
    }

    public ATCommandRequest setAO(APIOutputFormat outputFormat) {
        return new ATCommandRequest(ATCommand.AO, new int[]{outputFormat.getValue()});
    }

    public ATCommandRequest queryAO() {
        return new ATCommandRequest(ATCommand.AO, new int[]{});
    }

    public ATCommandRequest setBD(BaudRate baudRate) {
        return new ATCommandRequest(ATCommand.BD, new int[]{baudRate.getValue()});
    }

    public ATCommandRequest queryBD() {
        return new ATCommandRequest(ATCommand.BD, new int[]{});
    }

    public ATCommandRequest setRO(int packetizationTimeout) {
        return new ATCommandRequest(ATCommand.RO, to1Byte(packetizationTimeout));
    }

    public ATCommandRequest queryRO() {
        return new ATCommandRequest(ATCommand.RO, new int[]{});
    }

    public ATCommandRequest setFT(int flowControlThreshold) {
        return new ATCommandRequest(ATCommand.FT, to1Byte(flowControlThreshold));
    }

    public ATCommandRequest queryFT() {
        return new ATCommandRequest(ATCommand.FT, new int[]{});
    }

    public ATCommandRequest setDIO7Configuration(DIO7Configuration dIO7Configuration) {
        return new ATCommandRequest(ATCommand.D7, new int[]{dIO7Configuration.getValue()});
    }

    public ATCommandRequest queryDIO7Configuration() {
        return new ATCommandRequest(ATCommand.D7, new int[]{});
    }

    public ATCommandRequest setDIO6Configuration(DIO6Configuration dIO6Configuration) {
        return new ATCommandRequest(ATCommand.D6, new int[]{dIO6Configuration.getValue()});
    }

    public ATCommandRequest queryDIO6Configuration() {
        return new ATCommandRequest(ATCommand.D6, new int[]{});
    }
//    /* I/O Commands */

    public ATCommandRequest setP0Configuration(PWM0_DIO10_Configuration configuration) {
        return new ATCommandRequest(ATCommand.P0, new int[]{configuration.getValue()});
    }

    public ATCommandRequest queryP0Configuration() {
        return new ATCommandRequest(ATCommand.P0, new int[]{});
    }

    public ATCommandRequest setP1Configuration(PWM1_DIO11_Configuration configuration) {
        return new ATCommandRequest(ATCommand.P1, new int[]{configuration.getValue()});
    }

    public ATCommandRequest queryP1Configuration() {
        return new ATCommandRequest(ATCommand.P1, new int[]{});
    }

    public ATCommandRequest setP2Configuration(DIO12_Configuration configuration) {
        return new ATCommandRequest(ATCommand.P2, new int[]{configuration.getValue()});
    }

    public ATCommandRequest queryP2Configuration() {
        return new ATCommandRequest(ATCommand.P2, new int[]{});
    }

    public ATCommandRequest setD0Configuration(AD0_DIO0_Configuration configuration) {
        return new ATCommandRequest(ATCommand.D0, new int[]{configuration.getValue()});
    }

    public ATCommandRequest queryD0Configuration() {
        return new ATCommandRequest(ATCommand.D0, new int[]{});
    }

    public ATCommandRequest setD1Configuration(AD1_DIO1_Configuration configuration) {
        return new ATCommandRequest(ATCommand.D1, new int[]{configuration.getValue()});
    }

    public ATCommandRequest queryD1Configuration() {
        return new ATCommandRequest(ATCommand.D1, new int[]{});
    }

    public ATCommandRequest setD2Configuration(AD2_DIO2_Configuration configuration) {
        return new ATCommandRequest(ATCommand.D2, new int[]{configuration.getValue()});
    }

    public ATCommandRequest queryD2Configuration() {
        return new ATCommandRequest(ATCommand.D2, new int[]{});
    }

    public ATCommandRequest setD3Configuration(AD3_DIO3_Configuration configuration) {
        return new ATCommandRequest(ATCommand.D3, new int[]{configuration.getValue()});
    }

    public ATCommandRequest queryD3Configuration() {
        return new ATCommandRequest(ATCommand.D3, new int[]{});
    }

    public ATCommandRequest setD4Configuration(AD4_DIO4_Configuration configuration) {
        return new ATCommandRequest(ATCommand.D4, new int[]{configuration.getValue()});
    }

    public ATCommandRequest queryD4Configuration() {
        return new ATCommandRequest(ATCommand.D4, new int[]{});
    }

    public ATCommandRequest setD5Configuration(AD5_DIO5_Configuration configuration) {
        return new ATCommandRequest(ATCommand.D5, new int[]{configuration.getValue()});
    }

    public ATCommandRequest queryD5Configuration() {
        return new ATCommandRequest(ATCommand.D5, new int[]{});
    }

    public ATCommandRequest setD8Configuration(DIO8_Configuration configuration) {
        return new ATCommandRequest(ATCommand.D8, new int[]{configuration.getValue()});
    }

    public ATCommandRequest queryD8Configuration() {
        return new ATCommandRequest(ATCommand.D8, new int[]{});
    }

    public ATCommandRequest setD9Configuration(DIO9_Configuration configuration) {
        return new ATCommandRequest(ATCommand.D9, new int[]{configuration.getValue()});
    }

    public ATCommandRequest queryD9Configuration() {
        return new ATCommandRequest(ATCommand.D9, new int[]{});
    }

    public ATCommandRequest setRP(int rssi_pwm_timer) {
        return new ATCommandRequest(ATCommand.RP, to1Byte(rssi_pwm_timer));
    }

    public ATCommandRequest queryRP() {
        return new ATCommandRequest(ATCommand.RP, new int[]{});
    }

    public ATCommandRequest setPRConfiguration(Set<Pullup_Resistor> enabledPullupResistors) {
        int value = 0;
        for (Pullup_Resistor pullupResistor : enabledPullupResistors) {
            value = value | (0x1 << pullupResistor.getValue());
        }
        return new ATCommandRequest(ATCommand.PR, new int[]{value});
    }

    public ATCommandRequest queryPRConfiguration() {
        return new ATCommandRequest(ATCommand.PR, new int[]{});
    }

    public ATCommandRequest setM0(int pwm0_output_level) {
        return new ATCommandRequest(ATCommand.M0, to2Bytes(pwm0_output_level));
    }

    public ATCommandRequest queryM0() {
        return new ATCommandRequest(ATCommand.M0, new int[]{});
    }

    public ATCommandRequest setM1(int pwm1_output_level) {
        return new ATCommandRequest(ATCommand.M1, to2Bytes(pwm1_output_level));
    }

    public ATCommandRequest queryM1() {
        return new ATCommandRequest(ATCommand.M1, new int[]{});
    }

    public ATCommandRequest setLT(int assoc_led_blink_time) {
        return new ATCommandRequest(ATCommand.LT, to1Byte(assoc_led_blink_time));
    }

    public ATCommandRequest queryLT() {
        return new ATCommandRequest(ATCommand.LT, new int[]{});
    }

    public ATCommandRequest CB() {
        return new ATCommandRequest(ATCommand.CB, new int[]{});
    }

    public ATCommandRequest setIR(long io_sample_rate) {
        return new ATCommandRequest(
                ATCommand.IR,
                to2Bytes(io_sample_rate));
    }

    public ATCommandRequest queryIR() {
        return new ATCommandRequest(ATCommand.IR, new int[]{});
    }

    public ATCommandRequest setIF(int sample_from_sleep) {
        return new ATCommandRequest(ATCommand.IF, to1Byte(sample_from_sleep));
    }

    public ATCommandRequest queryIF() {
        return new ATCommandRequest(ATCommand.IF, new int[]{});
    }

    public ATCommandRequest setRR(int mac_retries) {
        return new ATCommandRequest(ATCommand.RR, to1Byte(mac_retries));
    }

    public ATCommandRequest queryRR() {
        return new ATCommandRequest(ATCommand.RR, new int[]{});
    }

    public ATCommandRequest setMT(int multiple_transmissions) {
        return new ATCommandRequest(ATCommand.MT, to1Byte(multiple_transmissions));
    }

    public ATCommandRequest queryMT() {
        return new ATCommandRequest(ATCommand.MT, new int[]{});
    }

    public ATCommandRequest setIC(Set<Digital_IO_Pin> enabledDigitalChangeDetectionIOPins) {

        int value = 0;
        for (Digital_IO_Pin digitalIOPin : enabledDigitalChangeDetectionIOPins) {
            value = value | (0x1 << digitalIOPin.getValue());
        }
        return new ATCommandRequest(ATCommand.IC, new int[]{value});
    }

    public ATCommandRequest queryIC() {
        return new ATCommandRequest(ATCommand.IC, new int[]{});
    }

    public ATCommandRequest IS() {
        return new ATCommandRequest(ATCommand.IS, new int[]{});
    }

    public ATCommandRequest _1S() {
        return new ATCommandRequest(ATCommand._1S, new int[]{});
    }

    /* Diagnostics */
    public ATCommandRequest queryVR() {
        return new ATCommandRequest(ATCommand.VR, new int[]{});
    }

    public ATCommandRequest queryHV() {
        return new ATCommandRequest(ATCommand.HV, new int[]{});
    }

    public ATCommandRequest queryER() {
        return new ATCommandRequest(ATCommand.ER, new int[]{});
    }

    public ATCommandRequest queryGD() {
        return new ATCommandRequest(ATCommand.GD, new int[]{});
    }

    public ATCommandRequest queryTR() {
        return new ATCommandRequest(ATCommand.TR, new int[]{});
    }

    public ATCommandRequest queryAI() {
        return new ATCommandRequest(ATCommand.AI, new int[]{});
    }

    /* AT Command Options */
    public ATCommandRequest setCT(long timeout) {
        return new ATCommandRequest(ATCommand.CT,
                to2Bytes(timeout));
    }

    public ATCommandRequest queryCT() {
        return new ATCommandRequest(ATCommand.CT, new int[]{});
    }

    public ATCommandRequest CN() {
        return new ATCommandRequest(ATCommand.CN, new int[]{});
    }

    public ATCommandRequest setGT(long time) {
        return new ATCommandRequest(ATCommand.GT,
                to2Bytes(time));
    }

    //TODO: Check if this is valid
    public ATCommandRequest queryGT() {
        return new ATCommandRequest(ATCommand.GT, new int[]{});
    }

    public ATCommandRequest setCC(int character) {
        return new ATCommandRequest(ATCommand.CC, to1Byte(character));
    }

    public ATCommandRequest queryCC() {
        return new ATCommandRequest(ATCommand.CC, new int[]{});
    }
    /* Node Identification */

    public ATCommandRequest setID(int pan_id) {
        return new ATCommandRequest(ATCommand.ID, to2Bytes(pan_id));
    }

    public ATCommandRequest queryID() {
        return new ATCommandRequest(ATCommand.ID, new int[]{});
    }

    public ATCommandRequest setNT(int node_discover_timeout) {
        return new ATCommandRequest(ATCommand.NT, to2Bytes(node_discover_timeout));
    }

    public ATCommandRequest queryNT() {
        return new ATCommandRequest(ATCommand.NT, new int[]{});
    }

    public ATCommandRequest setNI(String nodeIdentifier) {
        int[] bytes = new int[nodeIdentifier.length()];
        for (int i = 0; i < nodeIdentifier.toCharArray().length; i++) {
            bytes[i] = nodeIdentifier.toCharArray()[i];
        }
        return new ATCommandRequest(ATCommand.NI, bytes);
    }

    public ATCommandRequest queryNI() {
        return new ATCommandRequest(ATCommand.NI, new int[]{});
    }

    public ATCommandRequest DN(String nodeIdentifier) {
        int[] bytes = new int[nodeIdentifier.length()];
        for (int i = 0; i < nodeIdentifier.toCharArray().length; i++) {
            bytes[i] = nodeIdentifier.toCharArray()[i];
        }
        return new ATCommandRequest(ATCommand.DN, bytes);
    }

    public ATCommandRequest ND() {
        return new ATCommandRequest(ATCommand.ND, new int[]{});
    }

    public ATCommandRequest setNO(Network_Discovery_Option network_Discovery_Options) {
        return new ATCommandRequest(ATCommand.NO, new int[]{network_Discovery_Options.getValue()});
    }

    public ATCommandRequest queryNO() {
        return new ATCommandRequest(ATCommand.NO, new int[]{});
    }
//    /* Sleep Commands */

    public ATCommandRequest setSM(Sleep_Mode sleepMode) {
        return new ATCommandRequest(ATCommand.SM, new int[]{sleepMode.getValue()});
    }

    public ATCommandRequest querySM() {
        return new ATCommandRequest(ATCommand.SM, new int[]{});
    }

    public ATCommandRequest setSO(Sleep_Option sleepOptions) {
        return new ATCommandRequest(ATCommand.SO, new int[]{sleepOptions.getValue()});
    }

    public ATCommandRequest querySO() {
        return new ATCommandRequest(ATCommand.SO, new int[]{});
    }

    public ATCommandRequest setST(long value) {
        return new ATCommandRequest(ATCommand.ST, to3Bytes(value));
    }

    public ATCommandRequest queryST() {
        return new ATCommandRequest(ATCommand.ST, new int[]{});
    }

    public ATCommandRequest setSP(long value) {
        return new ATCommandRequest(ATCommand.SP, to3Bytes(value));
    }

    public ATCommandRequest querySP() {
        return new ATCommandRequest(ATCommand.SP, new int[]{});
    }

    public ATCommandRequest queryMS() {
        return new ATCommandRequest(ATCommand.MS, new int[]{});
    }

    public ATCommandRequest querySQ() {
        return new ATCommandRequest(ATCommand.SQ, new int[]{});
    }

    public ATCommandRequest querySS() {
        return new ATCommandRequest(ATCommand.SS, new int[]{});
    }

    public ATCommandRequest queryOS() {
        return new ATCommandRequest(ATCommand.OS, new int[]{});
    }

    public ATCommandRequest queryOW() {
        return new ATCommandRequest(ATCommand.OW, new int[]{});
    }

    public ATCommandRequest queryWH() {
        return new ATCommandRequest(ATCommand.WH, new int[]{});
    }

    public ATCommandRequest setWH(long value) {
        return new ATCommandRequest(ATCommand.WH, to2Bytes(value));
    }

    /* Encryption and Security */
    public ATCommandRequest setEE(Encryption_Enable encryption_Enable) {
        return new ATCommandRequest(ATCommand.EE, new int[]{encryption_Enable.getValue()});
    }

    public ATCommandRequest queryEE() {
        return new ATCommandRequest(ATCommand.EE, new int[]{});
    }

    public ATCommandRequest setKY(int[] encryption_key) {
        return new ATCommandRequest(ATCommand.KY, encryption_key);
    }

    public ATCommandRequest queryKY() {
        return new ATCommandRequest(ATCommand.KY, new int[]{});
    }

    /* Networking */
    public ATCommandRequest setCH(int channel) {
        return new ATCommandRequest(ATCommand.CH, to1Byte(channel));
    }

    public ATCommandRequest queryCH() {
        return new ATCommandRequest(ATCommand.CH, new int[]{});
    }

    public ATCommandRequest setCE(Routing_Type routing_Type) {
        return new ATCommandRequest(ATCommand.CE, new int[]{routing_Type.getValue()});
    }

    public ATCommandRequest queryCE() {
        return new ATCommandRequest(ATCommand.CE, new int[]{});
    }

    /* RF Interfacing */
    public ATCommandRequest setPL(Power_Level power_Level) {
        return new ATCommandRequest(ATCommand.PL, new int[]{power_Level.getValue()});
    }

    public ATCommandRequest queryPL() {
        return new ATCommandRequest(ATCommand.PL, new int[]{});
    }

    public ATCommandRequest setCA(int cca_threshold) {
        return new ATCommandRequest(ATCommand.CA, to1Byte(cca_threshold));
    }

    public ATCommandRequest queryCA() {
        return new ATCommandRequest(ATCommand.CA, new int[]{});
    }

    public ATCommandRequest clearDB() {
        return new ATCommandRequest(ATCommand.DB, new int[]{0});
    }

    public ATCommandRequest queryDB() {
        return new ATCommandRequest(ATCommand.DB, new int[]{});
    }

//    /* DigiMesh */
    public ATCommandRequest setNH(int value) {
        return new ATCommandRequest(ATCommand.NH, to1Byte(value));
    }

    public ATCommandRequest queryNH() {
        return new ATCommandRequest(ATCommand.NH, new int[]{});
    }

    public ATCommandRequest setNN(int value) {
        return new ATCommandRequest(ATCommand.NN, to1Byte(value));
    }

    public ATCommandRequest queryNN() {
        return new ATCommandRequest(ATCommand.NN, new int[]{});
    }

    public ATCommandRequest setNQ(int value) {
        return new ATCommandRequest(ATCommand.NQ, to1Byte(value));
    }

    public ATCommandRequest queryNQ() {
        return new ATCommandRequest(ATCommand.NQ, new int[]{});
    }

    public ATCommandRequest setMR(int value) {
        return new ATCommandRequest(ATCommand.MR, to1Byte(value));
    }

    public ATCommandRequest queryMR() {
        return new ATCommandRequest(ATCommand.MR, new int[]{});
    }

    private int[] to1Byte(int i) {
        return to1Byte((long) i);
    }

    private int[] to2Bytes(int i) {
        return to2Bytes((long) i);
    }

    private int[] to3Bytes(int i) {
        return to3Bytes((long) i);
    }

    private int[] to4Bytes(int i) {
        return to4Bytes((long) i);
    }

    private int[] to1Byte(long l) {
        return new int[]{
                    (int) (l)};
    }

    private int[] to2Bytes(long l) {
        return new int[]{
                    (int) (l >> 8),
                    (int) (l)};
    }

    private int[] to3Bytes(long l) {
        return new int[]{
                    (int) (l >> 16),
                    (int) (l >> 8),
                    (int) (l)};
    }

    private int[] to4Bytes(long l) {
        return new int[]{
                    (int) (l >> 24),
                    (int) (l >> 16),
                    (int) (l >> 8),
                    (int) (l)};
    }
}
