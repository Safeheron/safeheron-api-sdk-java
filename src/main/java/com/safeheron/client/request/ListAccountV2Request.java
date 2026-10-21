package com.safeheron.client.request;


import lombok.Data;

/**
 * List Wallet Accounts V2
 * direct: query page direction, NEXT (ascending by accountIndex) by default, PREV (descending by accountIndex)
 * limit: the number of items to retrieve at a time, the default is 10, max is 100
 * fromId: accountKey of the last record from the previous result, not required for the first page
 * @author safeheron
 */
@Data
public class ListAccountV2Request extends LimitSearch {
    /**
     * Filter whether there are not-displayed wallet accounts in Safeheron Console
     * True: retrieve hidden wallet accounts
     * False: retrieve displayed wallet accounts
     * Default: retrieve all wallet accounts
     */
    private Boolean hiddenOnUI;

    /**
     * Filter wallets based on autoFuel setting:
     * Default: Ignore this query parameter
     * true: Only query wallets where autoFuel is set to true
     * false: Only query wallets where autoFuel is set to false
     */
    private Boolean autoFuel;

    /**
     * Wallet's archive status in Safeheron App and Web Console
     * True: Archived
     * False: Unarchived
     */
    private Boolean archived;

    /**
     * Filter the response based on this account name prefix
     */
    private String namePrefix;

    /**
     * Filter the response based on this account name suffix
     */
    private String nameSuffix;

    /**
     * Merchant unique business ID (100 characters max)
     */
    private String customerRefId;
}
