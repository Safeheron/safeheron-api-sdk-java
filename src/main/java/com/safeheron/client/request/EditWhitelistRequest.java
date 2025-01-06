package com.safeheron.client.request;

import lombok.Data;

/**
 * Modify a Whitelist
 *
 * @author safeheron
 */
@Data
public class EditWhitelistRequest {

    /**
     * Whitelist unique identifier
     */
    private String whitelistKey;

    /**
     * Whitelist unique name, 20 characters max
     */
    private String whitelistName;

    /**
     * Public blockchain address and the address format needs to meet the requirements of the chain
     */
    private String address;

    /**
     * The memo (up to 20 characters) for the destination address, also known as a comment or tag. For the following networks, if a destination address memo was set initially, a memo matching the one in the transaction record must be provided
     * TON: TON mainnet
     * TON_TESTNET: TON testnet
     */
    private String memo;

    /**
     * When the whitelist is involved in a transaction approval policy, modifications will result in the new whitelist being directly applied to the approval policy. False by default, meaning that when involved in a transaction approval policy, it will not be modified.
     */
    private Boolean force;
}
