package com.safeheron.client.response;

import lombok.Data;


/**
 * @author safeheron
 */

@Data
public class CollectionTransactionsUTXOResponse {
    /**
     * Transaction key
     */
    private String txKey;

    /**
     * Sweeping amount, the unit is the symbol returned by the coin list
     */
    private String collectionAmount;

    /**
     * Number of collections
     */
    private Long collectionNum;
}
