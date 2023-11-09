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

    /**
     * 	Coin key list, 20 array elements max
     */
    private List<String> coinKeyList;

    /**
     * Account tag
     */
    private String accountTag;
}
