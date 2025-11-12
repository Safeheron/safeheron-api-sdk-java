package com.safeheron.client.request;


import lombok.Data;

/**
 * Retrieve a Single Web3 Wallet Account request
 * @author safeheron
 */
@Data
public class OneWeb3AccountRequest  {
    /**
     * Account Key, the only account identifier。The Account Key, which is the unique identifier for the account. Cannot be empty at the same time as the customerRefId parameter. If both are provided, the accountKey parameter will take precedence.
     */
    private String accountKey;

    /**
     * Merchant unique business ID (100 characters max)
     */
    private String customerRefId;
}
