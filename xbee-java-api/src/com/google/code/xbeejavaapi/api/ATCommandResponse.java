/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.google.code.xbeejavaapi.api;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import org.apache.log4j.Logger;
import static com.google.code.xbeejavaapi.api.Constants.*;

/**
 *
 * @author David Miguel Antunes <davidmiguel [ at ] antunes.net>
 */
public abstract class ATCommandResponse extends FrameWithID {

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

    public ATCommandResponse(int[] data) {
        super(data);
        this.commandStatus = CommandStatus.get(data[4]);
        this.command = ATCommand.getCommand(new String(new char[]{(char) data[2], (char) data[3]}));
        this.data = data;
    }

    public ATCommand getCommand() {
        return command;
    }

    public CommandStatus getCommandStatus() {
        return commandStatus;
    }

    public static abstract class NumericATCommandResponse extends ATCommandResponse {

        private long value;

        public NumericATCommandResponse(int[] data) {
            super(data);

            value = 0;

            if (data.length > 5) {
                value = data[5];

                for (int i = 6; i < data.length; i++) {
                    value = value << 8;
                    value = value | data[i];
                }
            }
        }

        public long getValue() {
            return value;
        }

        public void setValue(long value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.getClass().getSimpleName() + " with value = " + value;
        }
    }

    public static abstract class XBeeAddressATCommandResponse extends ATCommandResponse {

        private XBeeAddress address;

        public XBeeAddressATCommandResponse(int[] data) {
            super(data);

            long highBytes = data[5];

            for (int i = 6; i <= 8; i++) {
                highBytes = highBytes << 8;
                highBytes = highBytes | data[i];
            }

            long lowBytes = data[9];

            for (int i = 10; i <= 12; i++) {
                lowBytes = lowBytes << 8;
                lowBytes = lowBytes | data[i];
            }

            address = new XBeeAddress(highBytes, lowBytes);
        }

        public XBeeAddress getAddress() {
            return address;
        }

        @Override
        public String toString() {
            return this.getClass().getSimpleName() + " with address = " + address;
        }
    }

    public static abstract class TextATCommandResponse extends ATCommandResponse {

        private String text;

        public TextATCommandResponse(int[] data) {
            super(data);

            text = "";

            if (data.length > 5) {
                for (int i = 5; i < data.length; i++) {
                    text += (char) data[i];
                }
            }
        }

        public String getText() {
            return text;
        }

        @Override
        public String toString() {
            return this.getClass().getSimpleName() + " with text = \"" + text + "\"";
        }
    }

    public static abstract class EnumerationATCommandResponse<T extends Enum> extends NumericATCommandResponse {

        private T element;

        public EnumerationATCommandResponse(int[] data, Class e) {
            super(data);
            Method m;
            try {
                m = e.getMethod("values", new Class[0]);
                ValueBasedEnum[] values = (ValueBasedEnum[]) m.invoke(null, new Object[0]);
                for (int i = 0; i < values.length; i++) {
                    ValueBasedEnum valueBasedEnum = values[i];
                    if (valueBasedEnum.getValue() == getValue()) {
                        element = (T) valueBasedEnum;
                    }
                }
            } catch (Exception ex) {
                logger.error(ex + " at " + ex.getStackTrace()[0].toString());
            }
        }

        public T getElement() {
            return element;
        }

        @Override
        public String toString() {
            return this.getClass().getSimpleName() + " with element = " + element;
        }
    }

    public static abstract class BitFieldATCommandResponse<T extends Enum> extends NumericATCommandResponse {

        private Set<T> enabledBits = new HashSet<T>();

        public BitFieldATCommandResponse(int[] data, Class e, int nBits) {
            super(data);
            int[] bits = new int[nBits];
            for (int i = 0; i < nBits; i++) {
                bits[i] = i;
            }
            readEnabledBits(e, bits);
        }

        public BitFieldATCommandResponse(int[] data, Class e, int[] bits) {
            super(data);
            readEnabledBits(e, bits);
        }

        private void readEnabledBits(Class e, int[] bits) {
            Method m;
            try {
                m = e.getMethod("values", new Class[0]);
                ValueBasedEnum[] values = (ValueBasedEnum[]) m.invoke(null, new Object[0]);
                for (int idx = 0; idx < bits.length; idx++) {
                    int bit = bits[idx];
                    if ((getValue() & (0x1 << bit)) != 0) {
                        for (int j = 0; j < values.length; j++) {
                            ValueBasedEnum valueBasedEnum = values[j];
                            if (valueBasedEnum.getValue() == bit) {
                                enabledBits.add((T) valueBasedEnum);
                            }
                        }
                    }
                }
            } catch (Exception ex) {
                logger.error(ex + " at " + ex.getStackTrace()[0].toString());
            }
        }

        public Set<T> getEnabledBits() {
            return enabledBits;
        }

        @Override
        public String toString() {
            return this.getClass().getSimpleName();
        }
    }

    /* Special */
    public static class WR extends ATCommandResponse {

        public WR(int[] data) {
            super(data);
        }
    }

    public static class RE extends ATCommandResponse {

        public RE(int[] data) {
            super(data);
        }
    }

    public static class FR extends ATCommandResponse {

        public FR(int[] data) {
            super(data);
        }
    }

    public static class AC extends ATCommandResponse {

        public AC(int[] data) {
            super(data);
        }
    }

    public static class VL extends TextATCommandResponse {

        public VL(int[] data) {
            super(data);
        }
    }

    /* Addressing */
    public static class DH extends NumericATCommandResponse {

        public DH(int[] data) {
            super(data);
        }
    }

    public static class DL extends NumericATCommandResponse {

        public DL(int[] data) {
            super(data);
        }
    }

    public static class DD extends NumericATCommandResponse {

        public DD(int[] data) {
            super(data);
        }
    }

    public static class SH extends NumericATCommandResponse {

        public SH(int[] data) {
            super(data);
        }
    }

    public static class SL extends NumericATCommandResponse {

        public SL(int[] data) {
            super(data);
        }
    }

    public static class HP extends NumericATCommandResponse {

        public HP(int[] data) {
            super(data);
        }
    }

    public static class SE extends NumericATCommandResponse {

        public SE(int[] data) {
            super(data);
        }
    }

    public static class DE extends NumericATCommandResponse {

        public DE(int[] data) {
            super(data);
        }
    }

    public static class CI extends NumericATCommandResponse {

        public CI(int[] data) {
            super(data);
        }
    }

    public static class NP extends NumericATCommandResponse {

        public NP(int[] data) {
            super(data);
        }
    }
    /* Serial Interfacing (I/O) */

    public static class AP extends EnumerationATCommandResponse<APIMode> {

        public AP(int[] data) {
            super(data, APIMode.class);
        }
    }

    public static class AO extends EnumerationATCommandResponse<APIOutputFormat> {

        public AO(int[] data) {
            super(data, APIOutputFormat.class);
        }
    }

    public static class BD extends EnumerationATCommandResponse<BaudRate> {

        public BD(int[] data) {
            super(data, BaudRate.class);
        }
    }

    public static class RO extends NumericATCommandResponse {

        public RO(int[] data) {
            super(data);
        }
    }

    public static class FT extends NumericATCommandResponse {

        public FT(int[] data) {
            super(data);
        }
    }

    public static class NB extends EnumerationATCommandResponse<Parity> {

        public NB(int[] data) {
            super(data, Parity.class);
        }
    }

    public static class D7 extends EnumerationATCommandResponse<DIO7Configuration> {

        public D7(int[] data) {
            super(data, DIO7Configuration.class);
        }
    }

    public static class D6 extends EnumerationATCommandResponse<DIO6Configuration> {

        public D6(int[] data) {
            super(data, DIO6Configuration.class);
        }
    }
    /* I/O Commands */

    public static class P0 extends EnumerationATCommandResponse<PWM0_DIO10_Configuration> {

        public P0(int[] data) {
            super(data, PWM0_DIO10_Configuration.class);
        }
    }

    public static class P1 extends EnumerationATCommandResponse<PWM1_DIO11_Configuration> {

        public P1(int[] data) {
            super(data, PWM1_DIO11_Configuration.class);
        }
    }

    public static class P2 extends EnumerationATCommandResponse<DIO12_Configuration> {

        public P2(int[] data) {
            super(data, DIO12_Configuration.class);
        }
    }

    public static class D0 extends EnumerationATCommandResponse<AD0_DIO0_Configuration> {

        public D0(int[] data) {
            super(data, AD0_DIO0_Configuration.class);
        }
    }

    public static class D1 extends EnumerationATCommandResponse<AD1_DIO1_Configuration> {

        public D1(int[] data) {
            super(data, AD1_DIO1_Configuration.class);
        }
    }

    public static class D2 extends EnumerationATCommandResponse<AD2_DIO2_Configuration> {

        public D2(int[] data) {
            super(data, AD2_DIO2_Configuration.class);
        }
    }

    public static class D3 extends EnumerationATCommandResponse<AD3_DIO3_Configuration> {

        public D3(int[] data) {
            super(data, AD3_DIO3_Configuration.class);
        }
    }

    public static class D4 extends EnumerationATCommandResponse<AD4_DIO4_Configuration> {

        public D4(int[] data) {
            super(data, AD4_DIO4_Configuration.class);
        }
    }

    public static class D5 extends EnumerationATCommandResponse<AD5_DIO5_Configuration> {

        public D5(int[] data) {
            super(data, AD5_DIO5_Configuration.class);
        }
    }

    public static class D8 extends EnumerationATCommandResponse<DIO8_Configuration> {

        public D8(int[] data) {
            super(data, DIO8_Configuration.class);
        }
    }

    public static class D9 extends EnumerationATCommandResponse<DIO9_Configuration> {

        public D9(int[] data) {
            super(data, DIO9_Configuration.class);
        }
    }

    public static class RP extends NumericATCommandResponse {

        public RP(int[] data) {
            super(data);
        }
    }

    public static class PR extends BitFieldATCommandResponse<Pullup_Resistor> {

        public PR(int[] data) {
            super(data, Pullup_Resistor.class, 15);
        }
    }

    public static class M0 extends NumericATCommandResponse {

        public M0(int[] data) {
            super(data);
        }
    }

    public static class M1 extends NumericATCommandResponse {

        public M1(int[] data) {
            super(data);
        }
    }

    public static class LT extends NumericATCommandResponse {

        public LT(int[] data) {
            super(data);
        }
    }

    public static class CB extends ATCommandResponse {

        public CB(int[] data) {
            super(data);
        }
    }

    public static class IR extends NumericATCommandResponse {

        public IR(int[] data) {
            super(data);
        }
    }

    public static class IF extends NumericATCommandResponse {

        public IF(int[] data) {
            super(data);
        }
    }

    public static class RR extends NumericATCommandResponse {

        public RR(int[] data) {
            super(data);
        }
    }

    public static class MT extends NumericATCommandResponse {

        public MT(int[] data) {
            super(data);
        }
    }

    public static class IC extends BitFieldATCommandResponse<Digital_IO_Pin> {

        public IC(int[] data) {
            super(data, Digital_IO_Pin.class, 13);
        }
    }

    public static class IS extends ATCommandResponse {

        private HashMap<Digital_IO_Pin, Boolean> digitalIOState = new HashMap<Digital_IO_Pin, Boolean>();
        private HashMap<Analog_IO_Pin, Double> analogIOState = new HashMap<Analog_IO_Pin, Double>();

        public IS(int[] data) {
            super(data);

            int idx = 6;

            long enabledDigitalIOValue = data[idx++] << 8;
            enabledDigitalIOValue = enabledDigitalIOValue | data[idx++];

            List<Digital_IO_Pin> enabledDigitalIOPins = readEnabledBits(Digital_IO_Pin.class, 13, enabledDigitalIOValue);

            long enabledAnalogIOValue = data[idx++];

            List<Analog_IO_Pin> enabledAnalogIOPins = readEnabledBits(Analog_IO_Pin.class, 6, enabledAnalogIOValue);

            if (!enabledDigitalIOPins.isEmpty()) {
                long digitalIOStateValue = data[idx++] << 8;
                digitalIOStateValue = digitalIOStateValue | data[idx++];

                List<Digital_IO_Pin> tempEnabledDigitalIOState = readEnabledBits(Digital_IO_Pin.class, 13, digitalIOStateValue);

                for (Digital_IO_Pin digital_IO_Pin : enabledDigitalIOPins) {
                    if (tempEnabledDigitalIOState.contains(digital_IO_Pin)) {
                        digitalIOState.put(digital_IO_Pin, Boolean.TRUE);
                    } else {
                        digitalIOState.put(digital_IO_Pin, Boolean.FALSE);
                    }
                }
            }

            for (Analog_IO_Pin analog_IO_Pin : enabledAnalogIOPins) {
                long value = data[idx++] << 8;
                value = value | data[idx++];

                analogIOState.put(analog_IO_Pin, (double) value / 1024.0);
            }
        }

        private <T> List<T> readEnabledBits(Class e, int nBits, long value) {

            ArrayList<T> enabledBits = new ArrayList<T>();

            int[] bits = new int[nBits];
            for (int i = 0; i < nBits; i++) {
                bits[i] = i;
            }

            Method m;
            try {
                m = e.getMethod("values", new Class[0]);
                ValueBasedEnum[] values = (ValueBasedEnum[]) m.invoke(null, new Object[0]);
                for (int idx = 0; idx < bits.length; idx++) {
                    int bit = bits[idx];
                    if ((value & (0x1 << bit)) != 0) {
                        for (int j = 0; j < values.length; j++) {
                            ValueBasedEnum valueBasedEnum = values[j];
                            if (valueBasedEnum.getValue() == bit) {
                                enabledBits.add((T) valueBasedEnum);
                            }
                        }
                    }
                }
            } catch (Exception ex) {
                logger.error(ex + " at " + ex.getStackTrace()[0].toString());
            }

            return enabledBits;
        }

        public HashMap<Analog_IO_Pin, Double> getAnalogIOState() {
            return analogIOState;
        }

        public HashMap<Digital_IO_Pin, Boolean> getDigitalIOState() {
            return digitalIOState;
        }
    }

    public static class _1S extends IS {

        public _1S(int[] data) {
            super(data);
        }
    }

    /* Diagnostics */
    public static class VR extends NumericATCommandResponse {

        public VR(int[] data) {
            super(data);
        }
    }

    public static class HV extends NumericATCommandResponse {

        public HV(int[] data) {
            super(data);
        }
    }

    public static class ER extends NumericATCommandResponse {

        public ER(int[] data) {
            super(data);
        }
    }

    public static class GD extends NumericATCommandResponse {

        public GD(int[] data) {
            super(data);
        }
    }

    public static class TR extends NumericATCommandResponse {

        public TR(int[] data) {
            super(data);
        }
    }

    public static class AI extends EnumerationATCommandResponse<Association_Indication> {

        public AI(int[] data) {
            super(data, Association_Indication.class);
        }
    }

    /* AT Command Options */
    public static class CT extends NumericATCommandResponse {

        public CT(int[] data) {
            super(data);
        }
    }

    public static class CN extends ATCommandResponse {

        public CN(int[] data) {
            super(data);
        }
    }

    public static class GT extends NumericATCommandResponse {

        public GT(int[] data) {
            super(data);
        }
    }

    public static class CC extends NumericATCommandResponse {

        public CC(int[] data) {
            super(data);
        }
    }


    /* Node Identification */
    public static class ID extends NumericATCommandResponse {

        public ID(int[] data) {
            super(data);
        }
    }

    public static class NT extends NumericATCommandResponse {

        public NT(int[] data) {
            super(data);
        }
    }

    public static class NI extends TextATCommandResponse {

        public NI(int[] data) {
            super(data);
        }
    }

    public static class DN extends XBeeAddressATCommandResponse {

        public DN(int[] data) {
            super(data);
        }
    }

    //TODO: Complete:
    public static class ND extends ATCommandResponse {

        XBeeAddress address;
        String nodeIdentifier;
        long parentNetworkAddress;
        Device_Type deviceType;
        long status;
        long profileId;
        long manufacturerId;

        public ND(int[] data) {
            super(data);

            int i = 7;
            long sh = data[i++];

            for (int j = 0; j < 3; j++, i++) {
                sh = sh << 8;
                sh = sh | data[i];
            }

            long sl = data[i++];

            for (int j = 0; j < 3; j++, i++) {
                sl = sl << 8;
                sl = sl | data[i];
            }

            address = new XBeeAddress(sh, sl);

            nodeIdentifier = "";

            for (;; i++) {
                if (data[i] == 0) {
                    break;
                }
                nodeIdentifier += (char) data[i];
            }

            i++;

            parentNetworkAddress = (data[i] << 8) | data[i + 1];

            i += 2;

            for (int j = 0; j < Device_Type.values().length; j++) {
                Device_Type device_Type = Device_Type.values()[j];
                if (device_Type.getValue() == data[i]) {
                    deviceType = device_Type;
                }
            }

            i++;

            status = data[i];

            i++;

            profileId = (data[i] << 8) | data[i + 1];

            i += 2;

            manufacturerId = (data[i] << 8) | data[i + 1];
        }

        public XBeeAddress getAddress() {
            return address;
        }

        public Device_Type getDeviceType() {
            return deviceType;
        }

        public long getManufacturerId() {
            return manufacturerId;
        }

        public String getNodeIdentifier() {
            return nodeIdentifier;
        }

        public long getParentNetworkAddress() {
            return parentNetworkAddress;
        }

        public long getProfileId() {
            return profileId;
        }

        public long getStatus() {
            return status;
        }

        @Override
        public String toString() {
            return "Node" + "\n"
                    + "address=" + address + "\n"
                    + "nodeIdentifier=" + nodeIdentifier + "\n"
                    + "parentNetworkAddress=" + "0x" + Long.toHexString(parentNetworkAddress).toUpperCase() + "\n"
                    + "deviceType=" + deviceType + "\n"
                    + "status=" + "0x" + Long.toHexString(status).toUpperCase() + "\n"
                    + "profileId=" + "0x" + Long.toHexString(profileId).toUpperCase() + "\n"
                    + "manufacturerId=" + "0x" + Long.toHexString(manufacturerId).toUpperCase();
        }
    }

    public static class NO extends BitFieldATCommandResponse<Network_Discovery_Option> {

        public NO(int[] data) {
            super(data, Network_Discovery_Option.class, new int[]{1, 2});
        }
    }
    /* Sleep Commands */

    public static class SM extends EnumerationATCommandResponse<Sleep_Mode> {

        public SM(int[] data) {
            super(data, Sleep_Mode.class);
        }
    }

    public static class SO extends BitFieldATCommandResponse<Sleep_Option> {

        public SO(int[] data) {
            super(data, Sleep_Option.class, 6);
        }
    }

    public static class ST extends NumericATCommandResponse {

        public ST(int[] data) {
            super(data);
        }
    }

    public static class SP extends NumericATCommandResponse {

        public SP(int[] data) {
            super(data);
        }
    }

    public static class MS extends NumericATCommandResponse {

        public MS(int[] data) {
            super(data);
        }
    }

    public static class SQ extends NumericATCommandResponse {

        public SQ(int[] data) {
            super(data);
        }
    }

    public static class SS extends BitFieldATCommandResponse<Sleep_Status_Value> {

        public SS(int[] data) {
            super(data, Sleep_Status_Value.class, 7);
        }
    }

    public static class OS extends NumericATCommandResponse {

        public OS(int[] data) {
            super(data);
        }
    }

    public static class OW extends NumericATCommandResponse {

        public OW(int[] data) {
            super(data);
        }
    }

    public static class WH extends NumericATCommandResponse {

        public WH(int[] data) {
            super(data);
        }
    }
    /* Encryption and Security */

    public static class EE extends EnumerationATCommandResponse<Encryption_Enable> {

        public EE(int[] data) {
            super(data, Encryption_Enable.class);
        }
    }

    public static class KY extends ATCommandResponse {

        public KY(int[] data) {
            super(data);
        }
    }
    /* Networking */

    public static class CH extends NumericATCommandResponse {

        public CH(int[] data) {
            super(data);
        }
    }

    public static class CE extends EnumerationATCommandResponse<Routing_Type> {

        public CE(int[] data) {
            super(data, Routing_Type.class);
        }
    }
    /* RF Interfacing */

    public static class PL extends EnumerationATCommandResponse<Power_Level> {

        public PL(int[] data) {
            super(data, Power_Level.class);
        }
    }

    public static class CA extends NumericATCommandResponse {

        public CA(int[] data) {
            super(data);
        }
    }

    public static class DB extends NumericATCommandResponse {

        public DB(int[] data) {
            super(data);
        }
    }
    /* DigiMesh */

    public static class NH extends NumericATCommandResponse {

        public NH(int[] data) {
            super(data);
        }
    }

    public static class NN extends NumericATCommandResponse {

        public NN(int[] data) {
            super(data);
        }
    }

    public static class NQ extends NumericATCommandResponse {

        public NQ(int[] data) {
            super(data);
        }
    }

    public static class MR extends NumericATCommandResponse {

        public MR(int[] data) {
            super(data);
        }
    }
}
