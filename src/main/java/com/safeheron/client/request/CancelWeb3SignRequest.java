package com.safeheron.client.request;

import lombok.Data;


/**
 * Cancel Signature
 *
 * @author safeheron
 */
@Data
public class CancelWeb3SignRequest {
    /**
     * Transaction key
     */
    private String txKey;
}
