package com.safeheron.client.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author safeheron
 */

@Data
public class GasTransactionsGetByTxKeyResponse {

    /**
     * Transaction key
     */
    private String txKey;

    /**
     * Transaction fee coin
     */
    private String symbol;

    /**
     * Total fee amount. A single transaction may have multiple Gas records; the total fee paid is the sum of all records with SUCCESS and FAILURE_GAS_CONSUMED statuses.
     */
    private String totalAmount;

    /**
     * Gas records
     */
    private List<Detail> detailList;

    @Data
    @NoArgsConstructor
    public static class Detail {

        /**
         * Energy rental transaction Key
         */
        private String gasServiceTxKey;

        /**
         * Transaction fee coin
         */
        private String symbol;

        /**
         * Amount
         */
        private String amount;

        /**
         * Balance after paying the fee
         */
        private String balance;

        /**
         * SUCCESS: Gas successful
         * FAILURE_GAS_REFUNDED: Gas failed, refunded
         * FAILURE_GAS_CONSUMED: Gas failed, but fees were incurred
         */
        private String status;

        /**
         * Gas resource type, only valid for TRON network:
         * ENERGY
         * BANDWIDTH
         */
        private String resourceType;

        /**
         * Gas deduction time, UNIX timestamp in milliseconds
         */
        private String timestamp;

    }
}
