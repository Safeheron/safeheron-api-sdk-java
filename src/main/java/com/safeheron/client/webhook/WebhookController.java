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
public class WebhookController {

    private String safeheronWebHookRsaPublicKey;
    private String webHookRsaPrivateKey;

    public WebHookResponse ReceiveWebHook(WebHook webHook) throws Exception {
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
        String dataDecrypt = AesUtil.decrypt(webHook.getBizContent(), aesKey, iv);
        //todo Merchants deal with their own business logic
        WebHookResponse webHookResponse = new WebHookResponse();
        webHookResponse.setCode("200");
        webHookResponse.setMessage("SUCCESS");
        return webHookResponse;
    }
}
