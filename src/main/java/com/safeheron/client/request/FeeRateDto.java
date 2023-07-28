package com.safeheron.client.request;

/**
 * @author safeheron
 */
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
     * No	Filecoin gas premium, similar to EIP-1559 max priority fee
     */
    private String gasPremium;

    /**
     * No	Filecoin gas fee cap, similar to EIP-1559 max fee
     */
    private String gasFeeCap;

    /**
     * No	SUI gas budget, similar to EIP-1559 max fee
     */
    private String gasBudget;
}
