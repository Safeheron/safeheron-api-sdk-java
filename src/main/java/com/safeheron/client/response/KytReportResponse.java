package com.safeheron.client.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * @author safeheron
 */

@Data
public class KytReportResponse {
    /**
     * Transaction key
     */
    private String txKey;

    /**
     * Merchant unique business ID
     */
    private String customerRefId;

    /**
     * Whether AML compliance screening was triggered for the transaction:
     * IN_PROGRESS: Evaluating — not yet confirmed whether screening will be triggered; amlList is unavailable, please wait for a status update
     * TRIGGERED: Triggered — screening was successfully initiated; check amlList for risk assessment results
     * UNTRIGGERED: Not triggered — screening was not initiated; amlList is empty
     */
    private String amlScreeningTriggeredState;

    private List<Aml> amlList;

    /**
     * Aml
     */
    @Data
    @NoArgsConstructor
    public static class Aml {
        private String provider;

        private String timestamp;

        private String status;

        private String riskLevel;

        private String lastUpdateTime;

        private Object payload;
    }
}
