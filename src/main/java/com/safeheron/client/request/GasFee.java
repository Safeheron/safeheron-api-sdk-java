package com.safeheron.client.request;
import lombok.Data;

/**
 * @author safeheron
 */
@Data
public class GasFee {
    /**
     * Currency of handling fees paid, such as TRX, ETH, BSC, ARB, POL, USDT
     */
    private String symbol;

    /**
     * Amount of fees paid
     */
    private String amount;
}
