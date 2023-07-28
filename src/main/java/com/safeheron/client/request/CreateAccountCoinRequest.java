package com.safeheron.client.request;

import lombok.Data;

/**
 * Add Coins to a Wallet Account request
 *
 * @author safeheron
 */
@Data
public class CreateAccountCoinRequest {
    /**
     * Coin key
     */
    private String coinKey;

    /**
     * Account key
     */
    private String accountKey;
}
