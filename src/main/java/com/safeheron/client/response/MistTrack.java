package com.safeheron.client.response;

import lombok.Data;

import java.util.List;

/**
 * @author safeheron
 */

@Data
public class MistTrack {

    /**
     * MistTrack risk assessment status:
     * EVALUATING: In evaluation
     * SUCCESS: Evaluation completed
     */
    private String status;

    /**
     * Risk assessment completion time, risk assessment is time-sensitive, please evaluate in real-time, do not rely on historical evaluation results
     */
    private String evaluationTime;

    /**
     * Risk score of the address, range: 3 ~ 100
     */
    private String score;

    /**
     * Risk level, divided according to the risk score, specifically as follows:
     * Low: 0 ~ 30
     * Moderate: 31 ~ 70
     * High: 71 ~ 90
     * Severe: 91 ~ 100
     */
    private String riskLevel;

    /**
     * Risk description of the address
     */
    private List<String> detailList;

    /**
     * Risk assessment details
     */
    private List<RiskDetail> riskDetail;
}
