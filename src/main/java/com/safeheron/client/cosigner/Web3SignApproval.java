package com.safeheron.client.cosigner;

import lombok.Data;

import java.util.List;

@Data
public class Web3SignApproval {
    private String txKey;

    private String transactionStatus;

    private String transactionSubStatus;

    private String subjectType;

    private Long createTime;

    private String accountKey;

    private String sourceAddress;

    private String auditUserKey;

    private String createdByUserKey;

    private String customerRefId;

    private Message message;

    private MessageHash messageHash;

    private Transaction transaction;

    @Data
    public static class Message {

        private Long chainId;

        private String data;
    }

    @Data
    public static class MessageHash {

        private Long chainId;

        private List<String> data;
    }


    @Data
    public static class Transaction {

        private Long chainId;

        private String data;

        private String to;

        private String value;

        private String gasPrice;

        private Integer gasLimit;

        private String maxPriorityFeePerGas;

        private String maxFeePerGas;

        private Long nonce;

        private String signedTransaction;

        private String txHash;
    }
}
