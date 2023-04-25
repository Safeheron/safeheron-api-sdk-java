package com.safeheron.client.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * Create an MPC Sign Transaction
 *
 * @author safeheron
 */
@Data
public class CreateMPCSignTransactionRequest {
    /**
     * Merchant unique business ID (100 characters max)
     */
    private String customerRefId;

    /**
     * Merchant extended field (defined by merchant) shown to merchant (255 characters max)
     */
    private String customerExt1;

    /**
     * Merchant extended field (defined by merchant) shown to merchant (255 characters max)
     */
    private String customerExt2;

    /**
     * Source account key
     */
    private String sourceAccountKey;

    /**
     * Signature algorithm
     */
    private String signAlg;

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
    }
}
