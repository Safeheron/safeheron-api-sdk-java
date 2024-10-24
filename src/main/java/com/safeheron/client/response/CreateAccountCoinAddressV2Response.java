package com.safeheron.client.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author safeheron
 */
@Data
public class CreateAccountCoinAddressV2Response {

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

