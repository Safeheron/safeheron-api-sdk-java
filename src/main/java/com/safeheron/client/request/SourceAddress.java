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
     * Source address shows potential phishing risk. Only incoming transactions on EVM chains and TRON are valid
     */
    private String isSourcePhishing;

    /**
     * The unique identifier of the address group of the source address, this field is only returned when the transaction source account type is VAULT_ACCOUNT
     */
    private String addressGroupKey;
}
