package com.safeheron.client.response;

import lombok.Data;

/**
 * @author safeheron
 */
@Data
public class CreateAccountCoinAddressResponse {
    /**
     * Coin receiving address
     */
    private String address;

    /**
     * addressType
     */
    private String addressType;

    /**
     * amlLock
     */
    private String amlLock;
}

