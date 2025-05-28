package com.safeheron.client.request;


import lombok.Data;

/**
 * @author safeheron
 */
@Data
public class AmlCheckerRequestRequest {
    /**
     * Blockchain network, supports:
     * Bitcoin
     * Ethereum
     * Tron
     */
    private String network;

    /**
     * Address
     */
    private String address;

}

