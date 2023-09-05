package com.safeheron.client.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author safeheron
 */
@Data
public class BatchCreateAccountCoinUTXOResponse {
    /**
     * Address list
     */
    private List<Address> addressList;

    /**
     * Account key
     */
    private String accountKey;

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
         * amlLock
         */
        private String amlLock;
    }
}

