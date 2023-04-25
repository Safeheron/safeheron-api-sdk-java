package com.safeheron.client.request;


import lombok.Data;

/**
 * Verify Coin Address request
 *
 * @author safeheron
 */
@Data
public class CheckCoinAddressRequest {
    /**
     * Coin key
     */
    private String coinKey;

    /**
     * Coin receiving address
     */
    private String address;

    /**
     * Verify contract address (If no value is provided, 'false' by default)
     * True: verify
     * False: not verify
     */
    private Boolean checkContract;

    /**
     * Verify AML compliance (If no value is provided or be verified, AML-compliant address by default)
     * True: verify
     * False: not verify
     */
    private Boolean checkAml;

    /**
     * Verify the validity of address format (If no value is provided, 'false' by dafault)
     * True: verify
     * False: not verify
     */
    private Boolean checkAddressValid;
}

