package com.safeheron.client.request;

import lombok.Data;

import java.util.List;


/**
 * For UTXOs that natively support multiple OUTPUTs, this interface allows a single transaction to transfer funds to multiple destination addresses simultaneously.(To use the Co-Signer, please use version 1.5.9 or higher)
 *
 * @author safeheron
 */
@Data
public class CreateTransactionsUTXOMultidestRequest {
    /**
     * Merchant unique business ID (100 characters max)
     */
    private String customerRefId;

    /**
     * Merchant extended field (defined by merchant) shown to merchant (255 characters max)
     */
    private String customerExt1;

    /**
     * Merchant extended field (defined by merchant) shown to merchant (255 characters max)
     */
    private String customerExt2;

    /**
     * Transaction note (180 characters max)
     */
    private String note;

    /**
     * Coin key
     */
    private String coinKey;

    /**
     * Transaction Fee Rate Grade
     * Choose between transaction fees. If the transaction fee rate is preset, it will take priority
     */
    private String txFeeLevel;

    /**
     * Transaction fee rate, either txFeeLevel or feeRateDto
     */
    private FeeRateDto feeRateDto;

    /**
     * Maximum estimated transaction fee rate for a given transaction
     */
    private String maxTxFeeRate;

    /**
     * Source account key
     */
    private String sourceAccountKey;

    /**
     * Account type
     */
    private String sourceAccountType;

    /**
     * Destination address list
     */
    private List<DestinationAddress> destinationAddressList;

    /**
     * Destination Tag
     */
    private String destinationTag;

    /**
     * Bitcoin enabled for RBF (Replace-by-fee is a protocol in the Bitcoin mempool that allows for the replacement of an unconfirmed transaction with another one)
     */
    private Boolean isRbf;
}
