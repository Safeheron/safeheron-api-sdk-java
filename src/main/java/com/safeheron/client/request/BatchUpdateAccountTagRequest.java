package com.safeheron.client.request;

import lombok.Data;

/**
 * Batch Label Wallet Accounts
 *
 * @author safeheron
 */
@Data
public class BatchUpdateAccountTagRequest {
    /**
     * Wallet account key
     */
    private String accountKey;

    /**
     * Account tag
     */
    private String accountTag;
}
