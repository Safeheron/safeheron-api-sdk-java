package com.safeheron.client.webhook;

import com.safeheron.client.request.Aml;
import lombok.Data;

import java.util.List;

@Data
public class AMLKYTAlertParam {
    /**
     * Transaction key
     */
    private String txKey;

    /**
     * Merchant unique business ID
     */
    private String customerRefId;

    /**
     * Transaction hash
     */
    private String txHash;

    /**
     * Coin key
     */
    private String coinKey;

    /**
     * Transaction amount, the unit is the symbol returned by the coin list
     */
    private String txAmount;

    /**
     * Source account name
     */
    private String sourceAccountName;

    /**
     * Source account key
     */
    private String sourceAccountKey;

    /**
     * Source account type
     */
    private String sourceAccountType;

    /**
     * Source address
     */
    private String sourceAddress;

    /**
     * Destination account name
     */
    private String destinationAccountName;

    /**
     * Destination account key
     */
    private String destinationAccountKey;

    /**
     * Destination account type
     */
    private String destinationAccountType;

    /**
     * Destination address
     */
    private String destinationAddress;

    /**
     * Transaction Direction
     */
    private String transactionDirection;

    private List<Aml> amlList;
}
