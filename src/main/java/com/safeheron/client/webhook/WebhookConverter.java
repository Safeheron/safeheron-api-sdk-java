package com.safeheron.client.webhook;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safeheron.client.exception.SafeheronException;
import com.safeheron.client.utils.AesUtil;
import com.safeheron.client.utils.JsonUtil;
import com.safeheron.client.utils.RsaUtil;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Webhook decryption and data conversion converter
 *
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
    public WebHookBizContent convert(WebHook webHook) throws Exception {
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
        String decrypt = AesUtil.decrypt(webHook.getBizContent(), aesKey, iv);
        ObjectMapper mapper = JsonUtil.getObjectMapper();

        //Data conversion
        WebHookBizContent webHookBizContent = mapper.readValue(decrypt, WebHookBizContent.class);
        String webHookBizContentJsonString = JsonUtil.toJson(webHookBizContent.getEventDetail());
        if (WebhookEventTypeEnum.TRANSACTION.getEventTypeList().contains(webHookBizContent.getEventType())) {
            TransactionParam transactionParam = mapper.readValue(webHookBizContentJsonString, TransactionParam.class);
            webHookBizContent.setEventDetail(transactionParam);
        } else if (WebhookEventTypeEnum.MPC_SIGN.getEventTypeList().contains(webHookBizContent.getEventType())) {
            MPCSignParam mpcSignParam = mapper.readValue(webHookBizContentJsonString, MPCSignParam.class);
            webHookBizContent.setEventDetail(mpcSignParam);
        } else if (WebhookEventTypeEnum.WEB3_SIGN.getEventTypeList().contains(webHookBizContent.getEventType())) {
            Web3SignParam web3SignParam = mapper.readValue(webHookBizContentJsonString, Web3SignParam.class);
            webHookBizContent.setEventDetail(web3SignParam);
        }
        return webHookBizContent;
    }
}
