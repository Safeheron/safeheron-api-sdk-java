package com.safeheron.client.response;

import lombok.Data;

import java.util.List;

/**
 * @author safeheron
 */
@Data
public class KyaScreeningOneResponse {
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
     * Overall status:
     * PROCESSING: Some orders still pending
     * FINISHED: All orders completed
     */
    private String status;

    /**
     * Creation time (UNIX ms timestamp)
     */
    private Long createTime;

    /**
     * Screening order list
     */
    private List<Order> orders;

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

        /**
         * Order status: PENDING / COMPLETED / FAILED / SKIPPED
         */
        private String status;

        /**
         * Risk level: LOW / MEDIUM / HIGH / SEVERE / UNKNOWN
         */
        private String riskLevel;

        /**
         * Completion time (UNIX ms timestamp). Only returned after terminal state
         */
        private Long completedAt;
    }
}
