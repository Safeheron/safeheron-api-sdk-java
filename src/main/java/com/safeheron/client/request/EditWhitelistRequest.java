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
     * When the whitelist is involved in a transaction approval policy, modifications will result in the new whitelist being directly applied to the approval policy. False by default, meaning that when involved in a transaction approval policy, it will not be modified.
     */
    private Boolean force;
}
