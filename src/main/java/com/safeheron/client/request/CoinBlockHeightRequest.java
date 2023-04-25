package com.safeheron.client.request;


import lombok.Data;

/**
 * Retrieve Current Block Height for Currency request
 *
 * @author safeheron
 */
@Data
public class CoinBlockHeightRequest {
    /**
     * Coin key, multiple coin keys are separated by commas
     */
    private String coinKey;
}

