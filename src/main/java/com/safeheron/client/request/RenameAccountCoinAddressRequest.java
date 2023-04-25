package com.safeheron.client.request;

import lombok.Data;

/**
 * Rename Coin Address Group of a Wallet Account request
 *
 * @author safeheron
 */
@Data
public class RenameAccountCoinAddressRequest {
    /**
     * Address group key
     */
    private String addressGroupKey;

    /**
     * Address group name, 30 characters max
     */
    private String addressGroupName;
}
