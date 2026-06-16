package com.safeheron.client.response;

import lombok.Data;

import java.util.List;


/**
 * @author safeheron
 */

@Data
public class KyaSupportedNetworksResponse {

    /**
     * Network identifier
     */
    private String network;

    /**
     * Chain type
     */
    private String chainType;

    /**
     * Providers supporting this network
     */
    private List<String> providers;


}
