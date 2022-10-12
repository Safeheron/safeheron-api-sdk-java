package com.safeheron.demo.api.account;

import lombok.Data;

/**
 * @author safeheron
 */
@Data
public class AddCoinRequest {
    private String coinKey;
    private String accountKey;

}
