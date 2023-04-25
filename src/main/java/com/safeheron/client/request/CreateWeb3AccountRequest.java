package com.safeheron.client.request;

import lombok.Data;

/**
 * Create a Web3 Wallet Account
 *
 * @author safeheron
 */
@Data
public class CreateWeb3AccountRequest {
    /**
     * Account name, within 30 characters
     */
    private String accountName;

    /**
     * 	Whether display in Safeheron Console
     * True: not display
     * False: display
     * Default: false
     */
    private Boolean hiddenOnUI;
}
