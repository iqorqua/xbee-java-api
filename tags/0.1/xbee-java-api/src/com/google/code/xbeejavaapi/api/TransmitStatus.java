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
public class TransmitStatus extends FrameWithID {

    public enum DeliveryStatus {

        Success(0x00),
        MacAckFailure(0x01),
        InvalidDestinationEndpoint(0x15),
        NetworkACKFailure(0x21),
        RouteNotFound(0x25);
        private int value;
        private static Map<Integer, DeliveryStatus> values = new HashMap<Integer, DeliveryStatus>();

        static {
            for (int i = 0; i < values().length; i++) {
                DeliveryStatus deliveryStatus = values()[i];
                values.put(deliveryStatus.value, deliveryStatus);
            }
        }

        private DeliveryStatus(int value) {
            this.value = value;
        }

        public static DeliveryStatus get(int value) {
            return values.get(value);
        }
    }

    public enum DiscoveryStatus {

        NoDiscoveryOverhead(0x00),
        RouteDiscovery(0x02);
        private int value;
        private static Map<Integer, DiscoveryStatus> values = new HashMap<Integer, DiscoveryStatus>();

        static {
            for (int i = 0; i < values().length; i++) {
                DiscoveryStatus discoveryStatus = values()[i];
                values.put(discoveryStatus.value, discoveryStatus);
            }
        }

        private DiscoveryStatus(int value) {
            this.value = value;
        }

        public static DiscoveryStatus get(int value) {
            return values.get(value);
        }
    }
    private static final Logger logger = Logger.getLogger(TransmitStatus.class);
    private int transmitRetryCount;
    private DeliveryStatus deliveryStatus;
    private DiscoveryStatus discoveryStatus;

    public TransmitStatus(int[] data) {
        super(data);
        transmitRetryCount = data[4];
        deliveryStatus = DeliveryStatus.get(data[5]);
        discoveryStatus = TransmitStatus.DiscoveryStatus.get(data[6]);
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public DiscoveryStatus getDiscoveryStatus() {
        return discoveryStatus;
    }

    public int getTransmitRetryCount() {
        return transmitRetryCount;
    }

    @Override
    public String toString() {
        return "TransmitStatus{" + "transmitRetryCount=" + transmitRetryCount + " deliveryStatus=" + deliveryStatus.toString() + " discoveryStatus=" + discoveryStatus.toString() + '}';
    }
}
