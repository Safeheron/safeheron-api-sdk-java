package com.safeheron.client.api;

import com.safeheron.client.request.CheckCoinAddressRequest;
import com.safeheron.client.request.CoinBalanceSnapshotRequest;
import com.safeheron.client.request.CoinBlockHeightRequest;
import com.safeheron.client.response.*;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import java.util.List;

/**
 * @author safeheron
 */
public interface CoinApiService {
    /**
     * Coin List
     * Retrieve the list of coins supported by Safeheron.
     *
     * @return CoinResponse
     * @see CoinResponse
     */
    @POST("/v1/coin/list")
    Call<List<CoinResponse>> listCoin();

    /**
     * Coin Maintenance List
     * Retrieve the information of coins under maintenance in Safeheron.
     *
     * @return CoinMaintainResponse
     * @see CoinMaintainResponse
     */
    @POST("/v1/coin/maintain/list")
    Call<List<CoinMaintainResponse>> listCoinMaintain();

    /**
     * Verify Coin Address
     * Verify the correctness of a cryptocurrency address based on the provided validation attributes.
     *
     * @param checkCoinAddressRequest checkCoinAddressRequest
     * @return CheckCoinAddressResponse
     * @see CheckCoinAddressRequest
     * @see CheckCoinAddressResponse
     */
    @POST("/v1/coin/address/check")
    Call<CheckCoinAddressResponse> checkCoinAddress(@Body CheckCoinAddressRequest checkCoinAddressRequest);

    /**
     * Snapshot the Coin Balance
     * Safeheron takes and stores daily snapshots of balances based on the transaction block's creation time in GMT+8. Please note that the snapshot only keeps data within 30 days.
     *
     * @param coinBalanceSnapshotRequest coinBalanceSnapshotRequest
     * @return CoinBalanceSnapshotResponse
     * @see CoinBalanceSnapshotRequest
     * @see CoinBalanceSnapshotResponse
     */
    @POST("/v1/coin/balance/snapshot")
    Call<List<CoinBalanceSnapshotResponse>> coinBalanceSnapshot(@Body CoinBalanceSnapshotRequest coinBalanceSnapshotRequest);

    /**
     * Retrieve Current Block Height for Currency
     * Retrieve the current block height for a specific cryptocurrency by providing its key.
     *
     * @param coinBlockHeightRequest coinBlockHeightRequest
     * @return CoinBlockHeightResponse
     * @see CoinBlockHeightRequest
     * @see CoinBlockHeightResponse
     */
    @POST("/v1/coin/block/height")
    Call<List<CoinBlockHeightResponse>> coinBlockHeight(@Body CoinBlockHeightRequest coinBlockHeightRequest);
}
