package com.safeheron.client.request;


import lombok.Data;

/**
 * Retrieve a Single Web3 Signature
 *
 * @author safeheron
 */
@Data
public class OneWeb3SignRequest {
    /**
     * Transaction key
     */
    private String txKey;

    /**
     * Merchant unique business ID (100 characters max)
     */
    private String customerRefId;
}
