package com.safeheron.client.api;

import com.safeheron.client.request.*;
import com.safeheron.client.response.*;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import java.util.List;

/**
 * @author safeheron
 */
public interface WhitelistApiService {

    /**
     * List Whitelist Data
     * Paginate the whitelist data based on the query criteria.
     *
     * @param listWhitelistRequest
     * @return List<WhitelistResponse>
     * @see ListWhitelistRequest
     * @see WhitelistResponse
     */
    @POST("/v1/whitelist/list")
    Call<List<WhitelistResponse>> listWhitelist(@Body ListWhitelistRequest listWhitelistRequest);

    /**
     * Retrieve a Single Whitelist
     * Retrieve the data of a whitelist.
     *
     * @param oneWhitelistRequest
     * @return OneWhitelistRequest
     * @see WhitelistResponse
     * @see WhitelistResponse
     */
    @POST("/v1/whitelist/one")
    Call<WhitelistResponse> oneWhitelist(@Body OneWhitelistRequest oneWhitelistRequest);

    /**
     * Create a Whitelist
     * Add a new whitelisted address. The newly added address needs to be approved in the Safeheron App before it becomes effective. The approval details are as follows:
     *
     * Admin approval: If a custom whitelist approval process is not set, it will become effective after being approved by the team admins according to the team's decision-making process.
     * Custom whitelist approval: If a whitelist approval process is set, it will become effective after being approved according to the process.
     *
     * @param createWhitelistRequest
     * @return CreateWhitelistRequest
     * @see CreateWhitelistResponse
     * @see CreateWhitelistResponse
     */
    @POST("/v1/whitelist/create")
    Call<CreateWhitelistResponse> createWhitelist(@Body CreateWhitelistRequest createWhitelistRequest);

    /**
     * Create a Whitelist Based on a Transaction
     * Whitelist the transaction's destination address when the transaction meets the following conditions:
     *
     * A transfer transaction from an asset wallet; Web3 wallet transactions or MPC Sign transactions are not supported.
     * The transaction is in a completed state as COMPLETED.
     * The transaction's destination address is a one-time address.
     *
     * @param createFromTransactionWhitelistRequest
     * @return CreateFromTransactionWhitelistRequest
     * @see CreateWhitelistResponse
     * @see CreateWhitelistResponse
     */
    @POST("/v1/whitelist/createFromTransaction")
    Call<CreateWhitelistResponse> createFromTransaction(@Body createFromTransactionWhitelistRequest createFromTransactionWhitelistRequest);




    /**
     * Modify a Whitelist
     * Modify a whitelist based on its unique identifier. The whitelist only supports modifying its name and address; whitelists pending for approval cannot be modified. After modifying the whitelist, it needs to be reviewed and approved in the Safeheron App before it becomes effective. The approval details are as follows:
     *
     * Admin approval: If a custom whitelist approval process is not set, it will become effective after being approved by the team admins according to the team's decision-making process.
     * Custom whitelist approval: If a whitelist approval process is set, it will become effective after being approved according to the process.
     *
     * @param editWhitelistRequest
     * @return EditWhitelistRequest
     * @see ResultResponse
     * @see ResultResponse
     */
    @POST("/v1/whitelist/edit")
    Call<ResultResponse> editWhitelist(@Body EditWhitelistRequest editWhitelistRequest);

    /**
     * Delete a Whitelist
     * To delete a whitelisted address, note that no approval is required for deletion. If a whitelisted address that is under approval is deleted, the approval task will also be automatically cancelled.
     *
     * @param deleteWhitelistRequest
     * @return DeleteWhitelistRequest
     * @see ResultResponse
     * @see ResultResponse
     */
    @POST("/v1/whitelist/delete")
    Call<ResultResponse> deleteWhitelist(@Body DeleteWhitelistRequest deleteWhitelistRequest);

    }
