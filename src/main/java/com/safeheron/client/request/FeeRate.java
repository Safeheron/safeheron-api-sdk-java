package com.safeheron.client.request;

import lombok.Data;

/**
 * @author safeheron
 */
@Data
public class FeeRate {
    /**
     * Fee rate, UTXO fee per byte, EVM gas price, TRON fee limit and SUI gas price
     */
    private String feeRate;

    /**
     * Estimated transaction fee
     */
    private String fee;

    /**
     * EVM gas limit
     */
    private String gasLimit;

    /**
     * EIP-1559 base fee
     */
    private String baseFee;

    /**
     * EIP-1559 max priority fee
     */
    private String maxPriorityFee;

    /**
     * EIP-1559 max fee, different from maxTxFeeRate in API
     */
    private String maxFee;

    /**
     * The number of bytes for UTXO-based coin, excluding UTXO-based coins is less than 1,000 satoshis
     */
    private String bytesSize;

    /**
     * Filecoin gas premium, similar to EIP-1559 max priority fee
     */
    private String gasPremium;

    /**
     * Filecoin gas fee cap, similar to EIP-1559 max fee
     */
    private String gasFeeCap;

    /**
     * SUI gasBudget
     */
    private String gasBudget;

    /**
     * The gas price the transaction sender is willing to pay, similar to EVM gasPrice
     */
    private String gasUnitPrice;

    /**
     * The maximum number of gas units that the transaction sender is willing to spend to execute the transaction, similar to EVM gasLimit
     */
    private String maxGasAmount;
}
