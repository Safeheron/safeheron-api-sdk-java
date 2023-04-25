package com.safeheron.client.response;

import lombok.Data;

import java.util.List;

/**
 * @author safeheron
 */
@Data
public class AccountCoinResponse {
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
    private Long coinDecimal;

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
     * Fee decimal on Safeheron Console
     */
    private Long feeDecimal;

    /**
     * Displayed coin decimal on Safeheron Console
     */
    private Long showCoinDecimal;

    /**
     * Account balance
     */
    private String balance;

    /**
     * Account balance, convert it into USD when query
     */
    private String usdBalance;

    /**
     * Coin address list
     */
    private List<AddressResult> addressList;
}

