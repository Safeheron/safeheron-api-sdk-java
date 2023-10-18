package com.safeheron.client.request;

import lombok.Data;

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
     * Account tag
     */
    private String accountTag;
}
