package com.safeheron.client.webhook;

import lombok.Data;

@Data
public class GasBalanceWarningParam {
    private String balance;
    private String threshold;
    private String symbol;
    private String message;
}
