package com.safeheron.client.cosigner;

import lombok.Data;

/**
 * @author safeheron
 */
@Data
public class CoSignerCallBackV3 {
    /**
     * Callback timestamp
     */
    private String timestamp;

    /**
     * Request signature, which can be verified using the API Co-Signer public key. Verification process:
     * Sort and serialize the request data in ascending dictionary order, serialize into bizContent=...&timestamp=...&version=v3 format. Use the API Co-Signer public key, the serialized string, and the sig value in the callback data to verify the signature (algorithm: RSA_PSS_SHA256)
     */
    private String sig;

    /**
     * Interface request parameter protocol version, currently fixed at "v3". Version number changes mean changes to the request data, and developers need to parse the request parameters according to the version
     */
    private String version;

    /**
     * Approval request data. For parsing, first Base64 decode the bizContent string, then perform JSON deserialization
     */
    private String bizContent;
}
