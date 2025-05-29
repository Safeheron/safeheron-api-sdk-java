package com.safeheron.client.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author safeheron
 */
@Data
public class AccountCoinBalanceResponse {

    /**
     * Coin balances
     */
    private List<CoinBalance> balanceList;

    @Data
    @NoArgsConstructor
    public static class CoinBalance {
        /**
         * Coin key
         */
        private String coinKey;

        /**
         * Total coin balance across all asset wallets (in coin units)
         */
        private String balance;
    }
}

