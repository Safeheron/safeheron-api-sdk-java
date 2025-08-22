package com.safeheron.client.api;

import com.safeheron.client.request.AmlCheckerRequestRequest;
import com.safeheron.client.request.AmlCheckerRetrievesRequest;
import com.safeheron.client.response.AmlCheckerRequestResponse;
import com.safeheron.client.response.AmlCheckerRetrievesResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author safeheron
 */
public interface ToolsApiService {


    /**
     * Create AML Risk Assessment Request
     *
     * @param amlCheckerRequestRequest amlCheckerRequestRequest
     * @return AmlCheckerRequestResponse
     * @see AmlCheckerRequestRequest
     * @see AmlCheckerRequestResponse
     */
    @POST("/v1/tools/aml-checker/request")
    Call<AmlCheckerRequestResponse> amlCheckerRequest(@Body AmlCheckerRequestRequest amlCheckerRequestRequest);

    /**
     * Retrieve AML Risk Assessment Result
     *
     * @param amlCheckerRetrievesRequest amlCheckerRetrievesRequest
     * @return AmlCheckerRetrievesResponse
     * @see AmlCheckerRetrievesRequest
     * @see AmlCheckerRetrievesResponse
     */
    @POST("/v1/tools/aml-checker/retrieves")
    Call<AmlCheckerRetrievesResponse> amlCheckerRetrieves(@Body AmlCheckerRetrievesRequest amlCheckerRetrievesRequest);


}
