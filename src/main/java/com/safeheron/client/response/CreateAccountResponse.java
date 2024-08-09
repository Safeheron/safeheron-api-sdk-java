package com.safeheron.client.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author safeheron
 */
@Data
public class CreateAccountResponse {

    /**
     * Wallet account key
     */
    private String accountKey;

    /**
     * Account public key info
     */
    private List<PubKey> pubKeys;

    /**
     * Coin address list
     */
    private List<CoinAddress> coinAddressList;

    /**
     * Account public key info
     */
    @Data
    @NoArgsConstructor
    public static class PubKey {
        /**
         * Signature algorithm, currently supports secp256k1
         */
        private String signAlg;

        /**
         * Account compressed public key
         */
        private String pubKey;
    }

    /**
     * Coin address list
     */
    @Data
    @NoArgsConstructor
    public static class CoinAddress {
        /**
         * Coin key
         */
        private String coinKey;
        /**
         * Address list
         */
        private List<CoinAddressResponse> addressList;

        @Data
        @NoArgsConstructor
        public static class CoinAddressResponse {
            /**
             * Coin receiving address
             */
            private String address;
            /**
             * Address type
             */
            private String addressType;

            /**
             * BIP44 derivation path
             */
            private String derivePath;
        }

    }

}
