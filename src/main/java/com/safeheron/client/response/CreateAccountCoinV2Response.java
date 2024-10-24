package com.safeheron.client.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author safeheron
 */
@Data
public class CreateAccountCoinV2Response {

    private String accountKey;

    private List<CoinAddress> coinAddressList;

    @Data
    @NoArgsConstructor
    public static class CoinAddress {

        private String coinKey;

        private String addressGroupKey;

        private String addressGroupName;

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
