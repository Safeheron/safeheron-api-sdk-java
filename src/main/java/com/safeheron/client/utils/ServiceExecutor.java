package com.safeheron.client.utils;

import com.safeheron.client.exception.SafeheronException;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

/**
 * @author safeheron
 */
@Slf4j
public class ServiceExecutor {
    private static final int MAX_ERROR_BODY_LENGTH = 1024;

    /**
     * Execute a REST call and block until the response is received.
     * @param <T> return param type
     * @param call call
     * @return return
     */
    public static <T> T execute(Call<T> call) {
        try {
            Response<T> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                String errorBody = (null != response.body() ? response.body().toString() : "")
                        + (null != response.errorBody() ? response.errorBody().string() : "");
                log.debug("http request failed, httpCode: {}, body: {}", response.code(), errorBody);
                if (errorBody.length() > MAX_ERROR_BODY_LENGTH) {
                    errorBody = errorBody.substring(0, MAX_ERROR_BODY_LENGTH) + "...";
                }
                throw new SafeheronException(500, "http request failed: " + errorBody);
            }
        } catch (SafeheronException se){
            throw se;
        } catch (IOException ioException) {
            log.error("http request io error", ioException);
            throw new SafeheronException(500, ioException.getMessage());
        } catch (Exception e){
            log.error("http request error", e);
            throw new SafeheronException(500, e.getMessage());
        }
    }
}
