package com.safeheron.client.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author safeheron
 */
@Data
public class CreateAccountCoinV2Response {

    /**
     * Account key
     */
    private String accountKey;

    /**
     * Coin address list
     */
    private List<CoinAddress> coinAddressList;

    @Data
    @NoArgsConstructor
    public static class CoinAddress {
        /**
         * Coin key
         */
        private String coinKey;

        /**
         * The unique identifier of the address group
         */
        private String addressGroupKey;

        /**
         * Address group name
         */
        private String addressGroupName;

        /**
         * Address list
         */
        private List<Address> addressList;

        @Data
        @NoArgsConstructor
        public static class Address {
            /**
             * Blockchain type
             */
            private String blockchainType;

            /**
             * Coin receiving address
             */
            private String address;

            /**
             * BIP44 derivation path
             */
            private String derivePath;
        }
    }
}
