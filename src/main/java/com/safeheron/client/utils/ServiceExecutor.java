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
                StringBuilder errorMsgBuilder = new StringBuilder();
                errorMsgBuilder.append("http request failed: ");
                errorMsgBuilder.append(null != response.body() ? response.body().toString() : "");
                errorMsgBuilder.append(null != response.errorBody() ? response.errorBody().string() : "");
                throw new SafeheronException(500, errorMsgBuilder.toString());
            }
        } catch (SafeheronException se){
            throw se;
        } catch (IOException ioException) {
            ioException.printStackTrace();
            throw new SafeheronException(500, ioException.getMessage());
        } catch (Exception e){
            e.printStackTrace();
            throw new SafeheronException(500, e.getMessage());
        }
    }
}
