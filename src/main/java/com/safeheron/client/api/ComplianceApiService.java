package com.safeheron.client.api;

import com.safeheron.client.request.KyaScreeningOneRequest;
import com.safeheron.client.request.KyaScreeningOrderOneRequest;
import com.safeheron.client.request.KyaScreeningRequest;
import com.safeheron.client.request.KytReportRequest;
import com.safeheron.client.response.KyaScreeningCreateResponse;
import com.safeheron.client.response.KyaScreeningOneResponse;
import com.safeheron.client.response.KyaScreeningOrderOneResponse;
import com.safeheron.client.response.KyaSupportedNetworksResponse;
import com.safeheron.client.response.KytReportResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import java.util.List;

/**
 * @author safeheron
 */
public interface ComplianceApiService {


    /**
     * Retrieve Transaction KYT Report
     *
     * @param kytReportRequest kytReportRequest
     * @return KytReportResponse
     * @see KytReportRequest
     * @see KytReportResponse
     */
    @POST("/v1/compliance/kyt/report")
    Call<KytReportResponse> kytReport(@Body KytReportRequest kytReportRequest);

    /**
     * Create KYA Screening Request
     *
     * @param kyaScreeningRequest kyaScreeningRequest
     * @return KyaScreeningCreateResponse
     * @see KyaScreeningRequest
     * @see KyaScreeningCreateResponse
     */
    @POST("/v1/compliance/kya/screening/create")
    Call<KyaScreeningCreateResponse> kyaScreeningCreate(@Body KyaScreeningRequest kyaScreeningRequest);

    /**
     * Retrieve KYA Screening Summary
     *
     * @param kyaScreeningOneRequest kyaScreeningOneRequest
     * @return KyaScreeningOneResponse
     * @see KyaScreeningOneRequest
     * @see KyaScreeningOneResponse
     */
    @POST("/v1/compliance/kya/screening/one")
    Call<KyaScreeningOneResponse> kyaScreeningOne(@Body KyaScreeningOneRequest kyaScreeningOneRequest);

    /**
     * Retrieve KYA Screening Order Details
     *
     * @param kyaScreeningOrderOneRequest kyaScreeningOrderOneRequest
     * @return KyaScreeningOrderOneResponse
     * @see KyaScreeningOrderOneRequest
     * @see KyaScreeningOrderOneResponse
     */
    @POST("/v1/compliance/kya/screening/order/one")
    Call<KyaScreeningOrderOneResponse> kyaScreeningOrderOne(@Body KyaScreeningOrderOneRequest kyaScreeningOrderOneRequest);

    /**
     * Retrieve Supported Networks & Providers
     *
     * @return KyaSupportedNetworksResponse
     * @see KyaSupportedNetworksResponse
     */
    @POST("/v1/compliance/kya/supportedNetworks")
    Call<List<KyaSupportedNetworksResponse>> kyaSupportedNetworks();
}
