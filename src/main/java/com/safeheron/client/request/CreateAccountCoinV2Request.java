package com.safeheron.client.request;

import lombok.Data;

import java.util.List;

/**
 * @author safeheron
 */
@Data
public class CreateAccountCoinV2Request {

    /**
     * Coin key list, 20 array elements max
     */
    private List<String> coinKeyList;

    /**
     * Account key
     */
    private String accountKey;
}
