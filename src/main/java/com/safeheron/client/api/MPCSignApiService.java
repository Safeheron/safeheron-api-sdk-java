package com.safeheron.client.api;

import com.safeheron.client.request.CreateMPCSignTransactionRequest;
import com.safeheron.client.request.ListMPCSignTransactionsRequest;
import com.safeheron.client.request.OneMPCSignTransactionsRequest;
import com.safeheron.client.response.MPCSignTransactionsResponse;
import com.safeheron.client.response.TxKeyResult;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import java.util.List;

/**
 * @author safeheron
 */
public interface MPCSignApiService {
    /**
     * Create an MPC Sign Transaction
     * Merchant can initiate MPC Sign via this interface. The merchant must first serialize the transaction data and generate a hash before using this interface to submit the hash and create a transaction. The resulting signature can be retrieved via the MPC Sign transaction interface or webhook. The merchant can proceed with the necessary follow-up processes to obtain the signature according to their specific needs.
     *
     * @param createMPCSignTransactionRequest createMPCSignTransactionRequest
     * @return TxKeyResult
     * @see CreateMPCSignTransactionRequest
     * @see TxKeyResult
     */
    @POST("/v1/transactions/mpcsign/create")
    Call<TxKeyResult> createMPCSignTransactions(@Body CreateMPCSignTransactionRequest createMPCSignTransactionRequest);

    /**
     * Retrieve a Single MPC Sign Transaction
     * To query a specific MPC Sign transaction, either customerRefId or txKey must be provided. If both parameters are provided, the query will be based on the txKey parameter.
     *
     * @param oneMPCSignTransactionsRequest oneMPCSignTransactionsRequest
     * @return MPCSignTransactionsResponse
     * @see OneMPCSignTransactionsRequest
     * @see MPCSignTransactionsResponse
     */
    @POST("/v1/transactions/mpcsign/one")
    Call<MPCSignTransactionsResponse> oneMPCSignTransactions(@Body OneMPCSignTransactionsRequest oneMPCSignTransactionsRequest);

    /**
     * MPC Sign Transaction List
     * Filter MPC Sign transaction history by various conditions.
     *
     * @param listMPCSignTransactionsRequest listMPCSignTransactionsRequest
     * @return MPCSignTransactionsResponse
     * @see ListMPCSignTransactionsRequest
     * @see MPCSignTransactionsResponse
     */
    @POST("/v1/transactions/mpcsign/list")
    Call<List<MPCSignTransactionsResponse>> listMPCSignTransactions(@Body ListMPCSignTransactionsRequest listMPCSignTransactionsRequest);
}
