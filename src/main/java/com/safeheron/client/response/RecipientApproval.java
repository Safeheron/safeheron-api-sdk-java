package com.safeheron.client.response;

import lombok.Data;


/**
 * @author safeheron
 */

@Data
public class RecipientApproval {
    /**
     * Recipient Connect ID
     */
    private String connectId;

    /**
     * Recipient Connect profile name
     */
    private String name;

    /**
     * Approval status on the recipient end:
     * PENDING_APPROVAL: Pending approval
     * APPROVED: Approved
     * REJECTED: Rejected
     * CANCELLED: Approval cancelled
     */
    private String approvalStatus;
}
