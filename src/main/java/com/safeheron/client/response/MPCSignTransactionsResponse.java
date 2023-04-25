package com.safeheron.client.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * @author safeheron
 */

@Data
public class MPCSignTransactionsResponse {
    /**
     * Transaction key
     */
    private String txKey;

    /**
     * Transaction status
     */
    private String transactionStatus;

    /**
     * Transaction substatus
     */
    private String transactionSubStatus;

    /**
     * Transaction creation time, UNIX timestamp (ms)
     */
    private Long createTime;

    /**
     * Source account key
     */
    private String sourceAccountKey;

    /**
     * Final approver key
     */
    private String auditUserKey;

    /**
     * Creator key
     */
    private String createdByUserKey;

    /**
     * Merchant unique business ID
     */
    private String customerRefId;

    /**
     * Merchant extended field
     */
    private String customerExt1;

    /**
     * Merchant extended field
     */
    private String customerExt2;

    /**
     * Signature algorithm
     */
    private String signAlg;

    /**
     * Final approver username
     */
    private String auditUserName;

    /**
     * Creator username
     */
    private String createdByUserName;

    /**
     * List of transaction data to be signed
     */
    private List<Date> dataList;

    /**
     * Date info
     */
    @Data
    @NoArgsConstructor
    static class Date {
        /**
         * Transaction note (180 characters max)
         */
        private String note;

        /**
         * Transaction data to be signed (view description below for details)
         */
        private String data;

        /**
         * Transaction signature (The value of sig consists of 32 bytes r + 32 bytes s + 1 byte v)
         */
        private String sig;
    }
}
