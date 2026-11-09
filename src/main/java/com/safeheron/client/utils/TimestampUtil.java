package com.safeheron.client.utils;

import com.safeheron.client.exception.SafeheronException;

/**
 * @author safeheron
 */
public class TimestampUtil {

    /**
     * Reject a callback whose UNIX millisecond timestamp deviates from the local clock by more than toleranceMillis.
     * The check is skipped when toleranceMillis is not positive.
     *
     * @param timestamp UNIX millisecond timestamp
     * @param toleranceMillis allowed deviation in milliseconds, non-positive disables the check
     */
    public static void checkFreshness(String timestamp, long toleranceMillis) {
        if (toleranceMillis <= 0) {
            return;
        }
        long requestTime;
        try {
            requestTime = Long.parseLong(timestamp);
        } catch (NumberFormatException e) {
            throw new SafeheronException("invalid timestamp: " + timestamp);
        }
        if (Math.abs(System.currentTimeMillis() - requestTime) > toleranceMillis) {
            throw new SafeheronException("timestamp expired: " + timestamp);
        }
    }
}
