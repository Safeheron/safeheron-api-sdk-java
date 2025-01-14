package com.safeheron.client.response;

import lombok.Data;

import java.util.List;


/**
 * @author safeheron
 */

@Data
public class ApprovalNode {
    /**
     * Approval threshold of the node
     */
    private Integer threshold;

    /**
     * Approval node name
     */
    private String name;

    /**
     * Current approval status of the approval node:
     * PENDING_APPROVAL: Pending approval
     * APPROVED: Approved
     * REJECTED: Rejected
     * CANCELLED: Approval cancelled. The following situations will result in the current approval node being in "Approval cancelled" status:
     * 1. A member in the node cancel the transaction
     * 2. A preceding approval node cancel or reject the approval, all subsequent nodes will automatically change to "Approval cancelled" status
     * 3. The Connect recipient (if any) cancel or reject approval, all nodes will automatically change to "Approval cancelled" status
     * 4. Transaction is cancelled before the approval completing, all nodes will automatically change to "Approval cancelled" status
     */
    private String approvalStatus;

    /**
     * Approval member
     */
    private List<Member> members;
}
