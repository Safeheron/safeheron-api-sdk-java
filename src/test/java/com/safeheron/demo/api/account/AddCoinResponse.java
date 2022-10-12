package com.safeheron.demo.api.account;

import lombok.Data;

/**
 * Create a new wallet account request
 *
 * @author safeheron
 */
@Data
public class AddCoinResponse {
    private String address;
    private String addressType;
    private String amlLock;
}
