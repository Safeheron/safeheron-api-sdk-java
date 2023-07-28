package com.safeheron.client.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author safeheron
 */

@Data
public class CheckCoinAddressResponse {
    /**
     * Valid address format
     * True: valid address
     * False: invalid address
     */
    private Boolean addressValid;

    /**
     * Contract address
     * True: contract address
     * False: non-contract address
     */
    private Boolean contract;

    /**
     * Subject to risk control limitations
     * True: AML valid address
     * False: AML blacklisted address
     */
    private Boolean amlValid;
}
