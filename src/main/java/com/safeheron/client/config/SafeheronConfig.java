package com.safeheron.client.config;

import com.safeheron.client.DefaultPrivateKeyProvider;
import com.safeheron.client.KeyProvider;
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
    @Builder.Default
    private String baseUrl = "";

    /**
     * api key, you can get from safeheron web console
     */
    @Builder.Default
    private String apiKey = "";

    /**
     * Your RSA private key.
     * <p>In Self-Managed Key Management scenarios, this field can also be used to store
     * a key identifier (keyId), which your custom {@link com.safeheron.client.KeyProvider}
     * can use to locate the actual key in an external system.</p>
     */
    @Builder.Default
    private String rsaPrivateKey = "";

    /**
     * KeyProvider for signing and decryption.
     * If both keyProvider and rsaPrivateKey are provided, keyProvider takes precedence.
     */
    private KeyProvider keyProvider;

    /**
     * Api key's platform public key, you can get from safeheron web console
     */
    @Builder.Default
    private String safeheronRsaPublicKey = "";

    /**
     * requestTimeout
     */
    @Builder.Default
    private Long requestTimeout = 20000L;

    public KeyProvider getKeyProvider() {
        if (keyProvider != null) {
            return keyProvider;
        }
        if (rsaPrivateKey != null && !rsaPrivateKey.isEmpty()) {
            return new DefaultPrivateKeyProvider(rsaPrivateKey);
        }
        return null;
    }
}
