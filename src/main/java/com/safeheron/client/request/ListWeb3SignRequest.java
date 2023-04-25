package com.safeheron.client.request;


import lombok.Data;

/**
 * Web3 Sign Transaction List
 *
 * @author safeheron
 */
@Data
public class ListWeb3SignRequest extends LimitSearch {
    /**
     * Web3 Sign type
     */
    private String subjectType;

    /**
     * Transaction status
     */
    private String transactionStatus;

    /**
     * Source account key
     */
    private String accountKey;

    /**
     * Start time for creating a transaction, UNIX timestamp (ms) (If no value is provided, the default value is createTimeMax minus 24 hours)
     */
    private Long createTimeMin;

    /**
     * End time for creating a transaction, UNIX timestamp (ms) (If no value is provided, the default value is the current UTC time)
     */
    private Long createTimeMax;
}
