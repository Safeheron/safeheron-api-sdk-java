package com.safeheron.client.request;

import lombok.Data;

/**
 * List Coins Within a Wallet Account request
 *
 * @author safeheron
 */
@Data
public class ListAccountCoinRequest {
    /**
     * Account key
     */
    private String accountKey;
}
