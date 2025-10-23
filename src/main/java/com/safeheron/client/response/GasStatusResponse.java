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

    /**
     * Gas configuration
     */
    private List<Configuration> configuration;

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

    @Data
    @NoArgsConstructor
    public static class Configuration {
        /**
         * Network. Currently supported networks are: Ethereum、TRON、BNB Smart Chain、Arbitrum、Polygon
         */
        private String network;

        /**
         * Whether automatic refueling is enabled
         */
        private Boolean enabled;
    }
}
