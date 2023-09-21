package com.safeheron.client.response;

import lombok.Data;

/**
 * @author safeheron
 */

@Data
public class CoinResponse {
    /**
     * Coin key
     */
    private String coinKey;

    /**
     * Coin full name
     */
    private String coinFullName;

    /**
     * Coin symbol
     */
    private String coinName;

    /**
     * Coin decimal
     */
    private String coinDecimal;

    /**
     * Transaction URL on explorer
     */
    private String txRefUrl;

    /**
     * Block explorer URL
     */
    private String addressRefUrl;

    /**
     * Coin logo URL
     */
    private String logoUrl;

    /**
     * Coin unit
     */
    private String symbol;

    /**
     * Ability to create multiple address groups
     * Yes: yes
     * No: no
     */
    private String isMultipleAddress;

    /**
     * Coin key that is used to pay for the transaction fee when conducting a transfer, such as when transferring ERC-20 tokens, transaction fees are paid in ETH
     */
    private String feeCoinKey;

    /**
     * Transaction fee unit name (Gwei, satoshis)
     */
    private String feeUnit;

    /**
     * Fee decimal on Safeheron java.io.Console
     */
    private String feeDecimal;

    /**
     * Displayed coin decimal on Safeheron Console
     */
    private String showCoinDecimal;

    /**
     * Coin type
     */
    private String coinType;

    /**
     * Contract address, NATIVE is the native asset, non-NATIVE is the contract address
     */
    private String tokenIdentifier;

    /**
     * Minimum transfer amount, the transfer unit is symbol
     */
    private String minTransferAmount;

    /**
     * Blockchain
     */
    private String blockChain;

    /**
     * Blockchain network
     */
    private String network;

    /**
     * Gas limit set by Safeheron
     */
    private String gasLimit;

    /**
     * Pay MEMO included type
     * Yes: yes
     * No: no
     */
    private String isMemo;

    /**
     * UTXO-based coin, view UTXO-based coins
     * Yes: yes
     * No: no
     */
    private String isUtxo;

    /**
     * Blockchain type
     */
    private String blockchainType;
}
