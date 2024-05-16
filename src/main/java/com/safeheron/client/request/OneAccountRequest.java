package com.safeheron.client.request;


import lombok.Data;

/**
 * Retrieve a Single Wallet Account
 * @author safeheron
 */
@Data
public class OneAccountRequest  {
    /**
     * Account key
     */
    private String accountKey;
}
