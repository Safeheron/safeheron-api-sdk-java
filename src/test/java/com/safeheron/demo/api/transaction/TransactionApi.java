package com.safeheron.demo.api.transaction;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author safeheron
 */
public interface TransactionApi {

    /**
     * Create a new transaction.
     *
     * @param request
     * @return CreateTransactionResponse
     */
    @POST("/v2/transactions/create")
    Call<CreateTransactionResponse> createTransaction(@Body CreateTransactionRequest request);
}
