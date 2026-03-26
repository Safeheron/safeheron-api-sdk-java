package com.safeheron.client;

import com.safeheron.client.config.RSATypeEnum;

/**
 * KeyProvider interface for signing and decryption capabilities.
 * This is the single extension point for self-managed key solutions.
 *
 * @author Jiahj
 */
public interface KeyProvider {
    /**
     * Sign content using RSA (PKCS#1 v1.5)
     *
     * @param content the content to sign
     * @return the Base64-encoded signature
     */
    String sign(String content);

    /**
     * Sign content using RSA-PSS
     *
     * @param content the content to sign
     * @return the Base64-encoded RSA-PSS signature
     */
    String signPSS(String content);

    /**
     * Decrypt content using RSA
     *
     * @param content the Base64-encoded encrypted content
     * @param rsaType the RSA algorithm type used for decryption
     * @return the decrypted raw byte array
     */
    byte[] decrypt(String content, RSATypeEnum rsaType);
}
