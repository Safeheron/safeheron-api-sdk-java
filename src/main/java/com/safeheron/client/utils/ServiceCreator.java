package com.safeheron.client.utils;

import com.safeheron.client.config.SafeheronConfig;
import com.safeheron.client.converter.ConverterFactory;
import com.safeheron.client.converter.RequestInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Retrofit;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author safeheron
 * @date 2022/8/1 15:17
 */
public class ServiceCreator {
    private static volatile Map<String, Retrofit> retrofitMap = new HashMap<>();

    public static <S> S create(Class<S> serviceClass, SafeheronConfig config) {
        Retrofit instance = getRetrofit(config);
        return instance.create(serviceClass);
    }


    private static Retrofit getRetrofit(SafeheronConfig config) {
        Retrofit retrofit = retrofitMap.get(config.getApiKey());
        if (retrofit == null) {
            synchronized (Retrofit.class) {
                if (retrofit == null) {
                    Retrofit.Builder builder = new Retrofit.Builder();
                    builder.baseUrl(config.getBaseUrl());
                    OkHttpClient.Builder httpClient = new OkHttpClient.Builder().protocols(Arrays.asList(Protocol.HTTP_1_1));
                    httpClient.addInterceptor(RequestInterceptor.create(config));
                    Long requestTimeout;
                    if (config.getRequestTimeout() != null) {
                        requestTimeout = config.getRequestTimeout();
                    } else {
                        requestTimeout = 20000L;
                    }
                    httpClient.connectTimeout(requestTimeout, TimeUnit.MILLISECONDS);
                    httpClient.readTimeout(requestTimeout, TimeUnit.MILLISECONDS);
                    httpClient.writeTimeout(requestTimeout, TimeUnit.MILLISECONDS);
                    builder.client(httpClient.build());
                    builder.addConverterFactory(ConverterFactory.create(config));
                    retrofit = builder.build();
                    retrofitMap.put(config.getApiKey(), retrofit);
                    return retrofit;
                }
            }
        }
        return retrofit;
    }
}
