package com.safeheron.client.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * @author safeheron
 */

@Data
public class KytRepostResponse {
    /**
     * Transaction key
     */
    private String txKey;

    /**
     * Merchant unique business ID
     */
    private String customerRefId;


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
