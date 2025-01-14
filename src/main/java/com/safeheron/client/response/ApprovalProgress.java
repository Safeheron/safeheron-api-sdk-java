package com.safeheron.client.response;

import lombok.Data;

import java.util.List;


/**
 * @author safeheron
 */

@Data
public class ApprovalProgress {
    /**
     * Approval progress details on the recipient end.
     * This field is only returned when the receiving address is Connect and the counterparty has set up incoming funds approval (except when the transaction approval status is BLOCKED_BY_POLICY)
     */
    private RecipientApproval recipientApproval;

    /**
     * Approval progress. Proceeds to team approval after Connect recipient approval (if any) is completed.
     */
    private List<TeamApproval> teamApproval;
}
