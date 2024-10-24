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

    private String addressGroupKey;
}
