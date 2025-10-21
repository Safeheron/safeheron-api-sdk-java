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
     * Auto-refuel. If set to true, the Gas Service will automatically supplement the Gas fee for the wallet when a transaction is initiated. The default value is false
     */
    private Boolean autoFuel;

    /**
     * Number of wallets to be created, greater than 0, less than 100
     */
    private Integer count;

    /**
     * Account tag
     */
    private String accountTag;
}
