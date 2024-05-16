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
     * Transaction amount
     */
    private String amount;
}
