package com.safeheron.client.response;

import lombok.Data;

/**
 * @author safeheron
 */

@Data
public class CoinBalanceSnapshotResponse {
    /**
     * Coin key
     */
    private String coinKey;

    /**
     * Coin balance, displayed in the unit of the symbol specified in the coin list
     */
    private String coinBalance;
}
