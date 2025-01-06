package com.safeheron.client.request;

import lombok.Data;


/**
 * Create a Transaction request
 *
 * @author safeheron
 */
@Data
public class CreateTransactionRequest {
    /**
     * Merchant unique business ID (100 characters max)
     */
    private String customerRefId;

    /**
     * Merchant extended field (defined by merchant) shown to merchant (255 characters max)
     */
    private String customerExt1;

    /**
     * Merchant extended field (defined by merchant) shown to merchant (255 characters max)
     */
    private String customerExt2;

    /**
     * Transaction note (180 characters max)
     */
    private String note;

    /**
     * Coin key
     */
    private String coinKey;

    /**
     * Transaction Fee Rate Grade
     * Choose between transaction fees. If the transaction fee rate is preset, it will take priority
     */
    private String txFeeLevel;

    /**
     * Transaction fee rate, either txFeeLevel or feeRateDto
     */
    private FeeRateDto feeRateDto;

    /**
     * Maximum estimated transaction fee rate for a given transaction
     */
    private String maxTxFeeRate;

    /**
     * Transaction amount
     */
    private String txAmount;

    /**
     * Deduct transaction fee from the transfer amount
     * False by default. If set to true, transaction fee will be deducted from the transfer amount
     * Note: This parameter can only be considered if a transaction’s asset is a base asset, such as ETH or MATIC. If the asset can’t be used for transaction fees, like USDC, this parameter is ignored
     */
    private Boolean treatAsGrossAmount;

    /**
     * Source account key
     */
    private String sourceAccountKey;

    /**
     * Account type
     */
    private String sourceAccountType;

    /**
     * Destination account key
     * Whitelist key if the destination is a whitelisted account;
     * Wallet account key if the destination is a wallet account;
     * No key for unknown address
     */
    private String destinationAccountKey;

    /**
     * Destination account type
     */
    private String destinationAccountType;

    /**
     * If the destinationAccountType is ONE_TIME_ADDRESS, then this field should have a value
     */
    private String destinationAddress;

    /**
     * The memo (up to 100 characters) for the destination address, also known as a comment or tag. This parameter is valid for transactions on the following networks:
     * TON: TON mainnet
     * TON_TESTNET: TON testnet
     */
    private String memo;

    /**
     * Destination Tag
     */
    @Deprecated
    private String destinationTag;

    /**
     * Bitcoin enabled for RBF (Replace-by-fee is a protocol in the Bitcoin mempool that allows for the replacement of an unconfirmed transaction with another one)
     */
    private Boolean isRbf;

    /**
     * The default setting for the parameter is [true]. This parameter determines whether a transaction can be created when the target address is a smart contract. If set to [false], a transaction can still be created for a contract address
     */
    private Boolean failOnContract;

    /**
     * Custom nonce
     */
    private Long nonce;

    /**
     * Customizable sequence number on Aptos, similar to the nonce in the EVM.
     */
    private Long sequenceNumber;

    /**
     * Balance verification, BALANCE_CHECK by default
     */
    private String balanceVerifyType;
}
