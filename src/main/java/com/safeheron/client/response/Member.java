package com.safeheron.client.response;

import lombok.Data;


/**
 * @author safeheron
 */

@Data
public class Member {
    /**
     * Approver unique identifier
     */
    private String auditUserKey;

    /**
     * Approver name
     */
    private String auditUserName;

    /**
     * Whether the approver is API Co-Signer
     */
    private Boolean isCoSigner;

    /**
     * Approval status of current approver:
     * PENDING_APPROVAL: Pending approval
     * APPROVED: Approved
     * REJECTED: Rejected
     * CANCELLED: Cancelled
     */
    private String approvalStatus;
}
