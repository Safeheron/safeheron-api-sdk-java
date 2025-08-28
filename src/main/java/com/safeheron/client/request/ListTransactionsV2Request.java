package com.safeheron.client.request;


import lombok.Data;

/**
 * Transaction List V2 request
 *
 * @author safeheron
 */
@Data
public class ListTransactionsV2Request extends LimitSearch {
    /**
     * Source account key
     */
    private String sourceAccountKey;

    /**
     * Source account type
     */
    private String sourceAccountType;

    /**
     * Destination account key
     */
    private String destinationAccountKey;

    /**
     * Destination account type
     */
    private String destinationAccountType;

    /**
     * The unique identifier key of a wallet account, used to query all transactions under that wallet. This is only supported for VAULT_ACCOUNT type wallets. This has a higher priority than sourceAccountKey, sourceAccountType, destinationAccountKey, destinationAccountType, and realDestinationAccountType. If accountKey is passed along with the five parameters mentioned above, only accountKey will be effective
     */
    private String accountKey;

    /**
     * Start time for creating a transaction, UNIX timestamp (ms)
     */
    private Long createTimeMin;

    /**
     * End time for creating a transaction, UNIX timestamp (ms)
     */
    private Long createTimeMax;

    /**
     * Min transaction amount
     */
    private String txAmountMin;

    /**
     * Max transaction amount
     */
    private String txAmountMax;

    /**
     * Coin key, multiple coin keys are separated by commas
     */
    private String coinKey;

    /**
     * Transaction fee coin key, multiple coin keys are separated by commas
     */
    private String feeCoinKey;

    /**
     * Transaction status
     */
    private String transactionStatus;

    /**
     * Transaction substatus
     */
    private String transactionSubStatus;

    /**
     * Min duration for completing a transaction, UNIX timestamp (ms)
     */
    private Long completedTimeMin;

    /**
     * Max duration for completing a transaction, UNIX timestamp (ms)
     */
    private Long completedTimeMax;

    /**
     * Merchant unique business ID
     */
    private String customerRefId;

    /**
     * Type of actual destination account
     */
    private String realDestinationAccountType;

    /**
     * Filter out custom transaction amounts, excluding transaction records below a certain amount specified in USD from the query results
     */
    private String hideSmallAmountUsd;

    /**
     * Filter transaction history by transaction direction:
     * Default: Retrieve all types of transactions
     * INFLOW: Retrieve inflows
     * OUTFLOW: Retrieve outflows
     * INTERNAL_TRANSFER: Retrieve internal transfers
     */
    private String transactionDirection;
}
