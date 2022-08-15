package com.safeheron.client.converter;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.safeheron.client.utils.AesUtil;
import com.safeheron.client.utils.JsonUtil;
import com.safeheron.client.utils.RsaUtil;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Request Body Converter
 * @author safeheron
 */
public class RequestBodyConverter<T> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");

    private final ObjectWriter objectWriter;
    private final String apiKey;
    private final String safeheronRsaPublicKey;
    private final String rsaPrivateKey;

    RequestBodyConverter(ObjectWriter writer, String apiKey,
                         String safeheronRsaPublicKey, String rsaPrivateKey) {
        this.objectWriter = writer;
        this.apiKey = apiKey;
        this.safeheronRsaPublicKey = safeheronRsaPublicKey;
        this.rsaPrivateKey = rsaPrivateKey;
    }

    @Override
    public RequestBody convert(T value) {
        try{
            // Use AES to encrypt request data
            final String requestJson = JsonUtil.toJson(value);
            byte[] aesKey = AesUtil.generateAESKey();
            byte[] ivKey = AesUtil.generateIvKey();
            String aesEncryptResult = "";
            if (requestJson != null){
                aesEncryptResult = AesUtil.encrypt(requestJson, aesKey, ivKey);
            }

            // Use Safeheron RSA public key to encrypt request's aesKey and aesIv
            byte[] sourceKey = Arrays.copyOf(aesKey, aesKey.length + ivKey.length);
            System.arraycopy(ivKey, 0, sourceKey, aesKey.length, ivKey.length);
            String rsaEncryptResult = RsaUtil.encrypt(sourceKey, safeheronRsaPublicKey);

            // Create params map
            long timestamp = System.currentTimeMillis();
            Map<String, String> requestData = new TreeMap<>();
            requestData.put("apiKey", apiKey);
            requestData.put("timestamp", timestamp + "");
            requestData.put("bizContent", aesEncryptResult);
            requestData.put("key", rsaEncryptResult);

            // Sign the request data with your RSA private key
            String signContent = requestData.entrySet().stream()
                    .map(entry -> entry.getKey() + "=" + entry.getValue())
                    .collect(Collectors.joining("&"));
            String rsaSig = RsaUtil.sign(signContent, rsaPrivateKey);
            requestData.put("sig", rsaSig);

            byte[] bytes = this.objectWriter.writeValueAsBytes(requestData);
            return RequestBody.create(bytes, MEDIA_TYPE);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}