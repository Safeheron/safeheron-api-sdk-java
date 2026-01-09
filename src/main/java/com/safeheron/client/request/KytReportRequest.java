package com.safeheron.client.request;


import lombok.Data;

/**
 * @author safeheron
 */
@Data
public class KytReportRequest {
    /**
     * Transaction key
     */
    private String txKey;

    /**
     * Merchant unique business ID (100 characters max)
     */
    private String customerRefId;
}

