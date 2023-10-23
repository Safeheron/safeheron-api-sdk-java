package com.safeheron.client.request;

import lombok.Data;

/**
 * Batch Create Wallet Accounts V1/V2 request
 *
 * @author safeheron
 */
@Data
public class BatchCreateAccountRequest {
    /**
     * The prefix of wallet account name, 30 characters max
     */
    private String accountName;

    /**
     * Number of wallets to be created, greater than 0, less than 100
     */
    private Integer count;

    /**
     * Account tag
     */
    private String accountTag;
}
