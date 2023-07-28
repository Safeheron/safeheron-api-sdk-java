package com.safeheron.client.webhook;

import lombok.Data;

import java.util.List;

@Data
public class Web3SignParam {
    private String txKey;

    private String transactionStatus;

    private String transactionSubStatus;


    private String subjectType;


    private Long createTime;


    private String accountKey;


    private String sourceAddress;


    private String auditUserKey;


    private String createdByUserKey;


    private String customerExt1;


    private String customerExt2;

    private String note;


    private Message message;

    private MessageHash messageHash;


    private Transaction transaction;

    @Data
    public static class Message {

        private Sig sig;

        @Data
        public static class Sig {

            private String hash;


            private String sig;

        }


        private Long chainId;


        private String data;
    }

    @Data
    public static class MessageHash {


        private Long chainId;


        private List<Sig> sigList;

        @Data
        public static class Sig {


            private String sig;


            private String hash;
        }
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


        private Sig sig;

        @Data
        public static class Sig {

            private String hash;


            private String sig;

        }


        private String signedTransaction;


        private String txHash;
    }
}
