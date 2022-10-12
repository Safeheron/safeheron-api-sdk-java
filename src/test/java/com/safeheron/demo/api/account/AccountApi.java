package com.safeheron.demo.api.account;

import com.safeheron.client.request.CreateAccountRequest;
import com.safeheron.client.request.ListAccountRequest;
import com.safeheron.client.response.AccountResponse;
import com.safeheron.client.response.CreateAccountResponse;
import com.safeheron.client.response.PageResult;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import java.util.List;

/**
 * @author safeheron
 */
public interface AccountApi {

    /**
     * Create a new wallet account.
     *
     * @param request
     * @return CreateAccountResponse
     * @see CreateAccountRequest
     * @see CreateAccountResponse
     */
    @POST("/v1/account/create")
    Call<CreateAccountResponse> createAccount(@Body CreateAccountRequest request);

    /**
     * List Wallet Accounts
     * Filter wallet account lists in team according to different combinations of conditions.
     *
     * @param request
     * @return PageResult<AccountResponse>
     * @see ListAccountRequest
     * @see AccountResponse
     */
    @POST("/v1/account/list")
    Call<PageResult<AccountResponse>> listAccounts(@Body ListAccountRequest request);

    /**
     * Add Coins to a Wallet Account
     *
     * @param request
     * @return List<AddCoinResponse>
     */
    @POST("/v1/account/coin/create")
    Call<List<AddCoinResponse>> addCoin(@Body AddCoinRequest request);

}
