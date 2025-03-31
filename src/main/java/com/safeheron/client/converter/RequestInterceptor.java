package com.safeheron.client.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.safeheron.client.config.AESTypeEnum;
import com.safeheron.client.config.RSATypeEnum;
import com.safeheron.client.config.SafeheronConfig;
import com.safeheron.client.utils.AesUtil;
import com.safeheron.client.utils.JsonUtil;
import com.safeheron.client.utils.RsaUtil;
import okhttp3.*;
import okio.Buffer;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class RequestInterceptor implements Interceptor {

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private static final ObjectMapper mapper = JsonUtil.getObjectMapper();

    private final ObjectWriter objectWriter;
    private final String apiKey;
    private final String safeheronRsaPublicKey;
    private final String rsaPrivateKey;


    public static RequestInterceptor create(SafeheronConfig config) {
        return new RequestInterceptor(config);
    }

    private RequestInterceptor(SafeheronConfig config) {
        this.objectWriter = mapper.writerFor(Map.class);
        this.apiKey = config.getApiKey();
        this.safeheronRsaPublicKey = config.getSafeheronRsaPublicKey();
        this.rsaPrivateKey = config.getRsaPrivateKey();
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();
        if ("POST".equals(request.method())) {
            try {
                // Use AES to encrypt request data
                Buffer buffer = new Buffer();
                request.body().writeTo(buffer);
                final String requestJson = buffer.readUtf8();
                byte[] aesKey = AesUtil.generateAESKey();
                byte[] ivKey = AesUtil.generateIvKey();
                String aesEncryptResult = "";
                if (StringUtils.isNotBlank(requestJson)) {
                    aesEncryptResult = AesUtil.encrypt(requestJson, aesKey, ivKey, AESTypeEnum.GCM);
                }

                // Use Safeheron RSA public key to encrypt request's aesKey and aesIv
                byte[] sourceKey = Arrays.copyOf(aesKey, aesKey.length + ivKey.length);
                System.arraycopy(ivKey, 0, sourceKey, aesKey.length, ivKey.length);
                String rsaEncryptResult = RsaUtil.encrypt(sourceKey, safeheronRsaPublicKey,RSATypeEnum.ECB_OAEP);

                // Create params map
                long timestamp = System.currentTimeMillis();
                Map<String, String> requestData = new TreeMap<>();
                requestData.put("apiKey", apiKey);
                requestData.put("timestamp", timestamp + "");
                if (StringUtils.isNotBlank(requestJson)) {
                    requestData.put("bizContent", aesEncryptResult);
                }
                requestData.put("key", rsaEncryptResult);

                // Sign the request data with your RSA private key
                String signContent = requestData.entrySet().stream()
                        .map(entry -> entry.getKey() + "=" + entry.getValue())
                        .collect(Collectors.joining("&"));
                String rsaSig = RsaUtil.sign(signContent, rsaPrivateKey);
                requestData.put("sig", rsaSig);
                requestData.put("rsaType", RSATypeEnum.ECB_OAEP.getCode());
                requestData.put("aesType", AESTypeEnum.GCM.getCode());

                byte[] bytes = this.objectWriter.writeValueAsBytes(requestData);
                return chain.proceed(request.newBuilder().post(RequestBody.create(bytes, MEDIA_TYPE)).build());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return chain.proceed(request);
    }
}
