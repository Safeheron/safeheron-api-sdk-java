package com.safeheron.client.request;


import lombok.Data;

import java.util.List;

/**
 * Retrieve Transaction Approval Details request
 *
 * @author safeheron
 */
@Data
public class ApprovalDetailTransactionsRequest {

    /**
     * Transaction key list within 20 transaction keys
     */
    private List<String> txKeyList;
}
