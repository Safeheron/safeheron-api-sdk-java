package com.safeheron.client.request;


import lombok.Data;

/**
 * List Web3 Wallet Accounts request
 * @author safeheron
 */
@Data
public class ListWeb3AccountRequest extends LimitSearch {
    /**
     * Filter the response based on this account name prefix
     */
    private String namePrefix;
}
