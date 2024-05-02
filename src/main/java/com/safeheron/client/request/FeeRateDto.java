package com.safeheron.client.request;

import lombok.Data;

/**
 * @author safeheron
 */
@Data
public class FeeRateDto {
    /**
     * Fee rate: fee per byte for UTXO, gas price for EVM chains, free limit for TRON (optional) and gas price for SUI
     */
    private String feeRate;

    /**
     * EVM gas limit
     */
    private String gasLimit;

    /**
     * EIP-1559 max priority fee
     */
    private String maxPriorityFee;

    /**
     * EIP-1559 max fee
     */
    private String maxFee;

    /**
     * Filecoin gas premium, similar to EIP-1559 max priority fee
     */
    private String gasPremium;

    /**
     * Filecoin gas fee cap, similar to EIP-1559 max fee
     */
    private String gasFeeCap;

    /**
     * SUI gas budget, similar to EIP-1559 max fee
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
