package com.safeheron.client.request;

import lombok.Data;

/**
 * @author safeheron
 */
@Data
public class LimitSearch {
    /**
     * Query page direction, NEXT by default
     */
    private Long direct;

    /**
     * The number of items to retrieve at a time, default max value is 500
     */
    private Long limit;
    
    /**
     * txkey of the first transaction record. If the first page has no value, provide the txKey of the last transaction record from the previous result
     */
    private Long fromId;
}
