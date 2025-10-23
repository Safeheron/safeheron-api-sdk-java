package com.safeheron.client.request;

import lombok.Data;

import java.util.List;

/**
 * Batch Set Auto-Fuel
 *
 * @author safeheron
 */
@Data
public class BatchUpdateAccountFuelRequest {
    /**
     * Account key, max is 100
     */
    private List<String> accountKeyList;

    /**
     * If set to true, Gas Service will automatically supplement the transaction fee (Gas) for the wallet when a transaction is initiated
     */
    private Boolean autoFuel;
}
