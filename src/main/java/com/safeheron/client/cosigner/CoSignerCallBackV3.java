package com.safeheron.client.cosigner;

import lombok.Data;

/**
 * @author safeheron
 */
@Data
public class CoSignerCallBackV3 {
    /**
     * Response timestamp, UNIX millisecond-format string
     */
    private String timestamp;

    /**
     * Signature data after signing response parameters by Safeheron API RSA Private Key
     */
    private String sig;

    /**
     * version
     */
    private String version;

    /**
     * AES-encrypted data of response parameters
     */
    private String bizContent;
}
