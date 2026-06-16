package com.safeheron.client.request;


import lombok.Data;

import java.util.List;

/**
 * @author safeheron
 */
@Data
public class KyaScreeningRequest {
    /**
     * On-chain address to screen, not limited to addresses inside Safeheron
     */
    private String address;

    /**
     * Chain type. See Retrieve Supported Networks & Providers for valid values
     */
    private String chainType;

    /**
     * Blockchain network identifier. Required when providers contains MistTrack. See Retrieve Supported Networks & Providers for valid values
     */
    private String network;

    /**
     * Screening providers. At least one, no duplicates; multiple values are screened in parallel:
     * MistTrack
     * Elliptic
     * Chainalysis
     */
    private List<String> providers;
}

