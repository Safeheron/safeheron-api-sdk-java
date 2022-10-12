package com.safeheron.demo.api.mpcsign;

import lombok.Data;

import java.util.List;

/**
 * Create a new wallet account request
 *
 * @author safeheron
 */
@Data
public class RetrieveMpcSignResponse {
    private String txKey;
    private String transactionStatus;
    private String transactionSubStatus;
    private Long createTime;
    private String sourceAccountKey;
    private String customerRefId;
    private List<SigResult> hashs;

    @Data
    static class SigResult {
        private String hash;
        private String sig;
        private String note;
    }
}
