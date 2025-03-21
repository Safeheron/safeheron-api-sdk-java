package com.safeheron.client.cosigner;

import com.safeheron.client.request.DestinationAddress;
import com.safeheron.client.request.SourceAddress;
import lombok.Data;

import java.util.List;

@Data
public class TransactionApproval {

    private String txKey;

    private String txHash;

    private String coinKey;

    private String txAmount;

    private String transactionType;

    private String sourceAccountKey;

    private String sourceAccountType;

    private String sourceAddress;

    private String destinationAccountKey;

    private String destinationAccountType;

    private String destinationAddress;

    private List<DestinationAddress> destinationAddressList;

    private  List<SourceAddress> sourceAddressList;

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

    private String replacedTxKey;

    private String replacedCustomerRefId;

    private String customerExt1;

    private String customerExt2;

    private String amlLock;

    private String estimateFee;

    private String curveType;

    private ProfileTxDTO sourceProfile;

    private ProfileTxDTO destinationProfile;

    private String replaceTxKey;
}
