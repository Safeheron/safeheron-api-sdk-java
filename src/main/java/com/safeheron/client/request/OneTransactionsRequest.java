package com.safeheron.client.request;


import lombok.Data;

/**
 * Retrieve a Transaction request
 *
 * @author safeheron
 */
@Data
public class OneTransactionsRequest  {
    /**
     * Transaction key
     */
    private String txKey;

    /**
     * Merchant unique business ID (100 characters max)
     */
    private String customerRefId;
}
