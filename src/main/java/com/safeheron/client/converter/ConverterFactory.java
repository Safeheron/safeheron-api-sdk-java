package com.safeheron.client.converter;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.safeheron.client.config.SafeheronConfig;
import com.safeheron.client.utils.JsonUtil;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * @author safeheron
 */
public class ConverterFactory extends Converter.Factory {

    private static ObjectMapper mapper = JsonUtil.getObjectMapper();
    private SafeheronConfig config;

    public static ConverterFactory create(SafeheronConfig config) {
        return new ConverterFactory(config);
    }

    private ConverterFactory(SafeheronConfig config) {
        this.config = config;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {
        JavaType javaType = mapper.getTypeFactory().constructType(type);
        ObjectReader reader = mapper.readerFor(javaType);
        return new ResponseBodyConverter<>(reader,
                config.getSafeheronRsaPublicKey(), config.getRsaPrivateKey());
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations,
                                                          Annotation[] methodAnnotations, Retrofit retrofit) {
        return new RequestBodyConverter<>();
    }
}
