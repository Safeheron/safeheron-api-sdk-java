package com.safeheron.client.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author safeheron
 */

@Data
public class GasStatusResponse {

    /**
     * Gas Balance
     */
    private List<GasBalance> gasBalance;

    @Data
    @NoArgsConstructor
    public static class GasBalance {
        /**
         * Coin
         */
        private String symbol;

        /**
         * Balance
         */
        private String amount;
    }
}
