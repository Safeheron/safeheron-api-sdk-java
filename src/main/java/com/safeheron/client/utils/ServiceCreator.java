package com.safeheron.client.utils;

import com.safeheron.client.config.SafeheronConfig;
import com.safeheron.client.converter.ConverterFactory;
import com.safeheron.client.converter.RequestInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Retrofit;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author safeheron
 * @date 2022/8/1 15:17
 */
public class ServiceCreator {
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder().protocols(Arrays.asList(Protocol.HTTP_1_1));
    private static Retrofit.Builder builder = new Retrofit.Builder();
    private static volatile Retrofit retrofit;

    public static <S> S create(Class<S> serviceClass, SafeheronConfig config) {
        Retrofit instance = getRetrofit(config);
        return instance.create(serviceClass);
    }


    private static Retrofit getRetrofit(SafeheronConfig config) {
        if (retrofit == null) {
            synchronized (Retrofit.class) {
                if (retrofit == null) {
                    builder.baseUrl(config.getBaseUrl());
                    httpClient.addInterceptor(RequestInterceptor.create(config));
                    if (config.getRequestTimeout() != null) {
                        httpClient.connectTimeout(config.getRequestTimeout(), TimeUnit.MILLISECONDS);
                        httpClient.readTimeout(config.getRequestTimeout(), TimeUnit.MILLISECONDS);
                        httpClient.writeTimeout(config.getRequestTimeout(), TimeUnit.MILLISECONDS);
                    }
                    builder.client(httpClient.build());
                    builder.addConverterFactory(ConverterFactory.create(config));
                    retrofit = builder.build();
                }
            }
        }
        return retrofit;
    }
}
