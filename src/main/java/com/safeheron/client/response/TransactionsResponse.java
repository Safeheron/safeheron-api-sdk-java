package com.safeheron.client.response;

import com.safeheron.client.request.DestinationAddress;
import com.safeheron.client.request.GasFee;
import com.safeheron.client.request.SourceAddress;
import lombok.Data;

import java.util.List;


/**
 * @author safeheron
 */

@Data
public class TransactionsResponse {
    /**
     * Transaction key
     */
    private String txKey;

    /**
     * Transaction hash
     */
    private String txHash;

    /**
     * Coin key
     */
    private String coinKey;

    /**
     * Transaction amount, the unit is the symbol returned by the coin list
     */
    private String txAmount;

    /**
     * Source account key
     */
    private String sourceAccountKey;

    /**
     * Source account type
     */
    private String sourceAccountType;

    /**
     * Source address
     */
    private String sourceAddress;

    /**
     * Source address shows potential phishing risk. Only incoming transactions on EVM chains and TRON are valid
     */
    private String isSourcePhishing;

    /**
     * Source address list
     */
    private List<SourceAddress> sourceAddressList;

    /**
     * Destination account key
     */
    private String destinationAccountKey;

    /**
     * Destination account type
     */
    private String destinationAccountType;

    /**
     * Destination address
     */
    private String destinationAddress;

    /**
     * Destination address shows potential phishing risk. Only outgoing transactions on EVM chains and TRON are valid
     */
    private String isDestinationPhishing;

    /**
     * Memo of the destination address when creating a transaction
     */
    private String memo;

    /**
     * Destination address list
     */
    private List<DestinationAddress> destinationAddressList;

    /**
     * If the destination is tag or memo type, then this value is empty
     */
    @Deprecated
    private String destinationTag;

    /**
     * Transaction type
     */
    private String transactionType;

    /**
     * Transaction status
     */
    private String transactionStatus;

    /**
     * Transaction substatus
     */
    private String transactionSubStatus;

    /**
     * Transaction creation time, UNIX timestamp (ms)
     */
    private Long createTime;

    /**
     * Note
     */
    private String note;

    /**
     * Final approver key
     */
    private String auditUserKey;

    /**
     * Creator key
     */
    private String createdByUserKey;

    /**
     * Transaction fee
     */
    private String txFee;

    /**
     * Coin key that is used to pay for the transaction fee when conducting a transfer, such as when transferring ERC-20 tokens, transaction fees are paid in ETH
     */
    private String feeCoinKey;

    /**
     * Total fee
     */
    private List<GasFee> gasFee;

    /**
     * Quoted transaction hash (only for sped-up transactions)
     */
    private String replaceTxHash;

    /**
     * Merchant unique business ID
     */
    private String customerRefId;

    /**
     * Transaction counter used to prevent double-spending and replay attacks. This field has a value when a transaction on an EVM-compatible public chain completes approval, or when a custom nonce is passed during transaction creation
     */
    private String nonce;

    /**
     * Represents the txKey of the replaced transaction, returned only during transaction acceleration. Please note that an accelerated transaction and the original transaction are two completely independent transactions and should not be confused or regarded as the same transaction
     */
    private String replacedTxKey;

    /**
     * Represents the customerRefId of the replaced transaction, returned only during transaction acceleration. Please note that an accelerated transaction and the original transaction are two completely independent transactions and should not be confused or regarded as the same transaction. (Note: This field has a value only for transactions accelerated after March 21, 2025.)
     */
    private String replacedCustomerRefId;

    /**
     * Merchant extended field
     */
    private String customerExt1;

    /**
     * Merchant extended field
     */
    private String customerExt2;

    /**
     * Whether the source address contains AML address(es)
     *     YES: contain
     *     NO: not contain
     */
    private String amlLock;

    /**
     * Block height (for confirming transaction and succeeded transaction)
     */
    private Long blockHeight;

    /**
     * Transaction completion time
     */
    private Long completedTime;

    /**
     * Type of actual destination account
     */
    private String realDestinationAccountType;

    /**
     * Amount in USD when transact
     */
    private String txAmountToUsd;

    /**
     * Source account name
     */
    private String sourceAccountName;

    /**
     * Destination account name
     */
    private String destinationAccountName;

    /**
     * Final approver username
     */
    private String auditUserName;

    /**
     * Creator username
     */
    private String createdByUserName;

    /**
     * Transaction Direction
     */
    private String transactionDirection;
}
