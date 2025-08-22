package com.safeheron.client.config;

import lombok.Builder;
import lombok.Data;

/**
 * @author safeheron
 */
@Data
@Builder
public class SafeheronConfig {
    /**
     * Safeheron Request Base URL
     */
    private String baseUrl = "";

    /**
     * api key, you can get from safeheron web console
     */
    private String apiKey = "";

    /**
     * Your RSA private key
     */
    private String rsaPrivateKey = "";

    /**
     * Api key's platform public key, you can get from safeheron web console
     */
    private String safeheronRsaPublicKey = "";

    /**
     * requestTimeout
     */
    private Long requestTimeout = 20000L;
}
