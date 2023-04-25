package com.safeheron.client.converter;

import com.safeheron.client.utils.JsonUtil;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

import java.io.IOException;


/**
 * Request Body Converter
 *
 * @author safeheron
 */
public class RequestBodyConverter<T> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");

    @Override
    public RequestBody convert(T value) throws IOException {
        return RequestBody.create(JsonUtil.toJson(value), MEDIA_TYPE);
    }
}