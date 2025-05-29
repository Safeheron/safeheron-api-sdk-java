package com.safeheron.client.request;

import lombok.Data;

/**
 * Batch Create Web3 Wallet Accounts
 *
 * @author safeheron
 */
@Data
public class BatchCreateWeb3AccountRequest {
    /**
     * The prefix of wallet account name, 50 characters max
     */
    private String accountName;

    /**
     * Number of wallets to be created, greater than 0, less than 100
     */
    private Integer count;

    /**
     * 	Whether display in Safeheron Console
     * True: not display
     * False: display
     * Default: false
     */
    private boolean hiddenOnUI;
}
