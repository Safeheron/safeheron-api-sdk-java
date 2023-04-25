package com.safeheron.client.request;


import lombok.Data;

/**
 * MPC Sign Transaction List
 *
 * @author safeheron
 */
@Data
public class ListMPCSignTransactionsRequest extends LimitSearch {
    /**
     * Start time for creating a transaction, UNIX timestamp (ms) (If no value is provided, the default value is createTimeMax minus 24 hours)
     */
    private Long createTimeMin;

    /**
     * End time for creating a transaction, UNIX timestamp (ms) (If no value is provided, the default value is the current UTC time)
     */
    private Long createTimeMax;
}
