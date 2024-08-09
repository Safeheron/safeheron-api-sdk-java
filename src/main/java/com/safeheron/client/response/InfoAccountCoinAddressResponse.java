package com.safeheron.client.response;

import lombok.Data;

/**
 * @author safeheron
 */
@Data
public class InfoAccountCoinAddressResponse {
    /**
     * Coin receiving address
     */
    private String address;

    /**
     * Address type
     */
    private String addressType;

    /**
     * The balance of the coin address
     */
    private String addressBalance;

    /**
     * Account key
     */
    private String accountKey;


    /**
     * BIP44 derivation path
     */
    private String derivePath;
}

