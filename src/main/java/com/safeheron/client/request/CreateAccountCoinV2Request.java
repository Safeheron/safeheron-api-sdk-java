package com.safeheron.client.request;

import lombok.Data;

import java.util.List;

/**
 * @author safeheron
 */
@Data
public class CreateAccountCoinV2Request {

    private List<String> coinKeyList;

    /**
     * Account key
     */
    private String accountKey;
}
