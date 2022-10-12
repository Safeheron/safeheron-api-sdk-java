package com.safeheron.demo.api.mpcsign;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author safeheron
 */
public interface MpcSignApi {

    /**
     * Create an MPC Sign Transaction
     *
     * @param request
     * @return CreateMpcSignResponse
     */
    @POST("/v1/transactions/mpcsign/create")
    Call<CreateMpcSignResponse> createMpcSign(@Body CreateMpcSignRequest request);


    /**
     * Retrieve an MPC Sign Transaction
     *
     * @param request
     * @return CreateTransactionResponse
     */
    @POST("/v1/transactions/mpcsign/one")
    Call<RetrieveMpcSignResponse> retrieveSig(@Body RetrieveMpcSignRequest request);
}
