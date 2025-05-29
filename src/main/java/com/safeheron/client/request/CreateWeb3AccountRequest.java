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
     * Account name, 50 characters max
     */
    private String accountName;

    /**
     * Merchant unique business ID (100 characters max)
     * The customerRefId uniquely represents a wallet. In the case of duplicate customerRefId values (for example, when resubmitting due to request timeouts or other errors), the data returned by the interface will remain consistent
     */
    private String customerRefId;

    /**
     * 	Whether display in Safeheron Console
     * True: not display
     * False: display
     * Default: false
     */
    private Boolean hiddenOnUI;
}
