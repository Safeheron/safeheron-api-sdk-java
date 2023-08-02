package com.safeheron.client.cosigner;

import lombok.Data;

@Data
public class TransactionApproval {

    private String txKey;

    private String txHash;

    private String coinKey;

    private String txAmount;

    private String sourceAccountKey;

    private String sourceAccountType;

    private String sourceAddress;

    private String destinationAccountKey;

    private String destinationAccountType;

    private String destinationAddress;

    private String destinationTag;

    private String vaultTxDirection;

    private String transactionStatus;

    private String transactionSubStatus;

    private Long createTime;

    private String note;

    private String auditUserKey;

    private String createdByUserKey;

    private String txFee;

    private String feeCoinKey;

    private String replaceTxHash;

    private String customerRefId;

    private String customerExt1;

    private String customerExt2;

    private String amlLock;
}
