package com.safeheron.client.response;

import lombok.Data;

/**
 * @author safeheron
 */
@Data
public class KyaScreeningOrderOneResponse {
    /**
     * Screening order ID
     */
    private String screenOrderId;

    /**
     * ID of the parent screening request
     */
    private String screenId;

    /**
     * Provider identifier: MistTrack / Elliptic / Chainalysis
     */
    private String provider;

    /**
     * Screened address
     */
    private String address;

    /**
     * Address type (auto-determined by Safeheron):
     * VAULT_ACCOUNT / WHITELISTING_ACCOUNT / ONE_TIME_ADDRESS
     */
    private String addressType;

    /**
     * Chain type
     */
    private String chainType;

    /**
     * Blockchain network identifier
     */
    private String network;

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

    /**
     * Screening initiation time (UNIX ms timestamp)
     */
    private Long createTime;

    /**
     * Provider's raw report (available after screening completes; structure varies by provider)
     */
    private Object payload;
}
