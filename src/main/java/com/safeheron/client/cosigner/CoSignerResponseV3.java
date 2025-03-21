package com.safeheron.client.cosigner;

import lombok.Data;

/**
 * @author safeheron
 */
@Data
public class CoSignerResponseV3 {

    /**
     * APPROVE：Approval
     * REJECT：Rejection
     */
    private String action;

    /**
     * Unique ID of the approval task
     */
    private String approvalId;
}
