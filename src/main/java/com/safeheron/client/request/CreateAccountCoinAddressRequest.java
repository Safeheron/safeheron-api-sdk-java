package com.safeheron.client.request;

import lombok.Data;

/**
 * Add Address Group for UTXO-Based Coin request
 *
 * @author safeheron
 */
@Data
public class CreateAccountCoinAddressRequest{
    /**
     * Coin key
     */
    private String coinKey;

    /**
     * Account key
     */
    private String accountKey;

    /**
     * Address group name, 30 characters max
     */
    private String addressGroupName;

    private String customerRefId;
}
