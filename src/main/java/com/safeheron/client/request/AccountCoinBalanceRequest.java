package com.safeheron.client.request;

import lombok.Data;

import java.util.List;

/**
 * Retrieve Coin Balance request
 *
 * @author safeheron
 */
@Data
public class AccountCoinBalanceRequest {
    /**
     * Coin Keys, max 10
     */
    private List<String> coinKeyList;
}
