package com.safeheron.client.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.safeheron.client.exception.SafeheronException;
import com.safeheron.client.response.ApiResult;
import com.safeheron.client.utils.AesUtil;
import com.safeheron.client.utils.JsonUtil;
import com.safeheron.client.utils.RsaUtil;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Response Body Converter
 *
 * @author safeheron
 */
public class ResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final ObjectReader reader ;

    private final String safeheronRsaPublicKey;
    private final String rsaPrivateKey;

    ResponseBodyConverter(ObjectReader reader,
                          String safeheronRsaPublicKey, String rsaPrivateKey) {
        this.reader = reader;
        this.safeheronRsaPublicKey = safeheronRsaPublicKey;
        this.rsaPrivateKey = rsaPrivateKey;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        // Decode json data into ApiResult Object
        ObjectMapper mapper = JsonUtil.getObjectMapper();
        ApiResult apiResult = mapper.readValue(value.charStream(), ApiResult.class);

        Integer code = apiResult.getCode();
        String message = apiResult.getMessage();
        if (!Integer.valueOf(200).equals(code)) {
            throw new SafeheronException(code, message);
        }

        try{
            // Verify sign
            Map<String, String> sigMap = new TreeMap<>();
            sigMap.put("key", apiResult.getKey());
            sigMap.put("timestamp", apiResult.getTimestamp().toString());
            sigMap.put("bizContent", apiResult.getBizContent());
            sigMap.put("code", code.toString());
            sigMap.put("message", message);
            String signContent = sigMap.entrySet().stream()
                    .map(entry -> entry.getKey() + "=" + entry.getValue())
                    .collect(Collectors.joining("&"));
            boolean checkResult = RsaUtil.verifySign(signContent, apiResult.getSig(), safeheronRsaPublicKey);
            if (!checkResult) {
                throw new SafeheronException("response signature verification failed");
            }

            // Use your RSA private key to decrypt response's aesKey and aesIv
            byte[] aesSaltDecrypt = RsaUtil.decrypt(apiResult.getKey(), rsaPrivateKey);
            byte[] aesKey = Arrays.copyOfRange(aesSaltDecrypt, 0, 32);
            byte[] iv = Arrays.copyOfRange(aesSaltDecrypt, 32, aesSaltDecrypt.length);

            // Use AES to decrypt bizContent
            String dataDecrypt = AesUtil.decrypt(apiResult.getBizContent(), aesKey, iv);
            return reader.readValue(dataDecrypt);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}