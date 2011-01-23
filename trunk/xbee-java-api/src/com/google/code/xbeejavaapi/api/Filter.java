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
public class Filter {

    private static final Logger logger = Logger.getLogger(Filter.class);
    private boolean filterSenderAddress;
    private XBeeAddress senderAddress;
    private boolean filterSourceEndpoint;
    private int sourceEndpoint;
    private boolean filterDestinationEndpoint;
    private int destinationEndpoint;
    private boolean filterClusterId;
    private int clusterId;
    private boolean filterProfileId;
    private int profileId;

    public Filter(
            boolean filterSenderAddress, XBeeAddress senderAddress,
            boolean filterSourceEndpoint, int sourceEndpoint,
            boolean filterDestinationEndpoint, int destinationEndpoint,
            boolean filterClusterId, int clusterId,
            boolean filterProfileId, int profileId) {
        this.filterSenderAddress = filterSenderAddress;
        this.senderAddress = senderAddress;
        this.filterSourceEndpoint = filterSourceEndpoint;
        this.sourceEndpoint = sourceEndpoint;
        this.filterDestinationEndpoint = filterDestinationEndpoint;
        this.destinationEndpoint = destinationEndpoint;
        this.filterClusterId = filterClusterId;
        this.clusterId = clusterId;
        this.filterProfileId = filterProfileId;
        this.profileId = profileId;
    }

    public boolean matches(
            XBeeAddress senderAddress,
            int sourceEndpoint,
            int destinationEndpoint,
            int clusterId,
            int profileId) {

        if (filterSenderAddress
                && !senderAddress.equals(this.senderAddress)) {
            return false;
        }
        if (filterSourceEndpoint
                && !(sourceEndpoint == this.sourceEndpoint)) {
            return false;
        }
        if (filterDestinationEndpoint
                && !(destinationEndpoint == this.destinationEndpoint)) {
            return false;
        }
        if (filterClusterId
                && !(clusterId == this.clusterId)) {
            return false;
        }
        if (filterProfileId
                && !(profileId == this.profileId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Filter{" + "filterSenderAddress=" + filterSenderAddress + "senderAddress=" + senderAddress + "filterSourceEndpoint=" + filterSourceEndpoint + "sourceEndpoint=" + sourceEndpoint + "filterDestinationEndpoint=" + filterDestinationEndpoint + "destinationEndpoint=" + destinationEndpoint + "filterClusterId=" + filterClusterId + "clusterId=" + clusterId + "filterProfileId=" + filterProfileId + "profileId=" + profileId + '}';
    }
}
