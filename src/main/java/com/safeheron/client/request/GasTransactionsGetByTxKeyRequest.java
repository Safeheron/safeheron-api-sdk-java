package com.safeheron.client.request;


import lombok.Data;

/**
 * @author safeheron
 */
@Data
public class GasTransactionsGetByTxKeyRequest {
    /**
     * Transaction key, obtained from transactions created via the Create a Transaction V3 API, App, or Web Console.
     */
    private String txKey;
}

