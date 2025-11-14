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
public interface Web3ApiService {
    /**
     * Create a Web3 Wallet Account
     *
     * @param createWeb3AccountRequest createWeb3AccountRequest
     * @return Web3AccountResponse
     * @see CreateWeb3AccountRequest
     * @see Web3AccountResponse
     */
    @POST("/v1/web3/account/create")
    Call<Web3AccountResponse> createWeb3Account(@Body CreateWeb3AccountRequest createWeb3AccountRequest);

    /**
     * Batch Create Web3 Wallet Accounts
     * Create a batch of wallet accounts based on specified number. Web3 wallet accounts created in batches are not displayed in the Safeheron App by default.
     *
     * @param batchCreateWeb3AccountRequest batchCreateWeb3AccountRequest
     * @return BatchCreateWeb3AccountResponse
     * @see BatchCreateWeb3AccountRequest
     * @see BatchCreateWeb3AccountResponse
     */
    @POST("/v1/web3/batch/account/create")
    Call<List<BatchCreateWeb3AccountResponse>> batchCreateWeb3Account(@Body BatchCreateWeb3AccountRequest batchCreateWeb3AccountRequest);

    /**
     * List Web3 Wallet Accounts
     * Filter Web3 wallet account lists by various conditions.
     *
     * @param listWeb3AccountRequest listWeb3AccountRequest
     * @return Web3AccountResponse
     * @see ListWeb3AccountRequest
     * @see Web3AccountResponse
     */
    @POST("/v1/web3/account/list")
    Call<List<Web3AccountResponse>> listWeb3Accounts(@Body ListWeb3AccountRequest listWeb3AccountRequest);

    /**
     * Retrieve a Single Web3 Wallet Account
     * Retrieve a single web3 wallet account based on accountKey or customerRefId
     *
     * @param oneWeb3AccountRequest oneWeb3AccountRequest
     * @return Web3AccountResponse
     * @see OneWeb3AccountRequest
     * @see Web3AccountResponse
     */
    @POST("/v1/web3/account/one")
    Call<Web3AccountResponse> oneWeb3Account(@Body OneWeb3AccountRequest oneWeb3AccountRequest);

    /**
     * Create ethSign
     * Merchants can initiate an ethSign signature through this interface. The merchant is required to serialize the transaction data, generating a corresponding hash (supporting both 0x and non-0x formatted data). The hash is then submitted through this interface to create a signature, which can be obtained by Retrieve a Single Web3 Signature interface or webhook. From there, merchants can complete the subsequent steps according to their own needs once they have obtained the signature.
     *
     * @param createWeb3EthSignRequest createWeb3EthSignRequest
     * @return TxKeyResult
     * @see CreateWeb3EthSignRequest
     * @see TxKeyResult
     */
    @POST("/v1/web3/sign/ethSign")
    Call<TxKeyResult> createWeb3EthSign(@Body CreateWeb3EthSignRequest createWeb3EthSignRequest);

    /**
     * Create personalSign
     * Merchants can initiate a personalSign signature for any text using this interface. The merchant only needs to prepare the data to be signed and submit it through this interface to create the signature. The resulting signature can then be obtained by Retrieve a Single Web3 Signature interface or via webhook. From there, merchants can complete the subsequent steps according to their own needs once they have obtained the signature.
     *
     * @param createWeb3PersonalSignRequest createWeb3PersonalSignRequest
     * @return TxKeyResult
     * @see CreateWeb3PersonalSignRequest
     * @see TxKeyResult
     */
    @POST("/v1/web3/sign/personalSign")
    Call<TxKeyResult> createWeb3PersonalSign(@Body CreateWeb3PersonalSignRequest createWeb3PersonalSignRequest);

    /**
     * Create ethSignTypedData
     * Merchants can initiate an ethSignTypedData signature of specific formatted data (supporting data formats of v1, v3, and v4) through this interface. Merchants will need to format their signature data and submit it through the interface. Once the signature is created, the result can be retrieved via Retrieve a Single Web3 Signature interface or webhook. From there, merchants can complete the subsequent steps according to their own needs once they have obtained the signature.
     *
     * @param CreateWeb3EthSignTypedDataRequest createWeb3EthSignTypedDataRequest
     * @return TxKeyResult
     * @see CreateWeb3EthSignTypedDataRequest
     * @see TxKeyResult
     */
    @POST("/v1/web3/sign/ethSignTypedData")
    Call<TxKeyResult> createWeb3EthSignTypedData(@Body CreateWeb3EthSignTypedDataRequest CreateWeb3EthSignTypedDataRequest);


    /**
     * Create ethSignTransaction
     * Merchants can initiate ethSignTransaction signature transactions through this interface. The merchant must prepare transaction-related data, such as from, to, nonce, gas limit, gas price, value, data, and more. Once this data is submitted, a signature is created and the result can be obtained by Retrieve a Single Web3 Signature interface or webhook. From there, merchants can complete the subsequent steps according to their own needs once they have obtained the signature.
     *
     * @param createWeb3EthSignTransactionRequest createWeb3EthSignTransactionRequest
     * @return TxKeyResult
     * @see CreateWeb3EthSignTransactionRequest
     * @see TxKeyResult
     */
    @POST("/v1/web3/sign/ethSignTransaction")
    Call<TxKeyResult> createWeb3EthSignTransaction(@Body CreateWeb3EthSignTransactionRequest createWeb3EthSignTransactionRequest);

    /**
     * Cancel Signature
     * Cancel pending signatures.
     *
     * @param cancelWeb3SignRequest cancelWeb3SignRequest
     * @return ResultResponse
     * @see CancelWeb3SignRequest
     * @see ResultResponse
     */
    @POST("/v1/web3/sign/cancel")
    Call<ResultResponse> cancelWeb3Sign(@Body CancelWeb3SignRequest cancelWeb3SignRequest);

    /**
     * Retrieve a Single Web3 Signature
     * To query a transaction, either customerRefId or txKey are required. If both values are provided, the retrieval will be based on the txKey.
     *
     * @param oneWeb3SignRequest oneWeb3SignRequest
     * @return Web3SignResponse
     * @see OneWeb3SignRequest
     * @see Web3SignResponse
     */
    @POST("/v1/web3/sign/one")
    Call<Web3SignResponse> oneWeb3Sign(@Body OneWeb3SignRequest oneWeb3SignRequest);

    /**
     * Web3 Sign Transaction List
     * Filter Web3 Sign history by various conditions.
     *
     * @param listWeb3SignRequest listWeb3SignRequest
     * @return Web3SignResponse
     * @see ListWeb3SignRequest
     * @see Web3SignResponse
     */
    @POST("/v1/web3/sign/list")
    Call<List<Web3SignResponse>> listWeb3Sign(@Body ListWeb3SignRequest listWeb3SignRequest);
}
