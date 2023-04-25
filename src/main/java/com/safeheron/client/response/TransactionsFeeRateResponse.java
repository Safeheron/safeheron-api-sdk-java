package com.safeheron.client.response;

import com.safeheron.client.request.FeeRate;
import lombok.Data;


/**
 * @author safeheron
 */

@Data
public class TransactionsFeeRateResponse {
    /**
     * Fee rate unit
     */
    private String feeUnit;

    /**
     * Minimum fee rate
     */
    private FeeRate minFeeRate;

    /**
     * Fee rate when the transaction fee rate is low
     */
    private FeeRate lowFeeRate;

    /**
     * Fee rate when the transaction fee rate is medium
     */
    private FeeRate middleFeeRate;

    /**
     * Fee rate when the transaction fee rate is high
     */
    private FeeRate highFeeRate;
}
