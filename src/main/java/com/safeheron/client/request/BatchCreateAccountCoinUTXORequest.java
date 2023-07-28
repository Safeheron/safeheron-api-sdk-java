package com.safeheron.client.request;

import lombok.Data;

/**
 * Batch Add Address Groups for UTXO-Based Coin request
 *
 * @author safeheron
 */
@Data
public class BatchCreateAccountCoinUTXORequest {
    /**
     * Coin key
     */
    private String coinKey;

    /**
     * Account key
     */
    private String accountKey;

    /**
     * The number, max is 100
     */
    private Long count;

    /**
     * Address group name, 30 characters max
     */
    private String addressGroupName;
}
