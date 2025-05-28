package com.safeheron.client.request;


import lombok.Data;

/**
 * @author safeheron
 */
@Data
public class AmlCheckerRetrievesRequest {
    /**
     * Risk assessment request ID, which can be created through the Create AML Risk Assessment Request interface.
     */
    private String requestId;
}

