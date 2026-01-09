package com.safeheron.client.request;

import lombok.Data;

/**
 * @author safeheron
 */
@Data
public class Aml {

    private String provider;

    private String timestamp;

    private String status;

    private String riskLevel;

    private String lastUpdateTime;
}
