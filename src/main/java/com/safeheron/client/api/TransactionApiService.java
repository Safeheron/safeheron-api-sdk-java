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
public interface TransactionApiService {
    /**
     * Transaction List V1
     * Filter transaction history by various conditions. For optimal results, we recommend using the V2 version.
     *
     * @param listTransactionsV1Request
     * @return PageResult<TransactionsResponse>
     * @see ListTransactionsV1Request
     * @see TransactionsResponse
     */
    @POST("/v1/transactions/list")
    Call<PageResult<TransactionsResponse>> listTransactionsV1(@Body ListTransactionsV1Request listTransactionsV1Request);

    /**
     * Transaction List V2
     * Filter transaction history by various conditions.
     *
     * @param listTransactionsV2Request
     * @return List<TransactionsResponse>
     * @see ListTransactionsV2Request
     * @see TransactionsResponse
     */
    @POST("/v2/transactions/list")
    Call<List<TransactionsResponse>> listTransactionsV2(@Body ListTransactionsV2Request listTransactionsV2Request);

    /**
     * Create a new transaction.
     *
     * @param createTransactionRequest
     * @return TxKeyResult
     * @see CreateTransactionRequest
     * @see TxKeyResult
     */
    @POST("/v2/transactions/create")
    Call<TxKeyResult> createTransactions(@Body CreateTransactionRequest createTransactionRequest);

    /**
     * For UTXOs that natively support multiple OUTPUTs, this interface allows a single transaction to transfer funds to multiple destination addresses simultaneously.(To use the Co-Signer, please use version 1.5.9 or higher)
     *
     * @param createTransactionsUTXOMultidestRequest
     * @return TxKeyResult
     * @see CreateTransactionRequest
     * @see TxKeyResult
     */
    @POST("/v1/transactions/utxo/multidest/create")
    Call<TxKeyResult> createTransactionsUTXOMultidest(@Body CreateTransactionsUTXOMultidestRequest createTransactionsUTXOMultidestRequest);

    /**
     * Speed up EVM and UTXO-based Transactions
     * Transactions with low transaction fees and those that have been pending for a long time can be sped up. EVM-based and BTC transactions can be sped up through RBF(If 'isRbf' is set to true during transaction creation, the transaction will be accelerated using RBF acceleration. Otherwise, CPFP acceleration will be used.) For other UTXO-based transactions, CPFP will be used.
     *
     * @param recreateTransactionRequest
     * @return TxKeyResult
     * @see RecreateTransactionRequest
     * @see TxKeyResult
     */
    @POST("/v2/transactions/recreate")
    Call<TxKeyResult> recreateTransactions(@Body RecreateTransactionRequest recreateTransactionRequest);

    /**
     * Retrieve a Transaction
     * To query a transaction, either customerRefId or txKey are required. If both values are provided, the retrieval will be based on the txKey.
     *
     * @param oneTransactionsRequest
     * @return TransactionsResponse
     * @see OneTransactionsRequest
     * @see TransactionsResponse
     */
    @POST("/v1/transactions/one")
    Call<OneTransactionsResponse> oneTransactions(@Body OneTransactionsRequest oneTransactionsRequest);

    /**
     * Retrieve Transaction Approval Details
     * Query approval details of a transaction. Exclusively for transactions using the new advanced transaction policy. Learn more about new advanced transaction policies.
     *
     * @param approvalDetailTransactionsRequest
     * @return ApprovalDetailTransactionsResponse
     * @see ApprovalDetailTransactionsRequest
     * @see ApprovalDetailTransactionsResponse
     */
    @POST("/v1/transactions/approvalDetail")
    Call<ApprovalDetailTransactionsResponse> approvalDetailTransactions(@Body ApprovalDetailTransactionsRequest approvalDetailTransactionsRequest);

    /**
     * Estimate Transaction Fee
     * This interface provides users with an estimated range of transaction fee rates of a given cryptocurrency when creating or speeding up transactions.
     *
     * @param transactionsFeeRateRequest
     * @return TransactionsFeeRateResponse
     * @see TransactionsFeeRateRequest
     * @see TransactionsFeeRateResponse
     */
    @POST("/v2/transactions/getFeeRate")
    Call<TransactionsFeeRateResponse> transactionFeeRate(@Body TransactionsFeeRateRequest transactionsFeeRateRequest);

    /**
     * Cancel Transaction
     * Cancel the authorization-pending transaction and the signing-in-progress transaction.
     *
     * @param cancelTransactionRequest
     * @return ResultResponse
     * @see CancelTransactionRequest
     * @see ResultResponse
     */
    @POST("/v1/transactions/cancel")
    Call<ResultResponse> cancelTransactions(@Body CancelTransactionRequest cancelTransactionRequest);

    /**
     * UTXO-Based Coin Sweeping
     * For multi-address UTXO coins under a wallet account, this interface allows users to collect the balances of certain qualifying addresses into a specified destination address.
     *
     * @param collectionTransactionsUTXORequest
     * @return CollectionTransactionsUTXOResponse
     * @see CollectionTransactionsUTXORequest
     * @see CollectionTransactionsUTXOResponse
     */
    @POST("/v1/transactions/utxo/collection")
    Call<CollectionTransactionsUTXOResponse> collectionTransactionsUTXO(@Body CollectionTransactionsUTXORequest collectionTransactionsUTXORequest);
}
