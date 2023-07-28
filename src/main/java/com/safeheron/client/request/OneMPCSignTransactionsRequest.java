package com.safeheron.client.request;


import lombok.Data;

/**
 * Retrieve a Single MPC Sign Transaction
 *
 * @author safeheron
 */
@Data
public class OneMPCSignTransactionsRequest {
    /**
     * Transaction key
     */
    private String txKey;

    /**
     * Merchant unique business ID (100 characters max)
     */
    private String customerRefId;
}
