package com.safeheron.client.api;

import com.safeheron.client.response.ResultResponse;
import retrofit2.Call;
import retrofit2.http.POST;

/**
 * @author safeheron
 */
public interface APIKeyManagementService {

    /**
     * Deactivate API Key
     * This interface is used to apply to deactivate the currently used API Key (this can also be done in the Web Console). After the application is submitted, it will only take effect once a team administrator approves it in the Safeheron App. Once an API Key is deactivated, it will no longer be able to access any interfaces. For example, you can apply to deactivate an API Key in the following situations to protect your assets:
     *
     * The API Key is no longer in use and should be deactivated promptly to avoid risk;
     * The API Key's private key is suspected of being leaked, and it needs to be deactivated immediately to ensure fund security;
     * You receive an Illegal IP Request Event via Webhook and, after evaluation, need to deactivate the API Key (Event Type: ILLEGAL_IP_REQUEST);
     * To resume using it, you can submit an activation request through the Web Console. This will also require approval from a team administrator before the API Key can be reactivated.
     *
     * @return ResultResponse
     * @see ResultResponse
     */
    @POST("/v1/apikey/disable")
    Call<ResultResponse> disableApikey();
}
