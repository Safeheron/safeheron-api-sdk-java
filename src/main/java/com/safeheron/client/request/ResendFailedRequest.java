package com.safeheron.client.request;


import lombok.Data;

/**
 * Push All Failed Webhook Events
 *
 * @author safeheron
 */
@Data
public class ResendFailedRequest {

    /**
     * Start time in UNIX timestamp (ms). The time interval [startTime, endTime] is up to 1 hour
     */
    private Long startTime;

    /**
     * End time in UNIX timestamp (ms). The time interval [startTime, endTime] is up to 1 hour
     */
    private Long endTime;
}
