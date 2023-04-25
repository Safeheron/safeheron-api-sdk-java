package com.safeheron.client.response;

import lombok.Data;

/**
 * @author safeheron
 */

@Data
public class CoinMaintainResponse {
    /**
     * Coin key
     */
    private String coinKey;

    /**
     * Under maintenance or not
     */
    private Boolean maintain;

    /**
     * Maintenance title
     */
    private String title;

    /**
     * Content
     */
    private String content;

    /**
     * Coin maintenance start time, UNIX time in milliseconds
     */
    private String startTime;

    /**
     * Coin maintenance end time, UNIX time in milliseconds
     */
    private String endTime;
}
