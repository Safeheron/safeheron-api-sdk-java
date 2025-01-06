package com.safeheron.client.request;

import lombok.Data;

/**
 * Create a Whitelist
 *
 * @author safeheron
 */
@Data
public class createFromTransactionWhitelistRequest {

    /**
     * Whitelist unique name, 20 characters max
     */
    private String whitelistName;

    /**
     * Transaction key
     */
    private String txKey;

    /**
     * The destination address in the transaction record; case-sensitive
     */
    private String destinationAddress;

    /**
     * The memo (up to 20 characters) for the destination address, also known as a comment or tag. For the following networks, if a destination address memo was set initially, a memo matching the one in the transaction record must be provided
     * TON: TON mainnet
     * TON_TESTNET: TON testnet
     */
    private String memo;

    /**
     * Visibility status in Safeheron App and Web Console
     * False: Visible by default
     * True: Invisible; the invisible whitelist can only be managed and used through the API, such as querying, modifying, and using the whitelist as the destination address when initiating transactions
     */
    private Boolean hiddenOnUI;
}
