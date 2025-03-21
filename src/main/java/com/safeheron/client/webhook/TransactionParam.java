package com.safeheron.client.webhook;

import com.safeheron.client.request.DestinationAddress;
import com.safeheron.client.request.SourceAddress;
import lombok.Data;

import java.util.List;

@Data
public class TransactionParam {
    private String txKey;
    private String txHash;
    private String coinKey;
    private String txAmount;
    private String sourceAccountKey;
    private String sourceAccountType;
    private String sourceAddress;
    private String isSourcePhishing;
    private List<SourceAddress> sourceAddressList;
    private String destinationAccountKey;
    private String destinationAccountType;
    private String destinationAddress;
    private String isDestinationPhishing;
    private String memo;
    private List<DestinationAddress> destinationAddressList;
    private String destinationTag;
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
    private String nonce;
    private String replacedTxKey;
    private String replacedCustomerRefId;
    private String customerExt1;
    private String customerExt2;
    private String amlLock;
    private Long blockHeight;
    private Long completedTime;
    private String realDestinationAccountType;
    private String transactionType;
    private String transactionDirection;
}
