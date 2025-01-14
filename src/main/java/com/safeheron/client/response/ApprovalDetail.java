package com.safeheron.client.response;

import lombok.Data;


/**
 * @author safeheron
 */

@Data
public class ApprovalDetail {
    /**
     * Transaction key
     */
    private String txKey;

    /**
     * Approval status:
     * PENDING_APPROVAL: Pending approval
     * APPROVED: Approved
     * REJECTED: Rejected
     * CANCELLED: Cancelled
     * BLOCKED_BY_POLICY: Blocked by policy
     * FAILED: Failed
     */
    private String approvalStatus;

    /**
     * Name of triggered policy
     */
    private String policyName;

    /**
     * Approval progress details
     */
    private ApprovalProgress approvalProgress;
}
