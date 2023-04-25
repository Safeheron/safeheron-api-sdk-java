package com.safeheron.client.request;

import lombok.Data;

/**
 * Change Display of Wallet Account in App request
 *
 * @author safeheron
 */
@Data
public class UpdateAccountShowStateRequest {
    /**
     * Wallet account key
     */
    private String accountKey;

    /**
     * 	Whether display in Safeheron Console
     * True: not display
     * False: display
     * Default: false
     */
    private Boolean hiddenOnUI;
}
