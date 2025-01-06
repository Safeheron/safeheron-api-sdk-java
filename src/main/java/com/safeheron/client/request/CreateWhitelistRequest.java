package com.safeheron.client.request;

import lombok.Data;

/**
 * Create a Whitelist
 *
 * @author safeheron
 */
@Data
public class CreateWhitelistRequest {

    /**
     * Whitelist unique name, 20 characters max
     */
    private String whitelistName;

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
     * Bitcoin Testnet: Bitcoin testnet, which can receive Bitcoin testnet assets
     */
    private String chainType;

    /**
     * Public blockchain address and the address format needs to meet the requirements of the chain
     */
    private String address;

    /**
     * The memo (up to 20 characters) for the destination address, also known as a comment or tag. For the following networks, if a destination address memo was set initially, a memo matching the one in the transaction record must be provided
     * TON: TON mainnet
     * TON_TESTNET: TON testnet
     */
    private String memo;

    /**
     * Visibility status in Safeheron App and Web Console
     * False: Visible by default
     * True: Invisible; the invisible whitelist can only be managed and used through the API, such as querying, modifying, and using the whitelist as the destination address when initiating transactions
     */
    private Boolean hiddenOnUI;
}
