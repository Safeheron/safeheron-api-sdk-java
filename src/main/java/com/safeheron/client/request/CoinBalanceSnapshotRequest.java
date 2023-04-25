package com.safeheron.client.request;


import lombok.Data;

/**
 * Snapshot the Coin Balance request
 *
 * @author safeheron
 */
@Data
public class CoinBalanceSnapshotRequest {
    /**
     * Only supports querying data within the last 30 days, with the parameter a GMT+8 time in the format of yyyy-MM-dd provided.
     * Note: If the provided value is the current date (not a historical date), it will return the balance up to the current time.
     */
    private String gmt8Date;
}

