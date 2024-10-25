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
public interface AccountApiService {
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

    /**
     * Retrieve a Single Wallet Account
     * Retrieve a single wallet account in the team by providing accountKey.
     *
     * @param oneAccountRequest
     * @return AccountResponse
     * @see ListAccountRequest
     * @see AccountResponse
     */
    @POST("/v1/account/one")
    Call<AccountResponse> oneAccounts(@Body OneAccountRequest oneAccountRequest);

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
     * Batch Create Wallet Accounts V1
     * Generate a batch of wallet accounts based on a specified quantity. By default, the wallet accounts created in bulk will not be displayed in the Safeheron App. For optimal results, we recommend using the V2 version.
     *
     * @param batchCreateAccountRequest
     * @return BatchCreateAccountResponse
     * @see BatchCreateAccountRequest
     * @see BatchCreateAccountResponse
     */
    @POST("/v1/account/batch/create")
    Call<BatchCreateAccountResponse> batchCreateAccountV1(@Body BatchCreateAccountRequest batchCreateAccountRequest);

    /**
     * Batch Create Wallet Accounts V2
     * Generate a batch of wallet accounts based on a specified quantity. By default, the wallet accounts created in bulk will not be displayed in the Safeheron App.
     *
     * @param batchCreateAccountRequest
     * @return List<CreateAccountResponse>
     * @see BatchCreateAccountRequest
     * @see CreateAccountResponse
     */
    @POST("/v2/account/batch/create")
    Call<List<CreateAccountResponse>> batchCreateAccountV2(@Body BatchCreateAccountRequest batchCreateAccountRequest);

    /**
     * Change Display of Wallet Account in App
     * Change wallet account status in Safeheron App.
     *
     * @param updateAccountShowStateRequest
     * @return ResultResponse
     * @see UpdateAccountShowStateRequest
     * @see ResultResponse
     */
    @POST("/v1/account/update/show/state")
    Call<ResultResponse> updateAccountShowState(@Body UpdateAccountShowStateRequest updateAccountShowStateRequest);

    /**
     * Batch Label Wallet Accounts
     * Relabel a batch of wallet accounts.
     * Please note that it only supports to label wallets which are created by API. And, the wallets have been used to sweep the target account cannot be relabelled.
     *
     * @param batchUpdateAccountTagRequest
     * @return ResultResponse
     * @see UpdateAccountShowStateRequest
     * @see ResultResponse
     */
    @POST("/v1/account/batch/update/tag")
    Call<ResultResponse> batchUpdateAccountTag(@Body BatchUpdateAccountTagRequest batchUpdateAccountTagRequest);


    /**
     * Add Coins to a Wallet Account V1
     * Add a new coin to your wallet account, while generating the default address group for the added coin. Once successfully completed, it will return the address information of the newly created default address group. In case the added currency already exists within the account, it will promptly return the existing default address group information for that coin.
     * In a wallet account, UTXO-based cryptocurrencies can have multiple address groups, while other types of cryptocurrencies usually have only one. To check whether a particular cryptocurrency supports the addition of multiple address groups, simply check the 'isMultipleAddress' parameter through the Coin List.
     *
     * @param createAccountCoinRequest
     * @return List<CreateAccountCoinResponse>
     * @see CreateAccountCoinRequest
     * @see CreateAccountCoinResponse
     */
    @POST("/v1/account/coin/create")
    Call<List<CreateAccountCoinResponse>> createAccountCoin(@Body CreateAccountCoinRequest createAccountCoinRequest);

    /**
     * Add Coins to a Wallet Account V2
     * Add a new coin to your wallet account, and it will generate address information for the added coin. If the added currency already exists within the account, it will promptly return the existing address information for that coin.
     *
     * @param createAccountCoinV2Request
     * @return CreateAccountCoinV2Response
     * @see CreateAccountCoinV2Request
     * @see CreateAccountCoinV2Response
     */
    @POST("/v2/account/coin/create")
    Call<CreateAccountCoinV2Response> createAccountCoinV2(@Body CreateAccountCoinV2Request createAccountCoinV2Request);

    /**
     * Batch Add Coins to Wallet Accounts
     * Bulk addition of specified coins to designated wallet accounts. And, it creates a default address group for each coin and returns the address information contained within the newly created default address group. If a wallet account already contains the currency being added, the function will return the default address group data for that existing coin.
     *
     * @param batchCreateAccountCoinRequest
     * @return List<BatchCreateAccountCoinResponse>
     * @see BatchCreateAccountCoinRequest
     * @see BatchCreateAccountCoinResponse
     */
    @POST("/v1/account/batch/coin/create")
    Call<List<BatchCreateAccountCoinResponse>> batchCreateAccountCoin(@Body BatchCreateAccountCoinRequest batchCreateAccountCoinRequest);

    /**
     * List Coins Within a Wallet Account
     * Retrieve a complete list of all coins associated with a wallet account, along with the default address group information for each coin.
     *
     * @param listAccountCoinRequest
     * @return List<AccountCoinResponse>
     * @see ListAccountCoinRequest
     * @see AccountCoinResponse
     */
    @POST("/v1/account/coin/list")
    Call<List<AccountCoinResponse>> listAccountCoin(@Body ListAccountCoinRequest listAccountCoinRequest);

    /**
     * List Coin Address Group of a Wallet Account
     * Retrieve all address groups for a coin within the wallet account.
     *
     * @param listAccountCoinAddressRequest
     * @return PageResult<AccountCoinAddressResponse>
     * @see ListAccountCoinAddressRequest
     * @see AccountCoinAddressResponse
     */
    @POST("/v1/account/coin/address/list")
    Call<PageResult<AccountCoinAddressResponse>> listAccountCoinAddress(@Body ListAccountCoinAddressRequest listAccountCoinAddressRequest);

    /**
     * Retrieve The Balance of an Address
     * Retrieve the balance of a specific coin address.
     *
     * @param infoAccountCoinAddressRequest
     * @return InfoAccountCoinAddressResponse
     * @see InfoAccountCoinAddressRequest
     * @see InfoAccountCoinAddressResponse
     */
    @POST("/v1/account/coin/address/info")
    Call<InfoAccountCoinAddressResponse> infoAccountCoinAddress(@Body InfoAccountCoinAddressRequest infoAccountCoinAddressRequest);

    /**
     * Rename Coin Address Group of a Wallet Account
     * Rename a coin address group of a wallet account.
     *
     * @param renameAccountCoinAddressRequest
     * @return ResultResponse
     * @see RenameAccountCoinAddressRequest
     * @see ResultResponse
     */
    @POST("/v1/account/coin/address/name")
    Call<ResultResponse> renameAccountCoinAddress(@Body RenameAccountCoinAddressRequest renameAccountCoinAddressRequest);

    /**
     * Add Address Group for UTXO-Based Coin V1
     * Add a new address group for UTXO-based cryptocurrencies under a wallet account. If the coin does not exist, it will be added first, followed by the new address group. The function will return the details of the added address(es).
     *
     * @param createAccountCoinAddressRequest
     * @return CreateAccountCoinAddressResponse
     * @see CreateAccountCoinAddressRequest
     * @see CreateAccountCoinAddressResponse
     */
    @POST("/v1/account/coin/address/create")
    Call<CreateAccountCoinAddressResponse> createAccountCoinAddress(@Body CreateAccountCoinAddressRequest createAccountCoinAddressRequest);


    /**
     * Add Address Group for UTXOs V2
     * Add a new address group for UTXO-based cryptocurrencies under a wallet account.If the coin has not been added to the wallet, it will be added automatically.
     *
     * @param createAccountCoinAddressRequest
     * @return CreateAccountCoinAddressV2Response
     * @see CreateAccountCoinAddressRequest
     * @see CreateAccountCoinAddressV2Response
     */
    @POST("/v2/account/coin/address/create")
    Call<CreateAccountCoinAddressV2Response> createAccountCoinAddressV2(@Body CreateAccountCoinAddressRequest createAccountCoinAddressRequest);

    /**
     * Batch Add Address Groups for UTXO-Based Coin
     * For UTXO-based coins in a wallet account, it is possible to add multiple address groups to the account in bulk by specifying the wallet account and the desired number of address groups. The function will return the details of the added address groups. If the specified coin does not exist in the account, it will be added first, followed by the addition of the corresponding number of address groups.
     *
     * @param batchCreateAccountCoinUTXORequest
     * @return List<BatchCreateAccountCoinUTXOResponse>
     * @see BatchCreateAccountCoinUTXORequest
     * @see BatchCreateAccountCoinUTXOResponse
     */
    @POST("/v1/account/coin/utxo/batch/create")
    Call<List<BatchCreateAccountCoinUTXOResponse>> batchCreateAccountCoinUTXO(@Body BatchCreateAccountCoinUTXORequest batchCreateAccountCoinUTXORequest);
}
