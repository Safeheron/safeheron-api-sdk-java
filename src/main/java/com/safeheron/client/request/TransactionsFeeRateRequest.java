package com.safeheron.client.request;


import lombok.Data;

import java.util.List;


/**
 * Estimate Transaction Fee request
 *
 * @author safeheron
 */
@Data
public class TransactionsFeeRateRequest {
    /**
     * Coin key
     */
    private String coinKey;

    /**
     * Transaction hash, pass the original transaction hash when speed up transaction estimation
     */
    private String txHash;

    /**
     * Source account key, required for UTXO-based coins
     */
    private String sourceAccountKey;

    /**
     * Source address are required for TRON when estimating transaction fees. For EVM-based transactions, the source address is required when retrieving the gas limit on the blockchain. Otherwise, a default fixed gas limit value will be returned
     */
    private String sourceAddress;

    /**
     * Destination address is optional for TRON and FIL when estimating transaction fees (although providing it may result in a more accurate fee estimation). For EVM-based transactions, the destination address is required when retrieving the gas limit on the blockchain. Otherwise, a default fixed gas limit value will be returned
     */
    private String destinationAddress;

    /**
     * Destination address list
     */
    private List<DestinationAddress> destinationAddressList;

    /**
     * Transfer amount is required to calculate gas limit more accurately when using EVM chains. When using UTXO, providing the amount can estimate transaction fees more accurately. If no amount is provided, the calculation is based on the maximum UTXO quantity. When using SUI, providing the amount can estimate gas budget more accurately
     */
    private String value;
}
