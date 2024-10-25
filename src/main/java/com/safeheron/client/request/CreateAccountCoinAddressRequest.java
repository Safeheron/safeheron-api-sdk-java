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

    /**
     * Merchant unique business ID (100 characters max)
     * The customerRefId uniquely represents an address group. In the case of duplicate customerRefId values (for example, when resubmitting due to request timeouts or other errors), the data returned by the interface will remain consistent
     */
    private String customerRefId;
}
