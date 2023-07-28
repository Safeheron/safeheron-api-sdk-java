package com.safeheron.client.request;

import lombok.Data;


/**
 * Speed up EVM and UTXO-based Transactions request
 *
 * @author safeheron
 */
@Data
public class RecreateTransactionRequest {
    /**
     * Transaction key
     */
    private String txKey;

    /**
     * Transaction hash
     */
    private String txHash;

    /**
     * Coin key
     */
    private String coinKey;

    /**
     * Transaction Fee Rate Grade
     * Choose between transaction fees. If the transaction fee rate is preset, it will take priority
     */
    private String txFeeLevel;

    /**
     * Transaction fee rate, either txFeeLevel or feeRateDto
     */
    private FeeRateDto feeRateDto;
}
