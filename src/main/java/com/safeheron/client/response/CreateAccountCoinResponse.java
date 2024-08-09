package com.safeheron.client.response;

import lombok.Data;

/**
 * @author safeheron
 */
@Data
public class CreateAccountCoinResponse {
    /**
     * Coin receiving address
     */
    private String address;

    /**
     * addressType
     */
    private String addressType;

    /**
     * BIP44 derivation path
     */
    private String derivePath;
}
