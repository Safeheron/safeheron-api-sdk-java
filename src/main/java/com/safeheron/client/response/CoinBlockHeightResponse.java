package com.safeheron.client.response;

import lombok.Data;

/**
 * @author safeheron
 */

@Data
public class CoinBlockHeightResponse {
    /**
     * Coin key
     */
    private String coinKey;

    /**
     * Coin's current block height
     */
    private Long localBlockHeight;
}
