package com.safeheron.client.response;

import lombok.Data;

import java.util.List;

/**
 * @author safeheron
 */
@Data
public class KyaScreeningCreateResponse {
    /**
     * Screening request ID
     */
    private String screenId;

    /**
     * Screened address
     */
    private String address;

    /**
     * Chain type
     */
    private String chainType;

    /**
     * Blockchain network identifier
     */
    private String network;

    /**
     * Screening order list. One order per provider in providers
     */
    private List<Order> orders;

    /**
     * Creation time (UNIX ms timestamp)
     */
    private Long createTime;

    @Data
    public static class Order {
        /**
         * Screening order ID
         */
        private String screenOrderId;

        /**
         * Provider identifier: MistTrack / Elliptic / Chainalysis
         */
        private String provider;
    }
}
