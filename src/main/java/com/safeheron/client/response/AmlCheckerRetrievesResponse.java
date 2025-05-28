package com.safeheron.client.response;

import lombok.Data;

/**
 * @author safeheron
 */

@Data
public class AmlCheckerRetrievesResponse {

    /**
     * Risk assessment request ID
     */
    private String requestId;

    /**
     * Risk assessment request creation time, UNIX millisecond timestamp
     */
    private String createTime;

    /**
     * Blockchain network
     */
    private String network;

    /**
     * Address
     */
    private String address;

    /**
     * Whether it is a malicious address
     */
    private Boolean isMaliciousAddress;

    /**
     * MistTrack risk assessment result
     */
    private MistTrack mistTrack;
}
