/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.google.code.xbeejavaapi.api;

import java.nio.LongBuffer;
import org.apache.log4j.Logger;
import com.google.code.xbeejavaapi.api.Constants.*;

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
                new int[]{
                    (int) (addressHigh >> 24),
                    (int) (addressHigh >> 16),
                    (int) (addressHigh >> 8),
                    (int) (addressHigh)});
    }

    public ATCommandRequest queryDH() {
        return new ATCommandRequest(ATCommand.DH, new int[]{});
    }

    public ATCommandRequest setDL(long addressLow) {
        return new ATCommandRequest(
                ATCommand.DL,
                new int[]{
                    (int) (addressLow >> 24),
                    (int) (addressLow >> 16),
                    (int) (addressLow >> 8),
                    (int) (addressLow)});
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
        return new ATCommandRequest(ATCommand.HP, new int[]{hoppingChannel});
    }

    public ATCommandRequest queryHP() {
        return new ATCommandRequest(ATCommand.HP, new int[]{});
    }

    public ATCommandRequest setSE(int sourceEndpoint) {
        return new ATCommandRequest(ATCommand.SE, new int[]{sourceEndpoint});
    }

    public ATCommandRequest querySE() {
        return new ATCommandRequest(ATCommand.SE, new int[]{});
    }

    public ATCommandRequest setDE(int destinationEndpoint) {
        return new ATCommandRequest(ATCommand.DE, new int[]{destinationEndpoint});
    }

    public ATCommandRequest queryDE() {
        return new ATCommandRequest(ATCommand.DE, new int[]{});
    }

    public ATCommandRequest setCL(long clusterIdentifier) {
        return new ATCommandRequest(
                ATCommand.CL,
                new int[]{
                    (int) (clusterIdentifier >> 8),
                    (int) (clusterIdentifier)});
    }

    public ATCommandRequest queryCL() {
        return new ATCommandRequest(ATCommand.CL, new int[]{});
    }

    public ATCommandRequest setNP(long maximumNFPayloadBytes) {
        return new ATCommandRequest(
                ATCommand.NP,
                new int[]{
                    (int) (maximumNFPayloadBytes >> 8),
                    (int) (maximumNFPayloadBytes)});
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
        return new ATCommandRequest(ATCommand.RO, new int[]{packetizationTimeout});
    }

    public ATCommandRequest queryRO() {
        return new ATCommandRequest(ATCommand.RO, new int[]{});
    }

    public ATCommandRequest setFT(int flowControlThreshold) {
        return new ATCommandRequest(ATCommand.FT, new int[]{flowControlThreshold});
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
//    P0("P0"),
//    P1("P1"),
//    P2("P2"),
//    D0("D0"),
//    D1("D1"),
//    D2("D2"),
//    D3("D3"),
//    D4("D4"),
//    D5("D5"),
//    D8("D8"),
//    D9("D9"),
//    RP("RP"),
//    PR("PR"),
//    M0("M0"),
//    M1("M1"),
//    LT("LT"),
//    CB("CB"),
//    IR("IR"),
//    IF("IF"),
//    RR("RR"),
//    MT("MT"),
//    IC("IC"),
//    IS("IS"),
//    _1S("1S"),
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

    public ATCommandRequest setRP(int value) {
        return new ATCommandRequest(ATCommand.RP, new int[]{value});
    }

    public ATCommandRequest queryRP() {
        return new ATCommandRequest(ATCommand.RP, new int[]{});
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
                new int[]{
                    (int) (timeout >> 8),
                    (int) (timeout)});
    }

    public ATCommandRequest queryCT() {
        return new ATCommandRequest(ATCommand.CT, new int[]{});
    }

    public ATCommandRequest CN() {
        return new ATCommandRequest(ATCommand.CN, new int[]{});
    }

    public ATCommandRequest setGT(long time) {
        return new ATCommandRequest(ATCommand.GT,
                new int[]{
                    (int) (time >> 8),
                    (int) (time)});
    }

    //TODO: Check if this is valid
    public ATCommandRequest queryGT() {
        return new ATCommandRequest(ATCommand.GT, new int[]{});
    }

    public ATCommandRequest setCC(int character) {
        return new ATCommandRequest(ATCommand.CC, new int[]{character});
    }

    public ATCommandRequest queryCC() {
        return new ATCommandRequest(ATCommand.CC, new int[]{});
    }
//    /* Node Identification */
//    NT("NT"),
//    NI("NI"),
//    DN("DN"),
//    ND("ND"),
//    NO("NO"),
//    /* Sleep Commands */
//    SM("SM"),
//    SO("SO"),
//    ST("ST"),
//    SP("SP"),
//    MS("MS"),
//    SQ("SQ"),
//    SS("SS"),
//    OS("OS"),
//    OW("OW"),
//    WH("WH"),
//    /* Encryption and Security */
//    EE("EE"),
//    KY("KY"),
//    /* Networking */
//    CH("CH"),
//    ID("ID"),
//    CE("CE"),
//    /* RF Interfacing */
//    PL("PL"),
//    CA("CA"),
//    DB("DB"),
//    /* DigiMesh */
//    NH("NH"),
//    NN("NN"),
//    NQ("NQ"),
//    MR("MR");
}
