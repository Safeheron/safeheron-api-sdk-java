package com.safeheron.client.request;

import lombok.Data;


/**
 * Cancel Transaction request
 *
 * @author safeheron
 */
@Data
public class CancelTransactionRequest {
    /**
     * Transaction key
     */
    private String txKey;

    /**
     * Transaction type, TRANSACTION by default
     */
    private String txType;
}
