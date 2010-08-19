/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.google.code.xbeejavaapi;

import com.google.code.xbeejavaapi.api.ATCommandRequest;
import com.google.code.xbeejavaapi.api.Constants.AD0_DIO0_Configuration;
import com.google.code.xbeejavaapi.api.Constants.AD1_DIO1_Configuration;
import com.google.code.xbeejavaapi.api.Constants.AD2_DIO2_Configuration;
import com.google.code.xbeejavaapi.api.Constants.AD3_DIO3_Configuration;
import com.google.code.xbeejavaapi.api.Constants.AD4_DIO4_Configuration;
import com.google.code.xbeejavaapi.api.Constants.AD5_DIO5_Configuration;
import com.google.code.xbeejavaapi.api.Constants.APIMode;
import com.google.code.xbeejavaapi.api.Constants.APIOutputFormat;
import com.google.code.xbeejavaapi.api.Constants.Association_Indication;
import com.google.code.xbeejavaapi.api.Constants.BaudRate;
import com.google.code.xbeejavaapi.api.Constants.DIO12_Configuration;
import com.google.code.xbeejavaapi.api.Constants.DIO6Configuration;
import com.google.code.xbeejavaapi.api.Constants.DIO7Configuration;
import com.google.code.xbeejavaapi.api.Constants.DIO8_Configuration;
import com.google.code.xbeejavaapi.api.Constants.DIO9_Configuration;
import com.google.code.xbeejavaapi.api.Constants.Digital_IO_Pin;
import com.google.code.xbeejavaapi.api.Constants.Network_Discovery_Option;
import com.google.code.xbeejavaapi.api.Constants.PWM0_DIO10_Configuration;
import com.google.code.xbeejavaapi.api.Constants.PWM1_DIO11_Configuration;
import com.google.code.xbeejavaapi.api.Constants.Parity;
import com.google.code.xbeejavaapi.api.Constants.Power_Level;
import com.google.code.xbeejavaapi.api.Constants.Pullup_Resistor;
import com.google.code.xbeejavaapi.api.Constants.Routing_Type;
import com.google.code.xbeejavaapi.api.Constants.Sleep_Mode;
import com.google.code.xbeejavaapi.api.Constants.Sleep_Option;
import com.google.code.xbeejavaapi.api.Constants.Sleep_Status_Value;
import com.google.code.xbeejavaapi.api.DiscoveredNode;
import com.google.code.xbeejavaapi.api.XBeeAddress;
import com.google.code.xbeejavaapi.api.XBeeSerialNumber;
import com.google.code.xbeejavaapi.exception.XBeeOperationFailedException;
import java.util.Set;

/**
 *
 * @author David Miguel Antunes <davidmiguel [ at ] antunes.net>
 */
public interface XBee {

    void applyChanges() throws XBeeOperationFailedException;

    void clearMissedSyncCount(long sleepPeriod) throws XBeeOperationFailedException;

    /**
     * Disconnects this instance from hardware (normally a serial port).
     * No other method may be called after this one.
     */
    void disconnect();

    XBeeAddress discoverNode(String node) throws XBeeOperationFailedException;

    void forceSample() throws XBeeOperationFailedException;

    AD0_DIO0_Configuration getAD0DIO0Configuration() throws XBeeOperationFailedException;

    AD1_DIO1_Configuration getAD1DIO1Configuration() throws XBeeOperationFailedException;

    AD2_DIO2_Configuration getAD2DIO2Configuration() throws XBeeOperationFailedException;

    AD3_DIO3_Configuration getAD3DIO3Configuration() throws XBeeOperationFailedException;

    AD4_DIO4_Configuration getAD4DIO4Configuration() throws XBeeOperationFailedException;

    AD5_DIO5_Configuration getAD5DIO5Configuration() throws XBeeOperationFailedException;

    APIMode getAPIMode() throws XBeeOperationFailedException;

    APIOutputFormat getAPIOutputFormat() throws XBeeOperationFailedException;

    long getAssocLEDBlinkTime() throws XBeeOperationFailedException;

    Association_Indication getAssociationIndication() throws XBeeOperationFailedException;

    BaudRate getBaudRate() throws XBeeOperationFailedException;

    long getCcaThreshold() throws XBeeOperationFailedException;

    long getChannel() throws XBeeOperationFailedException;

    long getClusterIdentifier() throws XBeeOperationFailedException;

    long getCommandCharacter() throws XBeeOperationFailedException;

    long getCommandModeTimeout() throws XBeeOperationFailedException;

    DIO12_Configuration getDIO12Configuration() throws XBeeOperationFailedException;

    DIO6Configuration getDIO6Configuration() throws XBeeOperationFailedException;

    DIO7Configuration getDIO7Configuration() throws XBeeOperationFailedException;

    DIO8_Configuration getDIO8Configuration() throws XBeeOperationFailedException;

    DIO9_Configuration getDIO9Configuration() throws XBeeOperationFailedException;

    XBeeAddress getDestinationAddress() throws XBeeOperationFailedException;

    long getDestinationEndpoint() throws XBeeOperationFailedException;

    long getDeviceTypeIdentifier() throws XBeeOperationFailedException;

    Set<Digital_IO_Pin> getEnabledIODigitalChangeDetectionPins() throws XBeeOperationFailedException;

    Set<Pullup_Resistor> getEnabledPullupResistors() throws XBeeOperationFailedException;

    long getEncryptionEnabled() throws XBeeOperationFailedException;

    long getFirmwareVersion() throws XBeeOperationFailedException;

    long getFlowControlThreshold() throws XBeeOperationFailedException;

    long getGuardTimes() throws XBeeOperationFailedException;

    long getHardwareVersion() throws XBeeOperationFailedException;

    long getHoppingChannel() throws XBeeOperationFailedException;

    long getIOSampleRate() throws XBeeOperationFailedException;

    long getMacRetries() throws XBeeOperationFailedException;

    long getMaximumRFPayloadBytes() throws XBeeOperationFailedException;

    long getMissedSyncCount() throws XBeeOperationFailedException;

    long getMultipleTransmissions() throws XBeeOperationFailedException;

    Set<Network_Discovery_Option> getNetworkDiscoverOptions() throws XBeeOperationFailedException;

    long getNetworkIdentifier() throws XBeeOperationFailedException;

    long getNodeDiscoverTimeout() throws XBeeOperationFailedException;

    String getNodeIdentifier() throws XBeeOperationFailedException;

    long getNumberOfGoodPackets() throws XBeeOperationFailedException;

    long getNumberOfMissedSyncs() throws XBeeOperationFailedException;

    long getNumberOfRFErrors() throws XBeeOperationFailedException;

    long getNumberOfTransmissionErrors() throws XBeeOperationFailedException;

    long getOperationalSleepPeriod() throws XBeeOperationFailedException;

    long getOperationalWakePeriod() throws XBeeOperationFailedException;

    PWM0_DIO10_Configuration getPWM0DIO10Configuration() throws XBeeOperationFailedException;

    long getPWM0OutputLevel() throws XBeeOperationFailedException;

    PWM1_DIO11_Configuration getPWM1DIO11Configuration() throws XBeeOperationFailedException;

    long getPWM1OutputLevel() throws XBeeOperationFailedException;

    long getPacketizationTimeout() throws XBeeOperationFailedException;

    long getPanId() throws XBeeOperationFailedException;

    long getParity() throws XBeeOperationFailedException;

    Power_Level getPowerLevel() throws XBeeOperationFailedException;

    long getReceivedSignalStrength() throws XBeeOperationFailedException;

    Routing_Type getRoutingType() throws XBeeOperationFailedException;

    long getRssiPwmTimer() throws XBeeOperationFailedException;

    long getSampleFromSleepRate() throws XBeeOperationFailedException;

    XBeeSerialNumber getSerialNumber() throws XBeeOperationFailedException;

    Sleep_Mode getSleepMode() throws XBeeOperationFailedException;

    Set<Sleep_Option> getSleepOptions() throws XBeeOperationFailedException;

    long getSleepPeriod() throws XBeeOperationFailedException;

    Set<Sleep_Status_Value> getSleepStatus() throws XBeeOperationFailedException;

    long getSourceEndpoint() throws XBeeOperationFailedException;

    long getWakeHost() throws XBeeOperationFailedException;

    long getWakeTime() throws XBeeOperationFailedException;

    void pushCommissioningButton() throws XBeeOperationFailedException;

    void restoreDefaults() throws XBeeOperationFailedException;

    Set<DiscoveredNode> searchNodes() throws XBeeOperationFailedException;

    int sendATCommand(ATCommandRequest command) throws XBeeOperationFailedException;

    void setAD0DIO0Configuration(AD0_DIO0_Configuration config) throws XBeeOperationFailedException;

    void setAD1DIO1Configuration(AD1_DIO1_Configuration config) throws XBeeOperationFailedException;

    void setAD2DIO2Configuration(AD2_DIO2_Configuration config) throws XBeeOperationFailedException;

    void setAD3DIO3Configuration(AD3_DIO3_Configuration config) throws XBeeOperationFailedException;

    void setAD4DIO4Configuration(AD4_DIO4_Configuration config) throws XBeeOperationFailedException;

    void setAD5DIO5Configuration(AD5_DIO5_Configuration config) throws XBeeOperationFailedException;

    void setAPIMode(APIMode apiMode) throws XBeeOperationFailedException;

    void setAPIOutputFormat(APIOutputFormat apiOutputFormat) throws XBeeOperationFailedException;

    void setAssocLEDBlinkTime(long assocLEDBlinkTime) throws XBeeOperationFailedException;

    void setBaudRate(BaudRate baudRate) throws XBeeOperationFailedException;

    void setCcaThreshold(long ccaThreshold) throws XBeeOperationFailedException;

    void setChannel(long channel) throws XBeeOperationFailedException;

    void setClusterIdentifier(long clusterIdentifier) throws XBeeOperationFailedException;

    void setCommandCharacter(long commandCharacter) throws XBeeOperationFailedException;

    void setCommandModeTimeout(long commandModeTimeout) throws XBeeOperationFailedException;

    void setDIO12Configuration(DIO12_Configuration config) throws XBeeOperationFailedException;

    void setDIO6Configuration(DIO6Configuration config) throws XBeeOperationFailedException;

    void setDIO7Configuration(DIO7Configuration config) throws XBeeOperationFailedException;

    void setDIO8Configuration(DIO8_Configuration config) throws XBeeOperationFailedException;

    void setDIO9Configuration(DIO9_Configuration config) throws XBeeOperationFailedException;

    void setDestinationAddress(XBeeAddress address) throws XBeeOperationFailedException;

    void setDestinationEndpoint(long destinationEndpoint) throws XBeeOperationFailedException;

    void setEnabledIODigitalChangeDetectionPins(Set<Digital_IO_Pin> enabledDigitalChangeDetectionIOPins) throws XBeeOperationFailedException;

    void setEnabledPullupResistors(Set<Pullup_Resistor> enabledPullupResistors) throws XBeeOperationFailedException;

    void setEncryptionEnabled(boolean encryptionEnabled) throws XBeeOperationFailedException;

    void setEncryptionKey(int[] encryptionKey) throws XBeeOperationFailedException;

    void setFlowControlThreshold(long flowControlThreshold) throws XBeeOperationFailedException;

    void setGuardTimes(long guardTimes) throws XBeeOperationFailedException;

    void setHoppingChannel(long hoppingChannel) throws XBeeOperationFailedException;

    void setIOSampleRate(long ioSampleRate) throws XBeeOperationFailedException;

    void setMacRetries(long macRetries) throws XBeeOperationFailedException;

    void setMaximumRFPayloadBytes(long maximumRFPayloadBytes) throws XBeeOperationFailedException;

    void setMultipleTransmissions(long multipleTransmissions) throws XBeeOperationFailedException;

    void setNetworkDiscoverOptions(Set<Network_Discovery_Option> networkDiscoveryOptions) throws XBeeOperationFailedException;

    void setNetworkIdentifier(long networkIdentifier) throws XBeeOperationFailedException;

    void setNodeDiscoverTimeout(long nodeDiscoverTimeout) throws XBeeOperationFailedException;

    void setNodeIdentifier(String nodeIdentifier) throws XBeeOperationFailedException;

    void setPWM0DIO10Configuration(PWM0_DIO10_Configuration config) throws XBeeOperationFailedException;

    void setPWM0OutputLevel(long pwm0OutputLevel) throws XBeeOperationFailedException;

    void setPWM1DIO11Configuration(PWM1_DIO11_Configuration config) throws XBeeOperationFailedException;

    void setPWM1OutputLevel(long pwm0OutputLevel) throws XBeeOperationFailedException;

    void setPacketizationTimeout(long packetizationTimeout) throws XBeeOperationFailedException;

    void setPanId(long panId) throws XBeeOperationFailedException;

    void setParity(Parity parity) throws XBeeOperationFailedException;

    void setPowerLevel(Power_Level powerLevel) throws XBeeOperationFailedException;

    void setRoutingType(Routing_Type routingType) throws XBeeOperationFailedException;

    void setRssiPwmTimer(long rssiPwmTimer) throws XBeeOperationFailedException;

    void setSampleFromSleepRate(long sampleFromSleepRate) throws XBeeOperationFailedException;

    void setSleepMode(Sleep_Mode sleepMode) throws XBeeOperationFailedException;

    void setSleepOptions(Set<Sleep_Option> sleepOptions) throws XBeeOperationFailedException;

    void setSleepPeriod(long sleepPeriod) throws XBeeOperationFailedException;

    void setSourceEndpoint(long sourceEndpoint) throws XBeeOperationFailedException;

    void setWakeHost(long wakeHost) throws XBeeOperationFailedException;

    void setWakeTime(long wakeTime) throws XBeeOperationFailedException;

    void softwareReset() throws XBeeOperationFailedException;

    void write() throws XBeeOperationFailedException;

}
