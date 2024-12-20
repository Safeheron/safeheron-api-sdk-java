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
     * The unique identifier of the address group of the destination address, this field is only returned when the destination account type is VAULT_ACCOUNT
     */
    private String addressGroupKey;

    /**
     * Transaction amount
     */
    private String amount;
}
