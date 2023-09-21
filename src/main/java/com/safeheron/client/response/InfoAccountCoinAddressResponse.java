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
     * The account is frozen by AML
     * YES: frozen
     * NO: unfrozen
     */
    private String amlLock;
}

