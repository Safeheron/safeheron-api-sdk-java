package com.safeheron.client.response;

import com.safeheron.client.request.CreateWeb3EthSignRequest;
import com.safeheron.client.request.CreateWeb3EthSignTransactionRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * @author safeheron
 */

@Data
public class Web3SignResponse {
    /**
     * Transaction key
     */
    private String txKey;

    /**
     * Source account key
     */
    private String accountKey;

    /**
     * Source address
     */
    private String sourceAddress;

    /**
     * Transaction status
     */
    private String transactionStatus;

    /**
     * Transaction substatus
     */
    private String transactionSubStatus;

    /**
     * Creator key
     */
    private String createdByUserKey;

    /**
     * Creator username
     */
    private String createdByUserName;

    /**
     * Transaction creation time, UNIX timestamp (ms)
     */
    private Long createTime;

    /**
     * Final approver key
     */
    private String auditUserKey;

    /**
     * Final approver username
     */
    private String auditUserName;

    /**
     * Merchant unique business ID
     */
    private String customerRefId;

    /**
     * Note
     */
    private String note;

    /**
     * Merchant extended field
     */
    private String customerExt1;

    /**
     * Merchant extended field
     */
    private String customerExt2;

    /**
     * Account balance
     */
    private String balance;

    /**
     * Token balance
     */
    private String tokenBalance;

    /**
     * Coin unit
     */
    private String symbol;

    /**
     * Token name
     */
    private String tokenSymbol;

    /**
     * Signature Type
     */
    private String subjectType;

    /**
     * This field is returned when the signature type is ETH_SIGNTRANSACTION
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
         * Gas limit¬
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

        /**
         * Transaction hash (This value is returned for signed transactions)
         */
        private String txHash;
        /**
         * Hexadecimal data (This value is returned for signed transactions)
         */
        private String signedTransaction;
        /**
         * Signature
         */
        private Sig sig;

        @Data
        @NoArgsConstructor
        public static class Sig {
            /**
             * Hash
             */
            private String hash;

            /**
             * Signature (This value is returned for signed transactions)
             */
            private String sig;
        }
    }


    /**
     * Message
     */
    private Message message;

    /**
     * Message info
     */
    @Data
    @NoArgsConstructor
    public static class Message {
        /**
         * Chain ID
         */
        private Long chainId;

        /**
         * Data
         */
        private String data;

        /**
         * Signature
         */
        private Sig sig;

        @Data
        @NoArgsConstructor
        public static class Sig {
            /**
             * Hash
             */
            private String hash;

            /**
             * Signature (This value is returned for signed transactions)
             */
            private String sig;
        }
    }


    /**
     * Message Hash
     */
    private MessageHash messageHash;

    /**
     * MessageHash info
     */
    @Data
    @NoArgsConstructor
    public static class MessageHash {
        /**
         * Chain ID
         */
        private Long chainId;

        /**
         * Signature list
         */
        private List<Sig> sigList;

        @Data
        @NoArgsConstructor
        public static class Sig {
            /**
             * Hash
             */
            private String hash;

            /**
             * Signature (This value is returned for signed transactions)
             */
            private String sig;
        }
    }
}
