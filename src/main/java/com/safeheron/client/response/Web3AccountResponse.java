package com.safeheron.client.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author safeheron
 */
@Data
public class Web3AccountResponse {

    /**
     * Account Key, the only account identifier
     */
    private String accountKey;

    /**
     * Account name
     */
    private String accountName;

    /**
     * Display status in Safeheron App
     * True: not display
     * False: display
     */
    private Boolean hiddenOnUI;

    /**
     * Account public key information
     */
    private List<PubKey> pubKeyList;

    /**
     * Address list
     */
    private List<Address> addressList;

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
     * Address info
     */
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
    }
}
