package com.safeheron.client.response;

import lombok.Data;

/**
 * @author safeheron
 */

@Data
public class RiskDetail {

    /**
     * Risk type:
     * Malicious
     * Suspected Malicious
     * High Risk
     * Medium Risk
     */
    private String type;

    /**
     * Risk label
     */
    private String label;

    /**
     * Wallet address
     */
    private String address;

    /**
     * Transaction amount (USD)
     */
    private String volume;

    /**
     * Percentage of transaction amount
     */
    private String percent;
}
