package com.safeheron.client.api;

import com.safeheron.client.request.AmlCheckerRequestRequest;
import com.safeheron.client.request.KytReportRequest;
import com.safeheron.client.response.AmlCheckerRequestResponse;
import com.safeheron.client.response.KytRepostResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author safeheron
 */
public interface ComplianceApiService {


    /**
     * Create AML Risk Assessment Request
     *
     * @param kytReportRequest kytReportRequest
     * @return KytReportRequest
     * @see AmlCheckerRequestRequest
     * @see AmlCheckerRequestResponse
     */
    @POST("/v1/compliance/kyt/report")
    Call<KytRepostResponse> kytReport(@Body KytReportRequest kytReportRequest);
}
