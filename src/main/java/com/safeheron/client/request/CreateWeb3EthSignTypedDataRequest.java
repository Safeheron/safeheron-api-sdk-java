package com.safeheron.client.request;

import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * Create ethSignTypedData
 *
 * @author safeheron
 */
@Data
public class CreateWeb3EthSignTypedDataRequest {
    /**
     * Source account key
     */
    private String accountKey;

    /**
     * Merchant unique business ID (100 characters max)
     */
    private String customerRefId;

    /**
     * Note
     */
    private String note;

    /**
     * Merchant extended field (defined by merchant) shown to merchant (255 characters max)
     */
    private String customerExt1;

    /**
     * Merchant extended field (defined by merchant) shown to merchant (255 characters max)
     */
    private String customerExt2;

    /**
     * Use custom network
     * False: Use the network supported by Safeheron by default
     * True: Use a custom network added through the Safeheron Browser Extension
     */
    private Boolean useCustomNetwork;

    /**
     * Message
     */
    private Message message;

    /**
     * Message info
     */
    @Data
    @NoArgsConstructor
    public static class Message {
        /**
         * Chain ID (does not participate in signing, only the hash is used for signing)
         */
        private Long chainId;

        /**
         * Data to be signed
         */
        private String data;

        /**
         * EthSignTypedData Version
         */
        private String version;
    }
}
