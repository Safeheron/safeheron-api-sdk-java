package com.safeheron.client.request;


import lombok.Data;

/**
 * Re-push Transaction Webhook Events
 *
 * @author safeheron
 */
@Data
public class ResendWebhookRequest extends LimitSearch {

    /**
     * Supported events:
     * TRANSACTION
     * MPC_SIGN
     * WEB3_SIGN
     */
    private String category;

    /**
     * Transaction key
     */
    private String txKey;
}
