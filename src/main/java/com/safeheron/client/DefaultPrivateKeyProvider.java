package com.safeheron.client;

import com.safeheron.client.config.RSATypeEnum;
import com.safeheron.client.utils.RsaUtil;

import java.util.Objects;

/**
 * Built-in default KeyProvider implementation that uses a local RSA private key.
 *
 * @author Jiahj
 */
public final class DefaultPrivateKeyProvider implements KeyProvider {

    private final String privateKey;

    public DefaultPrivateKeyProvider(String privateKey) {
        this.privateKey = Objects.requireNonNull(privateKey, "privateKey must not be null");
    }

    @Override
    public String sign(String content) {
        try {
            return RsaUtil.sign(content, privateKey);
        } catch (Exception e) {
            throw new RuntimeException("Failed to sign content with RSA", e);
        }
    }

    @Override
    public String signPSS(String content) {
        try {
            return RsaUtil.signPSS(content, privateKey);
        } catch (Exception e) {
            throw new RuntimeException("Failed to sign content with RSA-PSS", e);
        }
    }

    @Override
    public byte[] decrypt(String content, RSATypeEnum rsaType) {
        try {
            return RsaUtil.decrypt(content, privateKey, rsaType);
        } catch (Exception e) {
            throw new RuntimeException("Failed to decrypt content with RSA", e);
        }
    }
}
