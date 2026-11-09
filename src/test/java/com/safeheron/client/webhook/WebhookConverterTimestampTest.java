package com.safeheron.client.webhook;

import com.safeheron.client.config.AESTypeEnum;
import com.safeheron.client.config.RSATypeEnum;
import com.safeheron.client.exception.SafeheronException;
import com.safeheron.client.utils.AesUtil;
import com.safeheron.client.utils.RsaUtil;
import org.junit.BeforeClass;
import org.junit.Test;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.Arrays;
import java.util.Base64;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WebhookConverterTimestampTest {

    private static final long TOLERANCE_MILLIS = 5 * 60 * 1000L;

    private static String platformPubKey;
    private static String platformPriKey;
    private static String clientPubKey;
    private static String clientPriKey;

    @BeforeClass
    public static void init() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair platform = generator.generateKeyPair();
        KeyPair client = generator.generateKeyPair();
        platformPubKey = Base64.getEncoder().encodeToString(platform.getPublic().getEncoded());
        platformPriKey = Base64.getEncoder().encodeToString(platform.getPrivate().getEncoded());
        clientPubKey = Base64.getEncoder().encodeToString(client.getPublic().getEncoded());
        clientPriKey = Base64.getEncoder().encodeToString(client.getPrivate().getEncoded());
    }

    @Test
    public void freshWebhookPassesWhenCheckEnabled() throws Exception {
        WebhookConverter converter = new WebhookConverter(platformPubKey, clientPriKey, TOLERANCE_MILLIS);
        WebHookBizContent content = converter.convert(buildWebHook(System.currentTimeMillis()));
        assertEquals("TEST_EVENT", content.getEventType());
    }

    @Test
    public void expiredWebhookRejectedWhenCheckEnabled() throws Exception {
        WebhookConverter converter = new WebhookConverter(platformPubKey, clientPriKey, TOLERANCE_MILLIS);
        WebHook webHook = buildWebHook(System.currentTimeMillis() - TOLERANCE_MILLIS - 60_000L);
        try {
            converter.convert(webHook);
            throw new AssertionError("expected SafeheronException");
        } catch (SafeheronException e) {
            assertTrue(e.getMessage().startsWith("timestamp expired"));
        }
    }

    @Test
    public void expiredWebhookPassesWhenCheckDisabled() throws Exception {
        WebhookConverter converter = new WebhookConverter(platformPubKey, clientPriKey);
        WebHookBizContent content = converter.convert(buildWebHook(System.currentTimeMillis() - TOLERANCE_MILLIS - 60_000L));
        assertEquals("TEST_EVENT", content.getEventType());
    }

    private WebHook buildWebHook(long timestamp) throws Exception {
        byte[] aesKey = AesUtil.generateAESKey();
        byte[] iv = AesUtil.generateIvKey();
        String bizContent = AesUtil.encrypt("{\"eventType\":\"TEST_EVENT\",\"eventDetail\":{}}", aesKey, iv, AESTypeEnum.GCM);
        byte[] sourceKey = Arrays.copyOf(aesKey, aesKey.length + iv.length);
        System.arraycopy(iv, 0, sourceKey, aesKey.length, iv.length);
        String key = RsaUtil.encrypt(sourceKey, clientPubKey, RSATypeEnum.ECB_OAEP);

        Map<String, String> sigMap = new TreeMap<>();
        sigMap.put("key", key);
        sigMap.put("timestamp", String.valueOf(timestamp));
        sigMap.put("bizContent", bizContent);
        String signContent = sigMap.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));

        WebHook webHook = new WebHook();
        webHook.setTimestamp(String.valueOf(timestamp));
        webHook.setKey(key);
        webHook.setBizContent(bizContent);
        webHook.setSig(RsaUtil.sign(signContent, platformPriKey));
        webHook.setRsaType(RSATypeEnum.ECB_OAEP.getCode());
        webHook.setAesType(AESTypeEnum.GCM.getCode());
        return webHook;
    }
}
