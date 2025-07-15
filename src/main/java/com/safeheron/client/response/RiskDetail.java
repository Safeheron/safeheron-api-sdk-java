package com.safeheron.client.response;

import lombok.Data;

/**
 * @author safeheron
 */

@Data
public class RiskDetail {

    /**
     * Risk type:
     * sanctioned_entity
     * illicit_activity
     * mixer
     * gambling
     * risk_exchange
     * bridge
     */
    private String riskType;

    /**
     * The name of the entity involved in the risk, example: garantex.io
     */
    private String entity;

    /**
     * How many hops to the risk entity, greater than or equal to 1
     */
    private String hopNum;

    /**
     * Risk exposure type:
     * direct
     * indirect
     */
    private String exposureType;

    /**
     * Transaction amount (USD)
     */
    private String volume;

    /**
     * Percentage of transaction amount
     */
    private String percent;
}
