package com.safeheron.client.request;

import lombok.Data;


/**
 * UTXO-Based Coin Sweeping request
 *
 * @author safeheron
 */
@Data
public class CollectionTransactionsUTXORequest {
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
     * Transaction fee rate, the unit is the feeUnit returned by the coin list
     */
    private String txFeeRate;

    /**
     * Transaction Fee Rate Grade
     * Choose between the transaction fee rate. If the transaction fee rate is preset, it will take priority
     */
    private String txFeeLevel;

    /**
     * Maximum estimated transaction fee rate for a given transaction
     */
    private String maxTxFeeRate;

    /**
     * Minimum sweeping amount
     */
    private String minCollectionAmount;

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
     * Destination Tag
     */
    private String destinationTag;
}
