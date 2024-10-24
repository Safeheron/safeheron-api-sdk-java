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

    private String customerRefId;

    /**
     * Address list
     */
    private List<AddressResult> addressList;
}

