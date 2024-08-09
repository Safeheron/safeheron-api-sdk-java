package com.safeheron.client.request;


import lombok.Data;

/**
 * List Whitelist Data
 *
 * @author safeheron
 */
@Data
public class ListWhitelistRequest extends LimitSearch {

    /**
     * The supported public blockchains for whitelist addresses are:
     * EVM: Ethereum-compatible public chain networks or Layer 2 addresses that can receive its native token and other tokens
     * Bitcoin: Bitcoin mainnet addresses that can receive Bitcoin
     * Bitcoin Cash: Bitcoin Cash network, which can receive BCH
     * Dash: anonymous Dash network, which can receive DASH
     * TRON: Tron mainnet addresses, which can receive TRX and TRC20 tokens, such as USDT and USDC
     * NEAR: NEAR mainnet, which can receive native token NEAR
     * Filecoin: Receive Filecoin native token FIL, but does not support receiving FIL or tokens from the FVM network
     * Sui: Sui mainnet, which can receive native token Sui and other tokens
     * Aptos: Aptos mainnet, which only supports receiving native token Aptos and does not support other tokens yet
     * Solana: Solana mainnet, which can receive native token SOL and other tokens
     * Bitcoin Testnet: Bitcoin testnet, which can receive Bitcoin testnet ass
     */
    private String chainType;

    /**
     * Whitelist status
     * AUDIT: pending approval
     * APPROVED: active
     * REJECTED: rejected
     */
    private String whitelistStatus;

    /**
     * Start time for creating a whitelist in UNIX timestamp (ms) (If no value is provided, the default value is createTimeMax minus 24 hours)
     */
    private Long createTimeMin;

    /**
     * End time for creating a whitelist in UNIX timestamp (ms) (If no value is provided, the default value is the current UTC time)
     */
    private Long createTimeMax;
}
