/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.google.code.xbeejavaapi.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author David Miguel Antunes <davidmiguel [ at ] antunes.net>
 */
public enum ATCommand {

    /* Special */
    WR("WR"),
    RE("RE"),
    FR("FR"),
    AC("AC"),
    VL("VL"),
    /* Addressing */
    DH("DH"),
    DL("DL"),
    DD("DD"),
    SH("SH"),
    SL("SL"),
    HP("HP"),
    SE("SE"),
    DE("DE"),
    CL("CL"),
    NP("NP"),
    /* Serial Interfacing (I/O) */
    AP("AP"),
    AO("AO"),
    BD("BD"),
    RO("RO"),
    FT("FT"),
    NB("NB"),
    D7("D7"),
    D6("D6"),
    /* I/O Commands */
    P0("P0"),
    P1("P1"),
    P2("P2"),
    D0("D0"),
    D1("D1"),
    D2("D2"),
    D3("D3"),
    D4("D4"),
    D5("D5"),
    D8("D8"),
    D9("D9"),
    RP("RP"),
    PR("PR"),
    M0("M0"),
    M1("M1"),
    LT("LT"),
    CB("CB"),
    IR("IR"),
    IF("IF"),
    RR("RR"),
    MT("MT"),
    IC("IC"),
    IS("IS"),
    _1S("1S"),
    /* Diagnostics */
    VR("VR"),
    HV("HV"),
    ER("ER"),
    GD("GD"),
    TR("TR"),
    AI("AI"),
    /* AT Command Options */
    CT("CT"),
    CN("CN"),
    GT("GT"),
    CC("CC"),
    /* Node Identification */
    NT("NT"),
    NI("NI"),
    DN("DN"),
    ND("ND"),
    NO("NO"),
    /* Sleep Commands */
    SM("SM"),
    SO("SO"),
    ST("ST"),
    SP("SP"),
    MS("MS"),
    SQ("SQ"),
    SS("SS"),
    OS("OS"),
    OW("OW"),
    WH("WH"),
    /* Encryption and Security */
    EE("EE"),
    KY("KY"),
    /* Networking */
    CH("CH"),
    ID("ID"),
    CE("CE"),
    /* RF Interfacing */
    PL("PL"),
    CA("CA"),
    DB("DB"),
    /* DigiMesh */
    NH("NH"),
    NN("NN"),
    NQ("NQ"),
    MR("MR");
    private String command;

    private ATCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
