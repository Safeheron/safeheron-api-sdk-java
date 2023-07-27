package com.safeheron.client.webhook;

import com.safeheron.client.exception.SafeheronException;
import com.safeheron.client.utils.AesUtil;
import com.safeheron.client.utils.RsaUtil;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author safeheron
 */
public class WebhookConverter {
    /**
     * safeheronWebHookRsaPublicKey
     * WebHook platform public key obtained from the web console
     */
    private final String safeheronWebHookRsaPublicKey;

    /**
     * webHookRsaPrivateKey
     * Own webHookRsaPrivateKey(Pair with RSA Public Key submitted to the web console)
     */
    private final String webHookRsaPrivateKey;

    /**
     * WebhookConverter
     *
     * @param safeheronWebHookRsaPublicKey
     * @param webHookRsaPrivateKey
     */
    public WebhookConverter(String safeheronWebHookRsaPublicKey, String webHookRsaPrivateKey) {
        this.safeheronWebHookRsaPublicKey = safeheronWebHookRsaPublicKey;
        this.webHookRsaPrivateKey = webHookRsaPrivateKey;
    }

    /**
     * convert
     *
     * @param webHook
     * @return String
     * @see WebHook
     * @see WebHookResponse
     */
    public String convert(WebHook webHook) throws Exception {
        // Verify sign
        Map<String, String> sigMap = new TreeMap<>();
        sigMap.put("key", webHook.getKey());
        sigMap.put("timestamp", webHook.getTimestamp().toString());
        sigMap.put("bizContent", webHook.getBizContent());

        String signContent = sigMap.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));
        boolean checkResult = RsaUtil.verifySign(signContent, webHook.getSig(), safeheronWebHookRsaPublicKey);
        if (!checkResult) {
            throw new SafeheronException("signature verification failed");
        }

        // Use your RSA private key to decrypt response's aesKey and aesIv
        byte[] aesSaltDecrypt = RsaUtil.decrypt(webHook.getKey(), webHookRsaPrivateKey);
        byte[] aesKey = Arrays.copyOfRange(aesSaltDecrypt, 0, 32);
        byte[] iv = Arrays.copyOfRange(aesSaltDecrypt, 32, aesSaltDecrypt.length);

        // Use AES to decrypt bizContent
        return AesUtil.decrypt(webHook.getBizContent(), aesKey, iv);
    }
}
