package com.safeheron.client.cosigner;

import lombok.Data;

/**
 * @author safeheron
 */
@Data
public class CoSignerResponse {

    private Boolean approve;

    private String txKey;
}
