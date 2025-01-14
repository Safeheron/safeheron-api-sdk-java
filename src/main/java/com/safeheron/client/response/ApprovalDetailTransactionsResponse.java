package com.safeheron.client.response;

import lombok.Data;

import java.util.List;


/**
 * @author safeheron
 */

@Data
public class ApprovalDetailTransactionsResponse {
    /**
     * List of transaction approval details, excluding the following transactions:
     * Transactions using old transaction policies
     * Transactions that do not exist in the system
     * Incoming fund transactions
     */
    private List<ApprovalDetail> approvalDetailList;
}
