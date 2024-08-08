package com.safeheron.client.request;


import lombok.Data;

/**
 * Retrieve a Single Whitelist
 *
 * @author safeheron
 */
@Data
public class OneWhitelistRequest {

    /**
     * Whitelist unique identifier. It is required if address is not provided. If both are provided, the whitelistKey takes precedence
     */
    private String whitelistKey;

    /**
     * Whitelist address. It is required if whitelistKey is not provided and please make sure the provided address is correct
     */
    private String address;
}
