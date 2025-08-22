package com.safeheron.client.api;

import com.safeheron.client.request.GasTransactionsGetByTxKeyRequest;
import com.safeheron.client.response.GasStatusResponse;
import com.safeheron.client.response.GasTransactionsGetByTxKeyResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author safeheron
 */
public interface GasApiService {


    /**
     * Retrieve Gas Balance
     * Retrieve your Gas balance for the TRON energy rental service.
     *
     * @see GasStatusResponse
     * @return GasStatusResponse
     */
    @POST("/v1/gas/status")
    Call<GasStatusResponse> gasStatus();

    /**
     * Retrieve Automatic Gas Records for Transactions
     * When the TRON energy rental service is enabled, Safeheron automatically tops up the required Gas fees for TRON network transactions. This API allows you to query the energy rental records used by a transaction. A single transaction may have multiple records. The actual Gas fee consumed by the transaction is the sum of all records with SUCCESS and FAILURE_GAS_CONSUMED statuses.
     *
     * @param gasTransactionsGetByTxKeyRequest gasTransactionsGetByTxKeyRequest
     * @return GasTransactionsGetByTxKeyRequest
     * @see GasTransactionsGetByTxKeyResponse
     * @see GasTransactionsGetByTxKeyResponse
     */
    @POST("/v1/gas/transactions/getByTxKey")
    Call<GasTransactionsGetByTxKeyResponse> gasTransactionsGetByTxKey(@Body GasTransactionsGetByTxKeyRequest gasTransactionsGetByTxKeyRequest);


}
