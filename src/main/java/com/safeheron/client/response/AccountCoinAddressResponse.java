package com.safeheron.client.response;

import lombok.Data;

import java.util.List;

/**
 * @author safeheron
 */
@Data
public class AccountCoinAddressResponse {
    /**
     * Address group key
     */
    private String addressGroupKey;

    /**
     * Address group name
     */
    private String addressGroupName;

    /**
     * Merchant unique business ID when adding an address group
     */
    private String customerRefId;

    /**
     * Address list
     */
    private List<AddressResult> addressList;
}

