package com.safeheron.client.request;

import lombok.Data;

import java.util.List;

/**
 * Batch Add Coins to Wallet Accounts request
 *
 * @author safeheron
 */
@Data
public class BatchCreateAccountCoinRequest {
    /**
     * Coin key
     */
    private String coinKey;

    /**
     * Account key, max is 100
     */
    private List<String> accountKey;

    /**
     * Address group name, 30 characters max
     */
    private String addressGroupName;
}
