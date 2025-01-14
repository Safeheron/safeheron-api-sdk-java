package com.safeheron.client.request;
import lombok.Data;

/**
 * @author safeheron
 */
@Data
public class DestinationAddress {
    /**
     * Destination address
     */
    private String address;

    /**
     * Destination address shows potential phishing risk. Only outgoing transactions on EVM chains and TRON are valid
     */
    private String isDestinationPhishing;

    /**
     * Memo of the destination address when creating a transaction
     */
    private String memo;

    /**
     * The unique identifier of the address group of the destination address, this field is only returned when the destination account type is VAULT_ACCOUNT
     */
    private String addressGroupKey;

    /**
     * Transaction amount
     */
    private String amount;
}
