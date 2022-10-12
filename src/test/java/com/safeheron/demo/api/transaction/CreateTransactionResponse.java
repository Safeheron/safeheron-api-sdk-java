package com.safeheron.demo.api.transaction;

import lombok.Data;

/**
 * Create a new wallet account request
 *
 * @author safeheron
 */
@Data
public class CreateTransactionResponse {
    private String txKey;
}
