package com.safeheron.client.response;

import lombok.Data;

import java.util.List;


/**
 * @author safeheron
 */

@Data
public class TeamApproval {
    /**
     * SINGLE: Single transaction limit
     * CUMULATIVE: Cumulative limit
     */
    private String type;

    /**
     * VALUE: Limited by value
     * AMOUNT: Limited by token denomination amount
     * COUNT: Limited by transaction count, only available for CUMULATIVE (cumulative limit)
     */
    private String limitBy;

    /**
     * Limit range of limitBy in a format as: [min, max]，which means: min <= limitBy < max, where max is -1 means there's no maximum limit
     */
    private List<String> range;

    /**
     * Hour-based limit time period of limitBy.This field is only returned when type is CUMULATIVE
     */
    private Integer timePeriod;

    /**
     * Approval node. If the transaction is blocked by policy, as the transaction approval status is BLOCKED_BY_POLICY, there will be no approval nodes
     */
    private List<ApprovalNode> approvalNodes;
}
