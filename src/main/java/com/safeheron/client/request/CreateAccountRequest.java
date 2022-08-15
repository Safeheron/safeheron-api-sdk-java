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
     * Whether auto fuel (the Gas Station service)
     * True: auto fuel
     * False: no auto fuel
     * Default: true
     */
    private Boolean autoFuel;

    /**
     * 	Whether display in Safeheron Console
     * True: not display
     * False: display
     * Default: false
     */
    private Boolean hiddenOnUI;
}
