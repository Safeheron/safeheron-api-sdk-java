package com.safeheron.client.request;
import lombok.Data;

/**
 * @author safeheron
 */
@Data
public class SourceAddress {
    /**
     * Source address
     */
    private String address;

    /**
     * The unique identifier of the address group of the source address, this field is only returned when the transaction source account type is VAULT_ACCOUNT
     */
    private String addressGroupKey;
}
