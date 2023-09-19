package com.safeheron.client.request;

import com.sun.xml.internal.bind.v2.model.core.ID;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * Create ethSignTransaction
 *
 * @author safeheron
 */
@Data
public class CreateWeb3EthSignTransactionRequest {
    /**
     * Source account key
     */
    private String accountKey;

    /**
     * Merchant unique business ID (100 characters max)
     */
    private String customerRefId;

    /**
     * Note
     */
    private String note;

    /**
     * Merchant extended field (defined by merchant) shown to merchant (255 characters max)
     */
    private String customerExt1;

    /**
     * Merchant extended field (defined by merchant) shown to merchant (255 characters max)
     */
    private String customerExt2;

    /**
     * Transaction
     */
    private Transaction transaction;

    /**
     * Transaction info
     */
    @Data
    @NoArgsConstructor
    public static class Transaction {
        /**
         * To
         */
        private String to;

        /**
         * Value (Unit: wei)
         */
        private String value;

        /**
         * Chain ID
         */
        private Long chainId;

        /**
         * Gas price
         */
        private String gasPrice;

        /**
         * Gas limit
         */
        private Long gasLimit;

        /**
         * Max priority fee per gas for EIP-1559
         */
        private String maxPriorityFeePerGas;

        /**
         * Max fee per gas for EIP-1559
         */
        private String maxFeePerGas;

        /**
         * Nonce
         */
        private Long nonce;

        /**
         * Data
         */
        private String data;
    }
}
