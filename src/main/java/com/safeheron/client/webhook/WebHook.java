package com.safeheron.client.webhook;

import lombok.Data;

/**
 * @author safeheron
 */
@Data
public class WebHook {
    /**
     * Response timestamp, UNIX millisecond-format string
     */
    private String timestamp;

    /**
     * Signature data after signing response parameters by Safeheron API RSA Private Key
     */
    private String sig;

    /**
     * Encrypted data of random AES key by your API RSA Public Key
     */
    private String key;

    /**
     * AES-encrypted data of response parameters
     */
    private String bizContent;

    private String RSAType;

    private String AESType;
}
