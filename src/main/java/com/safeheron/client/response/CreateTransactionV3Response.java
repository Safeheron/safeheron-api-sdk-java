package com.safeheron.client.response;

import lombok.Data;

/**
 * @author safeheron
 */
@Data
public class CreateTransactionV3Response {
    /**
     * Transaction key
     */
    private String txKey;

    /**
     * Merchant unique business ID for transaction creation
     */
    private String customerRefId;

    /**
     * With idempotentRequest set to true, the system enforces idempotency by returning the original txKey for duplicate customerRefIds, preventing redundant transaction creation
     */
    private Boolean idempotentRequest;
}
