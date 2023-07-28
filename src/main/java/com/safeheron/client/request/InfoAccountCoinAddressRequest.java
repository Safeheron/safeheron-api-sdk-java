package com.safeheron.client.request;

import lombok.Data;

/**
 * Retrieve The Balance of an Address request
 *
 * @author safeheron
 */
@Data
public class InfoAccountCoinAddressRequest {
    /**
     * Coin key
     */
    private String coinKey;

    /**
     * Coin receiving address
     */
    private String address;
}
