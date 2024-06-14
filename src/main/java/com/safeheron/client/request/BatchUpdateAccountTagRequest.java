package com.safeheron.client.request;

import lombok.Data;

import java.util.List;

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
    private List<String> accountKeyList;

    /**
     * Account tag
     */
    private String accountTag;
}
