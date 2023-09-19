package com.safeheron.client.utils;

import com.safeheron.client.config.SafeheronConfig;
import com.safeheron.client.converter.ConverterFactory;
import com.safeheron.client.converter.RequestInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Retrofit;

import java.util.Arrays;

/**
 * @author safeheron
 * @date 2022/8/1 15:17
 */
public class ServiceCreator {
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder().protocols(Arrays.asList(Protocol.HTTP_1_1));
    private static Retrofit.Builder builder = new Retrofit.Builder();
    private static Retrofit retrofit;

    public static <S> S create(Class<S> serviceClass, SafeheronConfig config) {
        if (retrofit == null) {
            builder.baseUrl(config.getBaseUrl());
            httpClient.addInterceptor(RequestInterceptor.create(config));
            builder.client(httpClient.build());
            builder.addConverterFactory(ConverterFactory.create(config));
            retrofit = builder.build();
        }
        return retrofit.create(serviceClass);
    }
}
