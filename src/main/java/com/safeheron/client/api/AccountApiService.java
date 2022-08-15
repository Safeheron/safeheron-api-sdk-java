package com.safeheron.client.api;

import com.safeheron.client.request.CreateAccountRequest;
import com.safeheron.client.request.ListAccountRequest;
import com.safeheron.client.response.AccountResponse;
import com.safeheron.client.response.CreateAccountResponse;
import com.safeheron.client.response.PageResult;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author safeheron
 */
public interface AccountApiService {

    /**
     * Create a new wallet account.
     *
     * @param createAccountRequest
     * @return CreateAccountResponse
     * @see CreateAccountRequest
     * @see CreateAccountResponse
     */
    @POST("/v1/account/create")
    Call<CreateAccountResponse> createAccount(@Body CreateAccountRequest createAccountRequest);

    /**
     * List Wallet Accounts
     * Filter wallet account lists in team according to different combinations of conditions.
     *
     * @param listAccountRequest
     * @return PageResult<AccountResponse>
     * @see ListAccountRequest
     * @see AccountResponse
     */
    @POST("/v1/account/list")
    Call<PageResult<AccountResponse>> listAccounts(@Body ListAccountRequest listAccountRequest);

}
