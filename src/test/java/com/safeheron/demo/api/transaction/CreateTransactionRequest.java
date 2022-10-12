package com.safeheron.demo.api.transaction;

import lombok.Data;

/**
 * @author safeheron
 */
@Data
public class CreateTransactionRequest {
    private String customerRefId;
    private String coinKey;
    private String txFeeLevel;
    private String txAmount;
    private String sourceAccountKey;
    private String sourceAccountType;
    private String destinationAccountKey;
    private String destinationAccountType;
    private String destinationAddress;

}
