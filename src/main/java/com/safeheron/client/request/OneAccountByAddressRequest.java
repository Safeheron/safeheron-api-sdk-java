package com.safeheron.client.request;


import lombok.Data;

/**
 * Query Wallet Account by Address request
 * @author safeheron
 */
@Data
public class OneAccountByAddressRequest {

    /**
     * The wallet address. Note: Wallet addresses for the TRON, Solana, and TON networks are case-sensitive, while other networks are case-insensitive
     */
    private String address;
}
