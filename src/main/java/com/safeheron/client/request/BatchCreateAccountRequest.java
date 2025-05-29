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
     * The prefix of wallet account name, 50 characters max
     */
    private String accountName;

    /**
     * Display status in Safeheron App
     * True: not display
     * False: display
     * Default: true
     */
    private Boolean hiddenOnUI;

    /**
     * Number of wallets to be created, greater than 0, less than 100
     */
    private Integer count;

    /**
     * Account tag
     */
    private String accountTag;
}
