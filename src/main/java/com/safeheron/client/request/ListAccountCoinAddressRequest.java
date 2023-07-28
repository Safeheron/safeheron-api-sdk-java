package com.safeheron.client.request;

import lombok.Data;

/**
 * List Coin Address Group of a Wallet Account request
 *
 * @author safeheron
 */
@Data
public class ListAccountCoinAddressRequest extends PageSearch{
    /**
     * Coin key
     */
    private String coinKey;

    /**
     * Account key
     */
    private String accountKey;
}
