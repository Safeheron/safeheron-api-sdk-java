package com.safeheron.client.request;

import lombok.Data;

import java.util.List;

/**
 * Create a new wallet account request
 *
 * @author safeheron
 */
@Data
public class CreateAccountRequest {
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

    /**
     * Auto-refuel. If set to true, the Gas Service will automatically supplement the Gas fee for the wallet when a transaction is initiated. The default value is false
     */
    private Boolean autoFuel;

    /**
     * 	Coin key list, 20 array elements max
     */
    private List<String> coinKeyList;

    /**
     * Account tag
     */
    private String accountTag;
}
