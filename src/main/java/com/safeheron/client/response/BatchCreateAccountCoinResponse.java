package com.safeheron.client.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author safeheron
 */
@Data
public class BatchCreateAccountCoinResponse {
    /**
     * Address list
     */
    private List<Address> addressList;

    /**
     * Account key
     */
    private String accountKey;

    /**
     * The unique identifier of the address group
     */
    private String addressGroupKey;

    /**
     * Address group name
     */
    private String addressGroupName;

    @Data
    @NoArgsConstructor
    public static class Address{
        /**
         * Coin receiving address
         */
        private String address;

        /**
         * addressType
         */
        private String addressType;

        /**
         * BIP44 derivation path
         */
        private String derivePath;
    }
}
