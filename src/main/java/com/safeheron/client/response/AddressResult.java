package com.safeheron.client.response;

import lombok.Data;

/**
 * @author safeheron
 */
@Data
public class AddressResult {
    /**
     * Coin receiving address
     */
    private String address;

    /**
     * addressType
     */
    private String addressType;

    /**
     * The balance of this coin address
     */
    private String addressBalance;

    /**
     * amlLock
     */
    private String amlLock;
}
